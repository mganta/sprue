package com.cloudera.sprue;

public class Patient implements java.io.Serializable {

	//observed readings
	private long evaluationDate;
	private String patientId;
	private String location;
	private float temperature;
	private int wbc;
	private int heartRate;
	private int respiratoryRate;
	private int sbp;
	private int hypotension;
	private String infectionFlag;
	private int organFailCount;
	
	//derived values
	private int sirsCounter;
	private int sirsFlag;
	private int sepsisFlag;
	private int severeSepsisFlag;
	private int septicShockFlag;
	private int organDysfunctionSyndrome;
	
	//internal evaluation flags
	private int evalHRFlag;
	private int evalRespFlag;
	private int evalWbcFlag;
	private int evalSepsisFlag;
	private int evalSirsFlag;
	private int evalSevereSepsisFlag;
	private int evalSepticShockFlag;
	private int evalOrganDyscFlag;
	private int evalTempFlag;
	
	public Patient(String patientId, long evaluationDate, String location) {
		this.evaluationDate = evaluationDate;
		this.patientId = patientId;
		this.location = location;
		this.sirsCounter = 0;
		this.sirsFlag = 0;
		this.sepsisFlag = 0;
		this.severeSepsisFlag = 0;
		this.septicShockFlag = 0;
		this.organDysfunctionSyndrome = 0;
		this.evalHRFlag = 0;
		this.evalRespFlag = 0;
		this.evalWbcFlag =0;
		this.evalSepsisFlag = 0;
		this.evalSevereSepsisFlag = 0;
		this.evalSepticShockFlag = 0;
		this.evalOrganDyscFlag = 0;
		this.evalSirsFlag = 0;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getEvalTempFlag() {
		return evalTempFlag;
	}


	public void setEvalTempFlag(int evalTempFlag) {
		this.evalTempFlag = evalTempFlag;
	}


	public int getEvalHRFlag() {
		return evalHRFlag;
	}


	public void setEvalHRFlag(int evalHRFlag) {
		this.evalHRFlag = evalHRFlag;
	}


	public int getEvalRespFlag() {
		return evalRespFlag;
	}


	public void setEvalRespFlag(int evalRespFlag) {
		this.evalRespFlag = evalRespFlag;
	}


	public int getEvalWbcFlag() {
		return evalWbcFlag;
	}


	public void setEvalWbcFlag(int evalWbcFlag) {
		this.evalWbcFlag = evalWbcFlag;
	}


	public int getEvalSepsisFlag() {
		return evalSepsisFlag;
	}


	public void setEvalSepsisFlag(int evalSepsisFlag) {
		this.evalSepsisFlag = evalSepsisFlag;
	}


	public int getEvalSevereSepsisFlag() {
		return evalSevereSepsisFlag;
	}


	public void setEvalSevereSepsisFlag(int evalSevereSepsisFlag) {
		this.evalSevereSepsisFlag = evalSevereSepsisFlag;
	}


	public int getEvalSepticShockFlag() {
		return evalSepticShockFlag;
	}


	public void setEvalSepticShockFlag(int evalSepticShockFlag) {
		this.evalSepticShockFlag = evalSepticShockFlag;
	}


	public int getEvalOrganDyscFlag() {
		return evalOrganDyscFlag;
	}


	public void setEvalOrganDyscFlag(int evalOrganDyscFlag) {
		this.evalOrganDyscFlag = evalOrganDyscFlag;
	}



	
	

	
	@Override
	public String toString() {
		return patientId + " " + evaluationDate + " --temp-- " + temperature + " --wbc-- " + wbc + " --heartRate-- " + heartRate +
				" --respRate-- " + respiratoryRate + " --sirscount-- " + sirsCounter +
				" --srcofInf-- " + infectionFlag ; 
	}


	public long getEvaluationDate() {
		return evaluationDate;
	}


	public void setEvaluationDate(long evaluationDate) {
		this.evaluationDate = evaluationDate;
	}


	public String getPatientId() {
		return patientId;
	}


	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public float getTemperature() {
		return temperature;
	}


	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}


	public int getWbc() {
		return wbc;
	}


	public void setWbc(int wbc) {
		this.wbc = wbc;
	}


	public int getHeartRate() {
		return heartRate;
	}


	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}


	public int getRespiratoryRate() {
		return respiratoryRate;
	}


	public void setRespiratoryRate(int respiratoryRate) {
		this.respiratoryRate = respiratoryRate;
	}


	public int getSbp() {
		return sbp;
	}


	public void setSbp(int sbp) {
		this.sbp = sbp;
	}


	public int getHypotension() {
		return hypotension;
	}


	public void setHypotension(int hypotension) {
		this.hypotension = hypotension;
	}


	public String getInfectionFlag() {
		return infectionFlag;
	}


	public void setInfectionFlag(String infectionFlag) {
		this.infectionFlag = infectionFlag;
	}


	public int getOrganFailCount() {
		return organFailCount;
	}


	public void setOrganFailCount(int organFailCount) {
		this.organFailCount = organFailCount;
	}


	public int getSirsCounter() {
		return sirsCounter;
	}


	public void setSirsCounter(int sirsCounter) {
		this.sirsCounter = sirsCounter;
	}

	public void incrementSirsCounter(int incrementVal) {
		this.sirsCounter = this.sirsCounter + incrementVal;
	}

	public int getSirsFlag() {
		return sirsFlag;
	}


	public void setSirsFlag(int sirsFlag) {
		this.sirsFlag = sirsFlag;
	}


	public int getSepsisFlag() {
		return sepsisFlag;
	}


	public void setSepsisFlag(int sepsisFlag) {
		this.sepsisFlag = sepsisFlag;
	}


	public int getEvalSirsFlag() {
		return evalSirsFlag;
	}


	public void setEvalSirsFlag(int evalSirsFlag) {
		this.evalSirsFlag = evalSirsFlag;
	}


	public int getSevereSepsisFlag() {
		return severeSepsisFlag;
	}


	public void setSevereSepsisFlag(int severeSepsisFlag) {
		this.severeSepsisFlag = severeSepsisFlag;
	}


	public int getSepticShockFlag() {
		return septicShockFlag;
	}


	public void setSepticShockFlag(int septicShockFlag) {
		this.septicShockFlag = septicShockFlag;
	}


	public int getOrganDysfunctionSyndrome() {
		return organDysfunctionSyndrome;
	}


	public void setOrganDysfunctionSyndrome(int organDysfunctionSyndrome) {
		this.organDysfunctionSyndrome = organDysfunctionSyndrome;
	}

	
}
