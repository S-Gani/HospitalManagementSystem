package com.entity;


public class UserAppointment {
    private String DocId,appointmentDate,problem,status,DocName,Phone,specilazationString;

	public UserAppointment(String docId, String appointmentDate, String problem, String status, String docName,
			String phone, String specilazationString) {
		super();
		DocId = docId;
		this.appointmentDate = appointmentDate;
		this.problem = problem;
		this.status = status;
		DocName = docName;
		Phone = phone;
		this.specilazationString = specilazationString;
	}

	public String getDocId() {
		return DocId;
	}

	public void setDocId(String docId) {
		DocId = docId;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDocName() {
		return DocName;
	}

	public void setDocName(String docName) {
		DocName = docName;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getSpecilazationString() {
		return specilazationString;
	}

	public void setSpecilazationString(String specilazationString) {
		this.specilazationString = specilazationString;
	}
	@Override
	public String toString() {
		return "UserAppointment [DocId=" + DocId + ", appointmentDate=" + appointmentDate + ", problem=" + problem
				+ ", status=" + status + ", DocName=" + DocName + ", Phone=" + Phone + ", specilazationString="
				+ specilazationString + "]";
	}

}
