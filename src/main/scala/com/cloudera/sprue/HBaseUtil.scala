package com.cloudera.sprue

import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.util.Bytes

object HBaseUtil {
  val columnFamily: String = "cf1"

  def insertIncomingDataIntoHBase(patient: Patient): Put = {
    if (patient.getPatientId == null) {
      return null
    } else {
      val put = new Put(Bytes.toBytes(patient.getPatientId))
      if (patient.getPatientId != null)
        put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("patientId"), Bytes.toBytes(patient.getPatientId))
      if (patient.getLocation != null)
        put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("location"), Bytes.toBytes(patient.getLocation))
      if ((patient.getEvaluationDate : java.lang.Long) != null)
        put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("evaluationDate"), Bytes.toBytes(patient.getEvaluationDate))
      if ((patient.getTemperature  : java.lang.Float) != null)
        put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("temperature"), Bytes.toBytes(patient.getTemperature))
      if ((patient.getWbc  : java.lang.Integer) != null)
        put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("wbc"), Bytes.toBytes(patient.getWbc))

      if ((patient.getHeartRate  : java.lang.Integer) != null)
        put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("heartRate"), Bytes.toBytes(patient.getHeartRate))
      if ((patient.getRespiratoryRate  : java.lang.Integer) != null)
        put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("respiratoryRate"), Bytes.toBytes(patient.getRespiratoryRate))
      if ((patient.getSbp  : java.lang.Integer) != null)
        put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("sbp"), Bytes.toBytes(patient.getSbp))
      if ((patient.getHypotension  : java.lang.Integer) != null)
        put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("hypotension"), Bytes.toBytes(patient.getHypotension))
      if (patient.getInfectionFlag != null)
        put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("infectionFlag"), Bytes.toBytes(patient.getInfectionFlag))
      if ((patient.getOrganFailCount  : java.lang.Integer) != null)
        put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("organFailCount"), Bytes.toBytes(patient.getOrganFailCount))

      put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("evalFinished"), Bytes.toBytes("N"))
      put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("recordUpdatedTime"), Bytes.toBytes(System.currentTimeMillis))

    }
  }

  def insertEvaluatedDataIntoHBase(patient: Patient): Put = {
    if (patient.getPatientId == null) {
      return null
    } else {
      val put = new Put(Bytes.toBytes(patient.getPatientId))
      put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("evalFinished"), Bytes.toBytes("Y"))
      put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("sirsCounter"), Bytes.toBytes(patient.getSirsCounter))
      put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("sirsFlag"), Bytes.toBytes(patient.getSirsFlag))
      put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("sepsisFlag"), Bytes.toBytes(patient.getSepsisFlag))
      put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("severeSepsisFlag"), Bytes.toBytes(patient.getSevereSepsisFlag))
      put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("septicShockFlag"), Bytes.toBytes(patient.getSepticShockFlag))
      put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("organDysfunctionFlag"), Bytes.toBytes(patient.getOrganDysfunctionSyndrome))
      put.add(Bytes.toBytes(columnFamily), Bytes.toBytes("systemEvalTime"), Bytes.toBytes(System.currentTimeMillis))
      put
    }
  }
}