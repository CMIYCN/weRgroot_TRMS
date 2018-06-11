package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.actions.ManageActions;


public class ReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String formName = "reimbursementform.html";
	private static final String menuName = "menu";
	private static ManageActions ma = new ManageActions();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet of MenuServlet");
		RequestDispatcher rd = request.getRequestDispatcher(formName);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//send to fileReimbursement form
		ma.fileReimbursement(request, getServletContext());
		response.sendRedirect(menuName);
	}

}
