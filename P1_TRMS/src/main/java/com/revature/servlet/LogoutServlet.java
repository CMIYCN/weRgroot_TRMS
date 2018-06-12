package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.actions.ManageActions;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String homeName = "home";
    private static ManageActions ma = new ManageActions();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet of LogoutServlet");
		ma.logout(request);
    	response.sendRedirect(homeName);
	}
}
