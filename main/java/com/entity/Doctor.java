package com.entity;

//update tempDoc set docId= concat('INR',CAST(123 AS VARCHAR(10))) where id=100
public class Doctor {
	private int is_delete;
	private String DocId, Last_updated_date , Last_update_by  , Specialization , docPhone, docEmail,DocName,Password;
	
	
	public Doctor() {
		super();
	}
	public int getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}
	public String getDocId() {
		return DocId;
	}
	public void setDocId(String docId) {
		DocId = docId;
	}
	public String getLast_updated_date() {
		return Last_updated_date;
	}
	public void setLast_updated_date(String last_updated_date) {
		Last_updated_date = last_updated_date;
	}
	public String getLast_update_by() {
		return Last_update_by;
	}
	public void setLast_update_by(String last_update_by) {
		Last_update_by = last_update_by;
	}
	public String getSpecialization() {
		return Specialization;
	}
	public void setSpecialization(String specialization) {
		Specialization = specialization;
	}
	public String getDocPhone() {
		return docPhone;
	}
	public void setDocPhone(String docPhone) {
		this.docPhone = docPhone;
	}
	public String getDocEmail() {
		return docEmail;
	}
	public void setDocEmail(String docEmail) {
		this.docEmail = docEmail;
	}
	public String getDocName() {
		return DocName;
	}
	public void setDocName(String docName) {
		DocName = docName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	@Override
	public String toString() {
		return "Doctor [is_delete=" + is_delete + ", DocId=" + DocId + ", Last_updated_date=" + Last_updated_date
				+ ", Last_update_by=" + Last_update_by + ", Specialization=" + Specialization + ", docPhone=" + docPhone
				+ ", docEmail=" + docEmail + ", DocName=" + DocName + ", Password=" + Password + "]";
	}
	
}
