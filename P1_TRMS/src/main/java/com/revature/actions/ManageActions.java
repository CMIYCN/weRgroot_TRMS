package com.revature.actions;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.DAOImpl.EmployeeDAOImpl;
import com.revature.DAOImpl.ReimbursementFormDAOImpl;
import com.revature.beans.Employee;
import com.revature.beans.ReimbursementForm;

public class ManageActions {
	private static String user = "username";
	private static String pass = "password";
	private static String fullName = "name";
	EmployeeDAOImpl edi = new EmployeeDAOImpl();
	ReimbursementFormDAOImpl rfai = new ReimbursementFormDAOImpl();
	private static final String reimbursementName = "reimbursement-form";
	private static final String mainName = "home";
	private static final String viewName = "view-reimbursements";
	
	//new employee can register
	public boolean register(HttpServletRequest request, ServletContext sc) {
		String username = request.getParameter(user);
		String password = request.getParameter(pass);
		String name = request.getParameter(fullName);
		Employee emp = edi.getEmployeeByUsername(username, sc);
		
		//check is usernameExists, register new employee account
		if (emp == null) {
			//createEmployee also needs int employeetype and int manager id or some information that 
			//would allow to find that out
			try {
				edi.createEmployee(username, password, name, 0, 0, sc);
				return true;
			} catch (SQLException e) {
				
			}
		}
		return false;
	}
	
	public int login(HttpServletRequest request, ServletContext sc) {
		String username = request.getParameter(user);
		String password = request.getParameter(pass);
		
		Employee emp = edi.getEmployeeByUsername(username, sc);
		if (emp.getPassword().equals(password)) {
			//set session to current logged in user
			HttpSession session = request.getSession();  
		    session.setAttribute("username", username); 
		    session.setAttribute("password", password);
		    //set type to session or return it
			return emp.getPositionID();
		}
		return -1;
	}
	
	//menus are displayed based on who is logged in
	//if it's an employee, it displays employee menu
	//if it's a manager, it displays manager menu
	public void menuOptionSelect(HttpServletRequest request, ServletContext sc, HttpServletResponse response) {
		String btnNew = request.getParameter("newreimbursement-button");
		String btnView = request.getParameter("viewreimbursement-button");
		
		try {
			if (btnNew != null)
				response.sendRedirect(reimbursementName);
			else if (btnView != null) 
				response.sendRedirect(viewName);
			else
				response.sendRedirect(mainName);
		} catch (IOException e) {
			
		}
	}

	public void fileReimbursement(HttpServletRequest request, ServletContext servletContext) {
		//get all parameters from reimbursement form
		//submit parameters to database
		String eventTime = request.getParameter("eventtime");
		String eventDate = request.getParameter("eventdate");
		String location = request.getParameter("location");
		String description = request.getParameter("description");
		int eventType = Integer.parseInt(request.getParameter("event-type"));
		Float cost = Float.parseFloat(request.getParameter("cost"));
		
		//get session user id
		try {
			Employee emp = edi.getEmployeeByUsername(request.getSession(false).getAttribute("username").toString(), servletContext);
			int id = emp.getEmpID();
			rfai.createReimbursementForm(
					id, eventTime, eventDate, 
					location, description, cost, 
					0F, 0, eventType, 0, 0, 0, servletContext
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//logout: once an employee or a manager clicks the "Sign Out" button
	//it calls the logout method and invalidates the session
	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession();  
        session.invalidate();
	}

	public boolean sessionActive(HttpServletRequest request) {
		HttpSession session = request.getSession(false);  

		if (session != null){  
        	return true;
        }  
        else{  
            return false;
        }  
	}
	
	//lists all the reimbursement form to be approved or deny
	public List<ReimbursementForm> getStringReimbursementForms(ServletContext sc, HttpServletRequest request) throws SQLException{
		Employee emp = edi.getEmployeeByUsername(request.getSession(false).getAttribute("username").toString(), sc);
		List<ReimbursementForm> reimburseForms;
		
		if (emp.getPositionID() == 0)
			reimburseForms = rfai.getReimbursementForm(emp.getEmpID(), sc);
		else
			reimburseForms = rfai.getReimbursementFormList(sc);

		return reimburseForms;
	}
	
	public ReimbursementForm getAReimbursementForm(int formID,ServletContext sc) throws SQLException{
		ReimbursementForm rf = rfai.getForm(formID, sc);
		return rf;
	}

	//approve: When a manager approves a reimbursement request,
	//the approve method is called and the request is forwarded to the
	//next highest ranked manager. All three managers have to approve every request
	public void approve(ServletContext sc, HttpServletRequest request, int formID) throws SQLException {
		Employee emp = edi.getEmployeeByUsername(request.getSession(false).getAttribute("username").toString(), sc);
		
		switch (emp.getPositionID()) {
			case 1:
				//direct
				rfai.supervisorApproval(formID, sc);
				break;
			case 2:
				//depart
				rfai.departmentApproval(formID, sc);
				break;
			case 3:
				//benco
				rfai.bencoApproval(formID, sc);
				break;
			case 4:
				//ceo
				break;
		}
	}

	//deny: The request goes through the direct supervisor and then to the next level manager
	//if first level manager denies it, the form doesn't go to the next level
	public void deny(ServletContext sc, HttpServletRequest request, int formID) throws SQLException {
		Employee emp = edi.getEmployeeByUsername(request.getSession(false).getAttribute("username").toString(), sc);
		
		switch (emp.getPositionID()) {
			case 1:
				//direct
				rfai.supervisorDENIAL(formID, "Because I said so.", sc);
				break;
			case 2:
				//depart
				rfai.departmentDenial(formID, "Because I said so.", sc);
				break;
			case 3:
				//benco
				System.out.println("benco deny");
				rfai.bencoDenial(formID, "Because I said so.", sc);
				break;
			case 4:
				//ceo
				break;
		}
	}
}
