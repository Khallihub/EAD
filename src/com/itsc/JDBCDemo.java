package com.itsc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCDemo {

	public static void main(String[] args) {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/StudentsDB";
			String username = "root"; // your username
			String password = ""; // your password
			Class.forName(driver); // optional
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Established Connection");
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}

}
