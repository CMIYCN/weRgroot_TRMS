package com.revature.DAOImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import com.revature.beans.Employee;
import com.revature.beans.ReimbursementForm;
import com.revature.dao.ReimbursementFormDAO;
import com.revature.util.ConnFactory;

public class ReimbursementFormDAOImpl implements ReimbursementFormDAO {
	public static ConnFactory cf = ConnFactory.getInstance();
	public List<ReimbursementForm> getReimbursementFormList(ServletContext sc) throws SQLException {
		List<ReimbursementForm> reimbursementList = new ArrayList<ReimbursementForm>();
		Connection conn= cf.getConnection(sc);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM REIMBURSEMENTFORM");
		ReimbursementForm rf;
		
		while(rs.next()) {
			rf = new ReimbursementForm(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getFloat(8),rs.getFloat(9),rs.getInt(10),rs.getInt(12),rs.getInt(13),rs.getInt(14));
			reimbursementList.add(rf);
		}
		rs.close();
		conn.close();
		return reimbursementList;
	}


	public void createReimbursementForm(int eventID,int empID,String eventTime,
			String eventDate,String location,String description,Float cost,Float projectedReimbursement,int urgent,
			int supervisorApproval,int departmentApproval, int bencoApproval, ServletContext sc) throws SQLException {
		Connection conn = cf.getConnection(sc);
		String sql = "{call CREATE_REIMBURSEMENT_FORM(?,?,?,?,?,?,?,?,?,?,?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, eventID);
		call.setInt(2, empID);
		call.setString(3, eventTime);
		call.setString(4, eventDate);
		call.setString(5, location);
		call.setString(6, description);
		call.setFloat(7, cost);
		call.setFloat(8, projectedReimbursement);
		call.setInt(9, urgent);
		call.setInt(10, supervisorApproval);
		call.setInt(11, departmentApproval);
		call.setInt(12, bencoApproval);
		call.execute();
		call.close();
		conn.close();

	}

	public List<ReimbursementForm> getReimbursementForm(int empID, ServletContext sc) throws SQLException {
		Connection conn = cf.getConnection(sc);
		String sql = "SELECT * FROM REIMBURSEMENT_FORM\n" + 
				"INNER JOIN APPROVALS" + 
				"ON REIMBURSEMENT_FORM.FORM_ID = APPROVALS.FORM_ID" + 
				"WHERE REIMBURSEMENT_FORM.EMP_ID=?";
		List<ReimbursementForm> reimbursementList = new ArrayList<ReimbursementForm>();
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, empID);
		ResultSet rs = ps.executeQuery();
		ReimbursementForm rf;
		
		while(rs.next()) {
			rf = new ReimbursementForm(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getFloat(8),rs.getFloat(9),rs.getInt(10),rs.getInt(12),rs.getInt(13),rs.getInt(14));
			reimbursementList.add(rf);
		}
		rs.close();
		conn.close();
		return reimbursementList;
		
	}

	public void updateApproval(int formID, int supervisorApproval, int departmentApproval, int bencoApproval,ServletContext sc)
			throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = cf.getConnection(sc);
		String sql = "call UPDATE_APPROVAL(?,?,?,?)";
		PreparedStatement ps;
			ps = conn.prepareStatement(sql);
			ps.setInt(1, supervisorApproval);
			ps.setInt(2, departmentApproval);
			ps.setInt(3, bencoApproval);
			ps.setInt(4, formID);
			ps.executeQuery();
			ps.close();
			conn.close();
	}
	

}
