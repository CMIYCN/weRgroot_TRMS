package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
	public abstract List<Employee> getEmployeeList() throws SQLException;
}
