package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String view = "view";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//process approvals from form
		//send to another servlet to retrieve form details for display
	
		RequestDispatcher rd = request.getRequestDispatcher("view");
		System.out.println("doPost GetFormServlet");
		rd.forward(request, response);
	}

}
