package com.revature.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.actions.ManageActions;

public class ViewSpecificReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String viewName = "reimbursementTable.html";
	private static final String menuName = "menu";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet of viewServlet");

		response.sendRedirect(menuName);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost viewServlet");
		RequestDispatcher rd = request.getRequestDispatcher(viewName);
		
		rd.forward(request, response);
	}
}
