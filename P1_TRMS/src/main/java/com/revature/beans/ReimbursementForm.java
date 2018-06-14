package com.revature.beans;

public class ReimbursementForm {
	private int formID;
	private int empID;
	private String eventTime;
	private String eventDate;
	private String location;
	private String description;
	private Float cost;
	private float projectedReimbursement;
	private int urgent;
	private int eventType;
	private int supervisorApproval;
	private int departmentApproval;
	private int bencoApproval;
	public ReimbursementForm(int formID, int empID, String eventTime, String eventDate, String location,
			String description, Float cost, float projectedReimbursement, int urgent,
			int eventType, int supervisorApproval, int departmentApproval, int bencoApproval) {
		super();
		this.formID = formID;
		this.empID = empID;
		this.eventTime = eventTime;
		this.eventDate = eventDate;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.projectedReimbursement = projectedReimbursement;
		this.urgent = urgent;
		this.eventType = eventType;
		this.supervisorApproval = supervisorApproval;
		this.departmentApproval = departmentApproval;
		this.bencoApproval = bencoApproval;
		
	}
	public int getFormID() {
		return formID;
	}
	public void setFormID(int formID) {
		this.formID = formID;
	}
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
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
	public Float getCost() {
		return cost;
	}
	public void setCost(Float cost) {
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
	public int getEventType() {
		return eventType;
	}
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
	public int getSupervisorApproval() {
		return supervisorApproval;
	}
	public void setSupervisorApproval(int supervisorApproval) {
		this.supervisorApproval = supervisorApproval;
	}
	public int getDepartmentApproval() {
		return departmentApproval;
	}
	public void setDepartmentApproval(int departmentApproval) {
		this.departmentApproval = departmentApproval;
	}
	public int getBencoApproval() {
		return bencoApproval;
	}
	public void setBencoApproval(int bencoApproval) {
		this.bencoApproval = bencoApproval;
	}
	
}
