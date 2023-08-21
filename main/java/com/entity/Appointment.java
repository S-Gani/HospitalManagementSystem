package com.entity;

public class Appointment {
	private String userId;
	private String appoint_date;
	private String problem;
	private int status;
	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Appointment(String userId, String appoint_date, String problem, int status) {
		super();
		this.userId = userId;
//		System.out.println("------"+appoint_date);
		this.appoint_date = appoint_date;
		this.problem = problem;
		this.status = status;
		
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getappoint_date() {
//		System.out.println("<<<----"+appoint_date);
		return appoint_date;
	}

	public void setappoint_date(String appoinDate) {
//		System.out.println("---->"+appoinDate);
		this.appoint_date = appoint_date;
	}
	public String getproblem() {
		return problem;
	}

	public void setproblem(String problem) {
		this.problem = problem;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
