package com.revature.P1TRMSTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.revature.DAOImpl.EmployeeDAOImpl;

class EmployeeDAOTest {
	private static String userName = "bobby22";
	private static String password = "22";
	private static String name = "Bobby";
	private static int positionType = 1;
	private static int managerID = 1;
	private static final EmployeeDAOImpl edi = new EmployeeDAOImpl();
	@Test
	void testCreateEmployee() throws SQLException {
		int oldsize = edi.getEmployeeList().size();
		//Insert Employee
		edi.createEmployee(userName, password, name, positionType, managerID);
		int newsize = edi.getEmployeeList().size();
		
		//Assert whether database list has increased
		assertTrue(newsize>oldsize);
		
		//Delete Employee
		edi.deleteEmployee(userName);
	}

}
