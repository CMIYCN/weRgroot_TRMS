package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.ReimbursementForm;;

public interface ReimbursementFormDAO {
	public abstract List<ReimbursementForm> getEmployeeList() throws SQLException;
	public abstract void createReimbursementForm(int formID,int eventID,int empID,String eventTime,String eventDate,String location,String description,Float cost,Float projectedReimbursement,int urgent);

}
