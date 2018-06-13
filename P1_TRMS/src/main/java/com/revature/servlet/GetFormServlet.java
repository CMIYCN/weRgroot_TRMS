package com.revature.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.actions.ManageActions;

public class GetFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String view = "view";
	private static final String viewName = "view-reimbursements";
	private static ManageActions ma = new ManageActions(); 
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//process approvals from form
		//send to another servlet to retrieve form details for display
		String appbtn = request.getParameter("approve-btn");
		String denybtn = request.getParameter("deny-btn");
		String formID = request.getParameter("formID");
		//if approve btn
		try {
			if (appbtn != null) {
				ma.approve(getServletContext(), request, Integer.parseInt(formID));
				//go to getForms servlet
				response.sendRedirect(viewName);
			} else if (denybtn != null) {
				ma.deny(getServletContext(), request, Integer.parseInt(formID));
				//go to getForms servlet
				response.sendRedirect(viewName);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("view");
				System.out.println("doPost GetFormServlet");
				rd.forward(request, response);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
