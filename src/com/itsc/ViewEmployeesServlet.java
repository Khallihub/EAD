package com.itsc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/view")
public class ViewEmployeesServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		DbConfig dbConfig = new DbConfig();
		response.setContentType("text/html");

		try (PrintWriter out = response.getWriter()) {
			Class.forName(dbConfig.driver);
			Connection connection = DriverManager.getConnection(dbConfig.url, dbConfig.username, dbConfig.password);
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

			out.print("<html><head><title>Employee List</title></head><body>");
			out.println("<h2>Employee List</h2>");
			out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Designation</th><th>Salary</th></tr>");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String designation = resultSet.getString("designation");
				int salary = resultSet.getInt("salary");

				out.println("<tr><td>" + id + "</td><td>" + name + "</td><td>" + designation + "</td><td>" + salary
						+ "</td></tr>");
			}

			out.println("</table></body></html>");

			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
