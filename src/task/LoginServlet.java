package task;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		HttpSession session = request.getSession(true); 
		String username = (String) session.getAttribute("email");
		if (username != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard");
			dispatcher.forward(request, response);
		} else {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(db_config.DB_URL, db_config.DB_USER,
						db_config.DB_PASSWORD);
				String query = "select * from users where email = ? AND password = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, email);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					// authentication successful
					conn.close();
					session.setAttribute("email", email);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard");
					dispatcher.forward(request, response);
				} else {
					// authentication failed
					conn.close();
					response.sendRedirect("login.jsp");
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
