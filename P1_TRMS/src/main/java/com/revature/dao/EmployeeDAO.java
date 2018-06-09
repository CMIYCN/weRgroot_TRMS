package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Employee;

public interface EmployeeDAO {
	public abstract List<Employee> getEmployeeList() throws SQLException;
}
