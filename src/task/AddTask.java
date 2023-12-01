package task;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddTask
 */
@WebServlet("/AddTask")
public class AddTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddTask() {
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
		// TODO Auto-generated method stub
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String dueDateString = request.getParameter("dueDate");
		int priority = Integer.parseInt(request.getParameter("priority"));
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		System.out.print(email);
		if (email != null) {
			java.sql.Date dueDate = null;
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date parsedDate = dateFormat.parse(dueDateString);
				dueDate = new java.sql.Date(parsedDate.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(db_config.DB_URL, db_config.DB_USER,
						db_config.DB_PASSWORD);

				String query = "INSERT INTO tasks (email, title, description, dueDate, priority, status) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, email);
				pstmt.setString(2, title);
				pstmt.setString(3, description);
				pstmt.setDate(4, dueDate);
				pstmt.setInt(5, priority);
				pstmt.setInt(6, 0); 

				pstmt.executeUpdate();
				conn.close();
				RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginServlet");
				dispatcher.forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			String errorMessage = "UnAuthorized! Please login first to add task";
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("link", "login.jsp");
			
			request.getRequestDispatcher("/error.jsp").forward(request, response);

		}

	}
}
