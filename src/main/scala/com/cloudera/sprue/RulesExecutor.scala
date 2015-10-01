package com.cloudera.sprue

/*
 * Class to execute the rules
 */
class RulesExecutor (xlsFileName : String)  extends Serializable {
 
  //evaluate all the rules and send the result back to 
  def evalRules (incomingEvents : Iterator[Patient]) : Iterator[Patient] = {
        val ksession = KieSessionFactory.getKieSession(xlsFileName)
        val patients = incomingEvents.map(patient => {
          ksession.execute(patient)
          patient
        })
        patients
  }
}