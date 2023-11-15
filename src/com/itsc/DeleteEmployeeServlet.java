package com.itsc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleteEmployeeServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response){	
		DbConfig dbConfig = new DbConfig();
		
		try {
			Class.forName(dbConfig.driver);
			Connection connection = DriverManager.getConnection(dbConfig.url, dbConfig.username, dbConfig.password);
	        Statement statement = connection.createStatement();
	        int id = Integer.parseInt(request.getParameter("id"));

	        String deleteQuery = String.format("DELETE FROM employees WHERE id = %d", id);
	        
	        statement.executeUpdate(deleteQuery);
	        statement.close();
	        connection.close();
	        	        
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		
	}

	
}
