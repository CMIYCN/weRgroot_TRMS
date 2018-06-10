package com.revature.actions;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.DAOImpl.EmployeeDAOImpl;
import com.revature.beans.Employee;

public class ManageActions {
	private static String user = "username";
	private static String pass = "password";
	private static String fullName = "name";
	EmployeeDAOImpl edi = new EmployeeDAOImpl();
	private static final String reimbursementName = "reimbursement-form";
	private static final String mainName = "home";
	
	public boolean register(HttpServletRequest request, ServletContext sc) {
		String username = request.getParameter(user);
		String password = request.getParameter(pass);
		String name = request.getParameter(fullName);
		
		//check is usernameExists, register new employee account
		//if (edi.getEmployeeByUsername(username) == null) {
			//createEmployee also needs int employeetype and int manager id or some information that 
			//would allow to find that out
			try {
				edi.createEmployee(username, password, name, 0, 0, sc);
				return true;
			} catch (SQLException e) {
				
			}
		//}
		return false;
	}
	
	public boolean login(HttpServletRequest request, ServletContext sc) {
		String username = request.getParameter(user);
		String password = request.getParameter(pass);
		
		Employee emp = edi.getEmployeeByUsername(username, sc);
		if (emp.getPassword().equals(password)) {
			HttpSession session = request.getSession();  
		    session.setAttribute("username", username); 
		    session.setAttribute("password", password);
			return true;
		}
		return false;
	}
	
	public void menuOptionSelect(HttpServletRequest request, ServletContext sc, HttpServletResponse response) {
		String btnNew = request.getParameter("newreimbursement-button");
		String btnView = request.getParameter("viewreimbursement-button");
		
		try {
			if (btnNew != null)
				response.sendRedirect(reimbursementName);
			else
				response.sendRedirect(mainName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//        HttpSession session = request.getSession(false);  
//        String n = (String)session.getAttribute("username"); 
//        System.out.println();
	}
}
