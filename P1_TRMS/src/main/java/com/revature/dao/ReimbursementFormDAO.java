package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;

import com.revature.beans.ReimbursementForm;;

public interface ReimbursementFormDAO {
	public abstract List<ReimbursementForm> getReimbursementFormList(ServletContext sc) throws SQLException;
	public abstract void createReimbursementForm(int formID,int eventID,int empID,String eventTime,String eventDate,String location,String description,Float cost,Float projectedReimbursement,int urgent, ServletContext sc);
	public abstract List<ReimbursementForm> getReimbursementForm(int empID, ServletContext sc)throws SQLException;
}
