package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MenuServlet
 */
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String menuName = "menu.html";
	private static final String homeName = "index.html";
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet of MenuServlet");
		RequestDispatcher rd = request.getRequestDispatcher(menuName);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//goto a existing reimbursement form
			//response.sendRedirect(reimbursementName);
		//create a new reimbursement form
			//response.sendRedirect(reimbursementName);
		//logout
			response.sendRedirect(homeName);
	}

}
