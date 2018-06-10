package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;

import com.revature.beans.Employee;

public interface EmployeeDAO {
	public abstract List<Employee> getEmployeeList(ServletContext sc) throws SQLException;
	public abstract void createEmployee(String userName,String password, String name, int positionType, int managerID, ServletContext sc)throws SQLException;
	public abstract Employee getEmployeeByUsername(String username, ServletContext sc)throws SQLException;
	public abstract void updateEmployee(String oldUserName,String newUserName,String password, String name, int positionType, int managerID, ServletContext sc)throws SQLException;
	public abstract void deleteEmployee(String username, ServletContext sc) throws SQLException;
	public abstract String getEmployeeUsernameAndPassword(String username, ServletContext sc)throws SQLException;
}
