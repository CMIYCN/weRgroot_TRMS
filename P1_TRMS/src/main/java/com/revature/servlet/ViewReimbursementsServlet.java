package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.actions.ManageActions;

public class ViewReimbursementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String viewReimbursementsName = "viewreimbursements.html";
	private static final String homeName = "home";
	private static ManageActions ma = new ManageActions();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet of viewReimbursementsServlet");

		if (ma.sessionActive(request)) {
			RequestDispatcher rd = request.getRequestDispatcher(viewReimbursementsName);
			rd.forward(request, response);
		} else {
			response.sendRedirect(homeName);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		//some actions based on table of reimbursements
	}
}
