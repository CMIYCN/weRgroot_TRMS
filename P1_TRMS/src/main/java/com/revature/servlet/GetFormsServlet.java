package com.revature.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.actions.ManageActions;

public class GetFormsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String regName = "register";
	private static ManageActions ma = new ManageActions();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet of GetFormsServlet");
		//we have message in request
		Gson gson = new Gson();
		try {
			response.getWriter().write(gson.toJson(ma.getStringReimbursementForms(getServletContext())));
			response.flushBuffer();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//go to register page with error
//		RequestDispatcher rd = request.getRequestDispatcher(regName);
//		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
