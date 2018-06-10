package com.revature.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;

public class ConnFactory {
	private static ConnFactory cf = new ConnFactory();
	
	private ConnFactory() {
		super();
	}
	
	public static synchronized ConnFactory getInstance() {
		if(cf==null) {
			cf= new ConnFactory(); 
		}
		return cf;
	}
	/*
	 * Name: getConnection()
	 * Input:None
	 * Output:Connection
	 * Description: Creates a connection to server
	 */
	public Connection getConnection(ServletContext sc) {
		Connection conn = null;
		//getConnection(url, user, password)
		try {
			InputStream input = sc.getResourceAsStream("/WEB-INF/database.properties");
			Properties prop = new Properties();
<<<<<<< HEAD
			prop.load(input);
			
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("after class");
=======
			prop.load(new FileReader("database.properties"));
			Class.forName(prop.getProperty("driver"));
>>>>>>> f6b38196b854336d5d5a7881976dce62bd3de3ae
			conn = DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("usr"), 
					prop.getProperty("password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return conn;
	}
}
