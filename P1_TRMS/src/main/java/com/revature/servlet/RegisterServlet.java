package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.actions.ManageActions;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String homeName = "home";
	private static final String regName = "register.html";
	private static ManageActions ma = new ManageActions(); 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet of RegisterServlet");
		RequestDispatcher rd = request.getRequestDispatcher(regName);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost of RegisterServlet");
		//attempt to register new user sending values
		boolean success = ma.register(request, getServletContext());
		//always redirect to home, success can be used to display an error if failure
		response.sendRedirect(homeName);

	}
}