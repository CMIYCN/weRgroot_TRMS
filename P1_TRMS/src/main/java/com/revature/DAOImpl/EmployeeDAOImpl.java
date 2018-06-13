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
import com.revature.dao.EmployeeDAO;
import com.revature.util.ConnFactory;

public class EmployeeDAOImpl implements EmployeeDAO {
	public static ConnFactory cf = ConnFactory.getInstance();
	
	public List<Employee> getEmployeeList(ServletContext sc) throws SQLException {
		List<Employee> employeeList = new ArrayList<Employee>();
		Connection conn= cf.getConnection(sc);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEE");
		Employee e;
		
		while(rs.next()) {
			e = new Employee(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getString(6));
			employeeList.add(e);
		}
		rs.close();
		conn.close();
		return employeeList;
	}
	public void createEmployee(String userName,String password, String name, int positionType, int managerID, ServletContext sc) throws SQLException {
		Connection conn = cf.getConnection(sc);
		String sql = "{call INSERT_EMPLOYEE(?,?,?,?,?)";
		
		CallableStatement call = conn.prepareCall(sql);
		call.setString(1, userName);
		call.setString(2, password);
		call.setString(3, name);
		call.setInt(4, positionType);
		call.setInt(5, managerID);
		call.execute();
		call.close();
		conn.close();
	}
	//working on stored procedure for this.
	public Employee getEmployeeByUsername(String username, ServletContext sc) {
		Connection conn = cf.getConnection(sc);
		String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE.USERNAME=?";
		Employee e = null;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			e = new Employee(
					rs.getInt(1),
					rs.getString(4),
					rs.getInt(5),
					rs.getInt(6),
					rs.getString(2),
					rs.getString(3));
			rs.close();
			ps.close();
			conn.close();
			return e;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return e;
	}
	public void updateEmployee(String oldUserName, String newUserName, String password, String name, int positionType,
			int managerID, ServletContext sc) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = cf.getConnection(sc);
		String sql = "{call UPDATE_EMPLOYEE(?,?,?,?,?,?)";
		
		CallableStatement call = conn.prepareCall(sql);
		call.setString(1, oldUserName);
		call.setString(2, newUserName);
		call.setString(3, password);
		call.setString(4, name);
		call.setInt(5, positionType);
		call.setInt(6, managerID);
		call.execute();
		conn.close();
	}
	public void deleteEmployee(String username, ServletContext sc) throws SQLException {
		Connection conn = cf.getConnection(sc);
		String sql = "{call DELETE_EMPLOYEE(?)";
		
		CallableStatement call = conn.prepareCall(sql);
		call.setString(1, username);
		call.execute();
		conn.close();
		
	}
	public String getEmployeeUsernameAndPassword(String username, ServletContext sc) throws SQLException {
		Connection conn = cf.getConnection(sc);
		String sql = "{call GET_PASSWORD(?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setString(1, username);
		call.registerOutParameter(2, java.sql.Types.VARCHAR);
		call.execute();
		String password = call.getString(2);
		call.close();
		conn.close();
		return password;
		
	}
	///returns the reimbursement that employee can still recieve
	public Float getReimbursementLeft(int empID,ServletContext sc)throws SQLException{
		Connection conn = cf.getConnection(sc);
		String sql = "{call REIMBURSEMENT_LEFT(?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, empID);
		call.registerOutParameter(2, java.sql.Types.FLOAT);
		call.execute();
		Float reimbursementLeft = call.getFloat(2);
		call.close();
		conn.close();
		return reimbursementLeft;
	}
	//enters a passing grade for the employee grade
	public void enterPassingGrade(int formID,ServletContext sc) throws SQLException {
		Connection conn = cf.getConnection(sc);
		String sql = "{call ENTER_PASS(?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, formID);
		call.execute();
		call.close();
		conn.close();
	}
	//ENTER FAILING GRADE FOR EMPLOYEE GRADE
	public void enterFailingGrade(int formID,ServletContext sc) throws SQLException {
		Connection conn = cf.getConnection(sc);
		String sql = "{call ENTER_FAIL(?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, formID);
		call.execute();
		call.close();
		conn.close();
	}
}
