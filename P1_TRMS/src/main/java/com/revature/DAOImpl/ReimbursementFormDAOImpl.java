package com.revature.DAOImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.omg.CORBA.portable.InputStream;

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
	//supervisor Approval
	public void setSuppervisorApproval(int formID, int supervisorApproval,ServletContext sc)throws SQLException {
		Connection conn = cf.getConnection(sc);
		String sql = "call SET_SUPERVISOR_APPROVAL(?,?)";
		PreparedStatement ps;
			ps = conn.prepareStatement(sql);
			ps.setInt(1, supervisorApproval);
			ps.setInt(2, formID);
			ps.executeQuery();
			ps.close();
			conn.close();
	}
	//DEPARTMENT HEAD APPROVAL
	public void setDepartmentHeadApproval(int formID, int departmentHead,ServletContext sc)throws SQLException {
		Connection conn = cf.getConnection(sc);
		String sql = "call SET_DEPARTMENT_APPROVAL(?,?)";
		PreparedStatement ps;
			ps = conn.prepareStatement(sql);
			ps.setInt(1, departmentHead);
			ps.setInt(2, formID);
			ps.executeQuery();
			ps.close();
			conn.close();
	}
	//SET BENCO APPROVAL
	public void setBencoApproval(int formID, int bencoApproval,ServletContext sc)throws SQLException {
		Connection conn = cf.getConnection(sc);
		String sql = "call SET_BENCO_APPROVAL(?,?)";
		PreparedStatement ps;
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bencoApproval);
			ps.setInt(2, formID);
			ps.executeQuery();
			ps.close();
			conn.close();
	}
	//Upload file
	public void addAttachment(String name,String description, File filename, ServletContext sc)throws SQLException, IOException{
		Connection conn = cf.getConnection(sc);
		String sql = "INSERT INTO ATTACHMENT_TEST (NAMEN, DESCRIPTION_, FILEN) VALUES (?, ?, ?)";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, name);
	    stmt.setString(2, description);
	    FileInputStream   fis = new FileInputStream(filename);
	    stmt.setBinaryStream(3, fis, (int) filename.length());
	    stmt.execute();
	    conn.commit();
	    fis.close();
	    conn.close();
	}
	public File getAttachment(String name,ServletContext sc) throws SQLException, IOException {
		Connection conn = cf.getConnection(sc);
	
	      String query = "SELECT FILEN FROM ATTACHMENT WHERE NAMEN = ?";
	      PreparedStatement pstmt = conn.prepareStatement(query);
	      pstmt.setString(1, name);
	      ResultSet rs = pstmt.executeQuery();
	      rs.next();
	      Blob blob = rs.getBlob("FILEN");
	      java.io.InputStream in = blob.getBinaryStream();
	      File output = new File(name);
	      OutputStream out = new FileOutputStream(output);
	      byte[] buff = blob.getBytes(1,(int)blob.length());
	      out.write(buff);
	      out.close();
	      // materialize BLOB onto client
	      return output;
	}
	

}
