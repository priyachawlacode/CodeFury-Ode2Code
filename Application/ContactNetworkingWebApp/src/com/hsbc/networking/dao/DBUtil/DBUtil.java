package com.hsbc.networking.dao.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static Connection conn;

	public static Connection getDBConnection() throws SQLException {
		try {
			if (conn == null) {
				String url = "jdbc:mysql://localhost:3306/contactPool?allowPublicKeyRetrieval=true&useSSL=false";
				String username = "root";
				String password = "root";
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
				conn = DriverManager.getConnection(url, username, password);
			}
		} catch (SQLException e) {
			throw e;
		}
		return conn;
	}

	public static void closeDBConnection() throws SQLException {

		try {
			conn.close();
		} catch (SQLException e) {
			throw e;
		}

	}
}
