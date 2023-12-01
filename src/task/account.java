package task;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class account
 */
@WebServlet("/account")
public class account extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public account() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String action = request.getParameter("action");
		if (email != null) {
			if (action.equals("view")) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(db_config.DB_URL, db_config.DB_USER,
							db_config.DB_PASSWORD);
					String query = "SELECT * FROM users WHERE email = ?";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setString(1, email);
					ResultSet rs = pstmt.executeQuery();

					if (rs.next()) {
						String name = rs.getNString("name");
						String password = rs.getString("password");
						request.setAttribute("name", name);
						request.setAttribute("email", email);
						request.setAttribute("password", password);
					} else {
						System.out.print("user not found");
					}

					conn.close();
					request.getRequestDispatcher("/account.jsp").forward(request, response);

				}

				catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			} else if (action.equals("edit")) {
				try {
					String name = request.getParameter("name");
					String newEmail = request.getParameter("email");
					String password = request.getParameter("password");
					
					if (name == null || name.isEmpty() || email == null || email.isEmpty() || password == null
							|| password.isEmpty()) {
						response.sendRedirect("account.jsp?error=missing_fields");
						return;
					}

					String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
					Pattern pattern = Pattern.compile(emailRegex);
					if (!pattern.matcher(email).matches()) {
						response.sendRedirect("account.jsp?error=invalid_email");
						return;
					}

					if (password.length() < 8) {
						response.sendRedirect("account.jsp?error=password_length");
						return;
					} else if (!password.matches(".*[A-Z].*")) {
						response.sendRedirect("account.jsp?error=uppercase_required");
						return;
					} else if (!password.matches(".*[a-z].*")) {
						response.sendRedirect("account.jsp?error=lowercase_required");
						return;
					} else if (!password.matches(".*\\d.*")) {
						response.sendRedirect("account.jsp?error=digit_required");
						return;
					} else if (!password.matches(".*[!@#$%^&*()].*")) {
						response.sendRedirect("account.jsp?error=special_character_required");
						return;
					}
					
					
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(db_config.DB_URL, db_config.DB_USER,
							db_config.DB_PASSWORD);
					String updateQuery = "UPDATE users SET name = ?, email = ?, password = ? WHERE email = ?";
					PreparedStatement pstmt = conn.prepareStatement(updateQuery);
					pstmt.setString(1, name);
					pstmt.setString(2, newEmail);
					pstmt.setString(3, password);
					pstmt.setString(4, email);
					int rowsUpdated = pstmt.executeUpdate();
					if (rowsUpdated > 0) {
						System.out.println("User profile updated successfully!");
						session.setAttribute("email", null);
					} else {
						System.out.println("No user found with the given ID.");
					}
					conn.close();
					request.getRequestDispatcher("/login.jsp").forward(request, response);

				}
				catch (SQLIntegrityConstraintViolationException e) {
				    e.printStackTrace();
				    response.sendRedirect("account.jsp?error=duplicate_entry");
				}

				catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			String errorMessage = "UnAuthorized! Please login first to view and edit your profile";
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("link", "login.jsp");
			
			request.getRequestDispatcher("/error.jsp").forward(request, response);

		}

	}

}
