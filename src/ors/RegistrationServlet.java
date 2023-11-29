package ors;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationServlet extends HttpServlet {


	protected void doPost(HttpServletRequest rq, HttpServletResponse rp) throws IOException {
// Retrieve form data
		String name = rq.getParameter("name");
		String email = rq.getParameter("email");
		String password = rq.getParameter("password");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.print("1");
			Connection conn = DriverManager.getConnection(db_config.DB_URL, db_config.DB_USER, db_config.DB_PASSWORD);
			System.out.print("2");

			String query = "insert into users (name, email, password) values(?,?,?)";
			System.out.print("3");

			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, password);
			
			pstmt.executeUpdate();
			conn.close();
			rp.sendRedirect("confirmation.jsp");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
}