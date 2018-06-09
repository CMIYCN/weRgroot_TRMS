package com.revature.beans;

public class ReimbursementForm {
	private int form_ID;
	private int event_ID;
	private int emp_ID;
	private String time;
	private String date;
	private String location;
	private String description;
	private float cost;
	private float projectedReimbursement;
	private int urgent;
	
	public ReimbursementForm(int form_ID, int event_ID, int emp_ID, String time, String date, String location,
			String description, float cost, float projectedReimbursement, int urgent) {
		super();
		this.form_ID = form_ID;
		this.event_ID = event_ID;
		this.emp_ID = emp_ID;
		this.time = time;
		this.date = date;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.projectedReimbursement = projectedReimbursement;
		this.urgent = urgent;
	}
	public int getForm_ID() {
		return form_ID;
	}
	public void setForm_ID(int form_ID) {
		this.form_ID = form_ID;
	}
	public int getEvent_ID() {
		return event_ID;
	}
	public void setEvent_ID(int event_ID) {
		this.event_ID = event_ID;
	}
	public int getEmp_ID() {
		return emp_ID;
	}
	public void setEmp_ID(int emp_ID) {
		this.emp_ID = emp_ID;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public float getProjectedReimbursement() {
		return projectedReimbursement;
	}
	public void setProjectedReimbursement(float projectedReimbursement) {
		this.projectedReimbursement = projectedReimbursement;
	}
	public int getUrgent() {
		return urgent;
	}
	public void setUrgent(int urgent) {
		this.urgent = urgent;
	}
	
	
}
