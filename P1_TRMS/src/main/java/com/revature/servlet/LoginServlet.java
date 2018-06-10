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

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String loginName = "login.html";
	private static final String menuName = "menu";
	private static ManageActions ma = new ManageActions(); 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet of LoginServlet");
		RequestDispatcher rd = request.getRequestDispatcher(loginName);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost of LoginServlet");
		//attempt to login sending form fields
		boolean success = ma.login(request, getServletContext());
		if (success) {
			response.sendRedirect(menuName);
		}
		//send error back and have user retry
		
	}

}
