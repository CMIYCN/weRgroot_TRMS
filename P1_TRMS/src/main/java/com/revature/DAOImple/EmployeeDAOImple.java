package com.revature.DAOImple;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeDAO;
import com.revature.util.ConnFactory;

public class EmployeeDAOImple implements EmployeeDAO {
	public static ConnFactory cf = ConnFactory.getInstance();
	
	public List<Employee> getEmployeeList() throws SQLException {
		List<Employee> employeeList = new ArrayList<Employee>();
		Connection conn= cf.getConnection();
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
	public void createEmployee(String userName,String password, String name, int positionType, int managerID) throws SQLException {
		Connection conn = cf.getConnection();
		String[] primaryKeys = new String[1];
		primaryKeys[0]="EmployeeId";//whatever employeeID series is
		String sql = "INSERT INTO EMPLOYEE VALUES(EmployeeID.NEXTVAL,?,?,?,?,?)";
		try {
		PreparedStatement ps= conn.prepareStatement(sql, primaryKeys);
		ps.setString(1, userName);
		ps.setString(2, password);
		ps.setString(3, name);
		ps.setInt(4, positionType);
		ps.setInt(5, managerID);
		ps.executeUpdate();
		ps.close();
		conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	//working on stored procedure for this.
	public Employee getEmployeeByUsername(String username) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE.USERNAME=?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		Employee e = new Employee(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getString(6));
		rs.close();
		ps.close();
		conn.close();
		return e;
	}
	public void updateEmployee(String oldUserName, String newUserName, String password, String name, int positionType,
			int managerID) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = cf.getConnection();
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
	public void deleteEmployee(String username) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{call DELETE_EMPLOYEE(?)";
		
		CallableStatement call = conn.prepareCall(sql);
		call.setString(1, username);
		call.execute();
		conn.close();
		
	}
	public void getEmployeeUsernameAndPassword(String username) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
