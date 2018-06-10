package com.revature.actions;
import javax.servlet.http.HttpServletRequest;

import com.revature.DAOImpl.EmployeeDAOImpl;
import com.revature.beans.Employee;

public class ManageActions {
	private static String user = "username";
	private static String pass = "password";
	private static String fname = "firstName";
	private static String lname = "lastName";
	EmployeeDAOImpl edi = new EmployeeDAOImpl();
	public boolean register(HttpServletRequest request) {
		String username = request.getParameter(user);
		String password = request.getParameter(pass);
		String firstName = request.getParameter(fname);
		String lastName = request.getParameter(lname);
		
		//check is usernameExists, register new employee account
		if (edi.getEmployeeByUsername(username) == null) {
			//createEmployee also needs int employeetype and int manager id or some information that 
			//would allow to find that out
			edi.createEmployee(username, password, firstName, lastName);
			return true;
		}
		return false;
	}
	
	public boolean login(HttpServletRequest request) {
		String username = request.getParameter(user);
		String password = request.getParameter(pass);
		
		Employee emp = edi.getEmployeeByUsername(username);
		if (emp.getPassword().equals(password)) {
			//save session/set session to current username 
			return true;
		}
		return false;
	}
}
