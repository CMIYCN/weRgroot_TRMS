package com.revature.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.DAOImpl.EmployeeDAOImpl;
import com.revature.beans.Employee;

public class GetCurUserPosTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeDAOImpl edi = new EmployeeDAOImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee emp = edi.getEmployeeByUsername(request.getSession(false).getAttribute("username").toString(), getServletContext());
		Gson gson = new Gson();

		response.getWriter().write(gson.toJson(emp));
		response.flushBuffer();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
