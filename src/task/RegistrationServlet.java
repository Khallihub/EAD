package task;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationServlet extends HttpServlet {

	protected void doPost(HttpServletRequest rq, HttpServletResponse rp) throws IOException {
// Retrieve form data
		String name = rq.getParameter("name");
		String email = rq.getParameter("email");
		String password = rq.getParameter("password");

		if (name == null || name.isEmpty() || email == null || email.isEmpty() || password == null
				|| password.isEmpty()) {
			rp.sendRedirect("registration.jsp?error=missing_fields");
			return;
		}

		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(emailRegex);
		if (!pattern.matcher(email).matches()) {
			rp.sendRedirect("registration.jsp?error=invalid_email");
			return;
		}

		if (password.length() < 8) {
			rp.sendRedirect("registration.jsp?error=password_length");
			return;
		} else if (!password.matches(".*[A-Z].*")) {
			rp.sendRedirect("registration.jsp?error=uppercase_required");
			return;
		} else if (!password.matches(".*[a-z].*")) {
			rp.sendRedirect("registration.jsp?error=lowercase_required");
			return;
		} else if (!password.matches(".*\\d.*")) {
			rp.sendRedirect("registration.jsp?error=digit_required");
			return;
		} else if (!password.matches(".*[!@#$%^&*()].*")) {
			rp.sendRedirect("registration.jsp?error=special_character_required");
			return;
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.print("1");
			Connection conn = DriverManager.getConnection(db_config.DB_URL, db_config.DB_USER, db_config.DB_PASSWORD);
			System.out.print("2");

			String query = "insert into users (name, email, password) values(?,?,?)";
			System.out.print("3");

			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, email.toLowerCase());
			pstmt.setString(3, password);

			pstmt.executeUpdate();
			conn.close();
			rp.sendRedirect("confirmation.jsp");
		}
		catch (SQLIntegrityConstraintViolationException e) {
		    e.printStackTrace();
		    rp.sendRedirect("registration.jsp?error=duplicate_entry");
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
}