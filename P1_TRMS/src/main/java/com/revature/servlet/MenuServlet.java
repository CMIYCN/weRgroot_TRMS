package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.actions.ManageActions;

public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String menuName = "employeehome.html";
	private static ManageActions ma = new ManageActions(); 
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet of MenuServlet");
		RequestDispatcher rd = request.getRequestDispatcher(menuName);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost of MenuServlet");
		//determine if correct user is logged in via session
		ma.menuOptionSelect(request, getServletContext(), response);
	}

}
