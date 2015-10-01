package com.cloudera.sprue

object PatientDataGenerator {
    val rn = scala.util.Random
    val locations = Seq("TX", "CA", "OK", "NY", "FL", "GA", "IL", "NE", "MN", "SC", "NM", "VA", "LA", "KS", "MS")
    
   def getPatientList (a : Int) : scala.collection.mutable.MutableList[Patient] = {
       val randomPatients = scala.collection.mutable.MutableList[Patient]()
      for (i <- 1 to rn.nextInt(250)) {

        val patient = new Patient("pikachu_" + i + "_machop" + a, System.currentTimeMillis, locations(rn.nextInt(locations.length)))

        if (i % 5 == 0) {
          patient.setTemperature(90 + rn.nextInt(14) * rn.nextFloat);
          patient.setHeartRate(20 + rn.nextInt(90));
          patient.setHypotension(rn.nextInt(2));
          patient.setInfectionFlag("Y");
          patient.setOrganFailCount(rn.nextInt(3));
          patient.setWbc(8000 + rn.nextInt(11000));
          patient.setRespiratoryRate(30 + rn.nextInt(20));
          patient.setSbp(rn.nextInt(100));
        } else if (i % 5 == 1) {
          patient.setTemperature(90 + rn.nextInt(14) * rn.nextFloat);
          patient.setHeartRate(20 + rn.nextInt(90));
          patient.setHypotension(rn.nextInt(2));
          patient.setInfectionFlag("N");
          patient.setOrganFailCount(rn.nextInt(4));
          patient.setWbc(3000 + rn.nextInt(11000));
          patient.setRespiratoryRate(10 + rn.nextInt(20));
          patient.setSbp(rn.nextInt(100));
        } else if (i % 5 == 2) {
          patient.setTemperature(90 + rn.nextInt(14) * rn.nextFloat);
          patient.setHeartRate(20 + rn.nextInt(90));
          patient.setHypotension(rn.nextInt(2));
          patient.setInfectionFlag("Y");
          patient.setOrganFailCount(rn.nextInt(3));
          patient.setWbc(3000 + rn.nextInt(11000));
          patient.setRespiratoryRate(10 + rn.nextInt(20));
          patient.setSbp(rn.nextInt(100));
        } else if (i % 5 == 3) {
          patient.setTemperature(90 + rn.nextInt(14) * rn.nextFloat);
          patient.setHeartRate(20 + rn.nextInt(90));
          patient.setHypotension(rn.nextInt(2));
          patient.setInfectionFlag("N");
          patient.setOrganFailCount(rn.nextInt(5));
          patient.setWbc(3000 + rn.nextInt(11000));
          patient.setRespiratoryRate(10 + rn.nextInt(20));
          patient.setSbp(rn.nextInt(100));
        } else if (i % 5 == 4) {
          patient.setTemperature(90 + rn.nextInt(14) * rn.nextFloat);
          patient.setHeartRate(20 + rn.nextInt(90));
          patient.setHypotension(rn.nextInt(2));
          patient.setInfectionFlag("Y");
          patient.setOrganFailCount(rn.nextInt(6));
          patient.setWbc(3000 + rn.nextInt(11000));
          patient.setRespiratoryRate(10 + rn.nextInt(20));
          patient.setSbp(rn.nextInt(100));
        }
        randomPatients += patient
      }
       randomPatients
    }
}