package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewReimbursementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String viewReimbursementsName = "viewreimbursements.html";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet of viewReimbursementsServlet");
		RequestDispatcher rd = request.getRequestDispatcher(viewReimbursementsName);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//some actions based on table of reimbursements
	}
}
