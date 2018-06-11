package com.revature.DAOImpl;

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
			rf = new ReimbursementForm(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getFloat(8),rs.getFloat(9),rs.getInt(10));
			reimbursementList.add(rf);
		}
		rs.close();
		conn.close();
		return reimbursementList;
	}

	public void createReimbursementForm(int eventID,int empID,String eventTime,
			String eventDate,String location,String description,Float cost,Float projectedReimbursement,int urgent, ServletContext sc) {
		Connection conn = cf.getConnection(sc);
		String[] primaryKeys = new String[1];
		primaryKeys[0]="FormId";//whatever formID series is
		String sql = "INSERT INTO REIMBURSEMENT_FORM VALUES(FormID.NEXTVAL,?,?,?,?,?)";
		try {
		PreparedStatement ps= conn.prepareStatement(sql, primaryKeys);
		//ps.setInt(1, formID);
		ps.setInt(1, eventID);
		ps.setInt(2, empID);
		ps.setString(3, eventTime);
		ps.setString(4, eventDate);
		ps.setString(5, location);
		ps.setString(6, description);
		ps.setFloat(7, cost);
		ps.setFloat(8, projectedReimbursement);
		ps.setInt(9, urgent);
		ps.executeUpdate();
		ps.close();
		conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}

	}

	public List<ReimbursementForm> getReimbursementForm(int empID, ServletContext sc) throws SQLException {
		Connection conn = cf.getConnection(sc);
		String sql = "SELECT * FROM REIMBURSEMENT_FORM WHERE EMP_ID = ?";
		List<ReimbursementForm> reimbursementList = new ArrayList<ReimbursementForm>();
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, empID);
		ResultSet rs = ps.executeQuery();
		ReimbursementForm rf;
		
		while(rs.next()) {
			rf = new ReimbursementForm(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getFloat(8),rs.getFloat(9),rs.getInt(10));
			reimbursementList.add(rf);
		}
		rs.close();
		conn.close();
		return reimbursementList;
		
	}

}
