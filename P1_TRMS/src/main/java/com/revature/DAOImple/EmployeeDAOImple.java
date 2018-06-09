package com.revature.DAOImple;

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
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
