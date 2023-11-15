package com.itsc;

import java.sql.*;

public class StatementWithResultSet {
	public static void main(String[] args) throws SQLException {
		String username = "root";
		String pwd = "";
		String databaseName = "teachersdb";
		String jdbcURL = "jdbc:mysql://localhost:3306/" + databaseName;

		Connection connection = DriverManager.getConnection(jdbcURL, username, pwd);
		Statement statement = connection.createStatement();

//		String query = "Select * from teachersdb";
		System.out.print("here:");
//		String createTableSQL = "CREATE TABLE teacher1 (" + "id INT AUTO_INCREMENT PRIMARY KEY,"
//				+ "first_name VARCHAR(255)," + "last_name VARCHAR(255)," + "school VARCHAR(255)," + "hire_date DATE,"
//				+ "salary DECIMAL(10, 2)" + ")";
//		statement.executeUpdate(createTableSQL);
//		System.out.println("Ttable ‘teachers’ created successfully.");
//
		String[] insertStatements = {

				"insert into teacher1(first_name, last_name, school, hire_date, salary) values('Aster', 'Nega', 'Yekatit 12', '2021-01-01', 8000)",
				"insert into teacher1(first_name, last_name, school, hire_date, salary) values('Jemal', 'Edris', 'Bole', '2021-09-11', 20000)",
				"insert into teacher1(first_name, last_name, school, hire_date, salary) values('Haile', 'Anaol', 'Saris', '2022-01-22', 15000)",
				"insert into teacher1(first_name, last_name, school, hire_date, salary) values('Teddy', 'Anbesaw', 'Bole', '2021-01-01', 8000)" };
		for (String stmt : insertStatements) {
			statement.executeUpdate(stmt);
		}
		System.out.println("Data Inserted Successfullly.");

		String query = "Select * from teacher1";
		ResultSet res = statement.executeQuery(query);
		while (res.next()) {
			int id = res.getInt("id");
			String fname = res.getString("first_name");
			String lname = res.getString("last_name");
			String school = res.getString("school");
			Date hire_date = res.getDate("hire_date");
			double salary = res.getDouble("salary");
			System.out.println("Teacher ID: " + id);
			System.out.println("Teacher Name: " + fname);
			System.out.println("Teacher Hire Date: " + hire_date);
			System.out.println("Teacher Salary: " + salary);
			System.out.println("...............");
		}
		res.close();
		statement.close();
		connection.close();
	}
}