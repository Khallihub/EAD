package com.itsc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddEmployeeServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		DbConfig dbConfig = new DbConfig();
		try {
			Class.forName(dbConfig.driver);
			Connection connection = DriverManager.getConnection(dbConfig.url, dbConfig.username, dbConfig.password);
			Statement statement = connection.createStatement();

//					statement.executeUpdate("CREATE DATABASE "+ dbConfig.database);
//					statement.executeUpdate("USE " + dbConfig.database);
//					
//					String createTableString = "CREATE TABLE employees(" + 
//										"id int auto_increment primary key," + 
//										"name varchar(255)," +
//										"designation varchar(255)," +
//										"salary int)";
//					statement.executeUpdate(createTableString);

			String name = request.getParameter("name");
			String designation = request.getParameter("designation");
			int salary = Integer.parseInt(request.getParameter("salary"));

			String insertStatements = String.format(
					"INSERT INTO employees (name, designation, salary) VALUES ('%s', '%s', %d)", name, designation,
					salary);

			statement.executeUpdate(insertStatements);

			statement.close();
			connection.close();

			response.sendRedirect("viewEmployee.html");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
