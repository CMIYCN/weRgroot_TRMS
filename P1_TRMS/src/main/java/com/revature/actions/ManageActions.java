package com.revature.actions;
import javax.servlet.http.HttpServletRequest;

import com.revature.DAOImpl.EmployeeDAOImpl;
import com.revature.beans.Employee;

public class ManageActions {
	private static String user = "username";
	private static String pass = "password";
	private static String fname = "firstName";
	private static String lname = "lastName";

	public boolean register(HttpServletRequest request) {
		String username = request.getParameter(user);
		String password = request.getParameter(pass);
		String firstName = request.getParameter(fname);
		String lastName = request.getParameter(lname);
		
		//check is usernameExists, register new employee account
		if (EmployeeDAOImpl.getEmployeeByUsername(username) == null) {
			EmployeeDAOImpl.createEmployee(username, password, firstName, lastName);
			return true;
		}
		return false;
	}
	
	public boolean login(HttpServletRequest request) {
		String username = request.getParameter(user);
		String password = request.getParameter(pass);
		
		Employee emp = EmployeeDAOImpl.getEmployeeByUsername(username);
		if (emp.getPassword().equals(password)) {
			//save session/set session to current username 
			return true;
		}
		return false;
	}
}
