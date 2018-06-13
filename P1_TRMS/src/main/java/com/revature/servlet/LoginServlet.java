package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.actions.ManageActions;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String loginName = "login.html";
	private static final String menuName = "menu";
	private static final String homeName = "home";
	private static final String managerName = "manager";
	private static ManageActions ma = new ManageActions(); 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet of LoginServlet");
		RequestDispatcher rd = request.getRequestDispatcher(loginName);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost of LoginServlet");
		//attempt to login sending form fields
		int type = ma.login(request, getServletContext());
		if (type == 0) {
			response.sendRedirect(menuName);
		} else if (type == 1) {
			//direct supervisor page
			response.sendRedirect(managerName);
		} else if (type == 2) {
			//department head page
			response.sendRedirect(managerName);
		} else if (type == 3) {
			//benco page
			response.sendRedirect(managerName);
		} else if (type == 4) {
			//ceo page
			response.sendRedirect(managerName);
		} else
			response.sendRedirect(homeName);
		
	}

}
