package com.cloudera.sprue

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.storage.StorageLevel
import org.apache.spark.rdd._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.kie.api.runtime.StatelessKieSession
import java.nio.file.Files
import java.nio.file.Paths
import org.apache.hadoop.hbase.HBaseConfiguration
import com.cloudera.spark.hbase.HBaseContext

object SepsisStream {

  def main(args: Array[String]) {
    if (args.length < 3) {
      System.err.println("Usage: SepsisStream <xlsFileName> <zk-quorum-for-hbase> <tsdburl>")
      System.exit(1)
    }

    //params
    val xlsFileName = args(0)
    val zkString = args(1)
    val url = args(2)

    //5 seconds interval
    val batchInterval = 5

    //check if the rules file exists
    if (!Files.exists(Paths.get(xlsFileName))) {
      println("Error: Rules file [" + xlsFileName + "] is missing")
      return
    }

    //setup spark. Toggle below two lines for local v/s yarn
    val sparkConf = new SparkConf().setAppName("Sepsis Rules Streaming Evaluation")
    //  val sparkConf = new SparkConf().setMaster("local[2]").setAppName("Sepsis Rules Streaming Evaluation")
   
    val sc = new SparkContext(sparkConf)
    val ssc = new StreamingContext(sc, Seconds(batchInterval))
    val sqc = new SQLContext(sc);
    // ssc.sparkContext.setLogLevel("ERROR") //spark 1.4 thingy

    //hbase config
    val hbaseConf = HBaseConfiguration.create()
    hbaseConf.set("hbase.zookeeper.quorum", zkString)
    val hbaseContext = new HBaseContext(sc, hbaseConf)
    val patientTable = "patientData"

    //setup a queue rdd to simulate streaming rdd
    val patientQueueRDD = scala.collection.mutable.Queue[RDD[Patient]]()
    val patientStream = ssc.queueStream(patientQueueRDD)

    //setup rules executor
    val rulesExecutor = new RulesExecutor(xlsFileName)

    //setup tsdbupdater
    val tsdbUpdater = new TSDBUpdater(url)

    // 1000 RDDs of random data
    for (batch <- 1 to 1000) {
      val randomPatients = PatientDataGenerator.getPatientList(batch)
      val rdd = ssc.sparkContext.parallelize(randomPatients)
      patientQueueRDD += rdd
    }

    //store incoming data in hbase
    hbaseContext.streamBulkPut[Patient](patientStream, patientTable, HBaseUtil.insertIncomingDataIntoHBase, true)

    //to-add filler logic to create snapshot

    //logic for each dstream
    patientStream.foreachRDD(rdd => {

      //evaluate all the rules for the batch
      val evaluatedPatients = rdd.mapPartitions(incomingEvents => { rulesExecutor.evalRules(incomingEvents) })

      //store the evaluation results in hbase
      hbaseContext.bulkPut[Patient](evaluatedPatients, patientTable, HBaseUtil.insertEvaluatedDataIntoHBase, true)

      //convert to dataframe
      val patientdf = sqc.applySchema(evaluatedPatients, classOf[Patient])

      //print batch details
      println("Total Patients in batch: " + patientdf.count())
      println("Patients with atleast one condition: " + patientdf.filter("sirsFlag > 0").count())

      //compute statistics and print them
      val countMatrix = patientdf.groupBy("location").agg(max("location"), max("evaluationDate"), sum("sirsFlag"), sum("sepsisFlag"), sum("severeSepsisFlag"), sum("septicShockFlag"), sum("organDysfunctionSyndrome"))
      countMatrix.show()

      //opentsdb update statistics
      countMatrix.foreach(row => { tsdbUpdater.loadPatientStats(row) })

    })

    ssc.start()
    ssc.awaitTermination()
  }
}