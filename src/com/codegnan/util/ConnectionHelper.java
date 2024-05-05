package com.codegnan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/jap_5";
		String username = "root";
		String password = "root";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, username, password);
		con.setAutoCommit(false);
		return con;
	}
	private ConnectionHelper() {
		// Nothing to execute here.
	}
}
