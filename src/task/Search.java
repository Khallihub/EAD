package task;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() {
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
		String searchTerm = request.getParameter("input");
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		if (email != null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(db_config.DB_URL, db_config.DB_USER,
						db_config.DB_PASSWORD);

				String query = "SELECT * FROM tasks WHERE email = ? AND (title LIKE ? OR dueDate LIKE ?)";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, email);
				pstmt.setString(2, "%" + searchTerm + "%");
				pstmt.setString(3, "%" + searchTerm + "%");

				ResultSet rs = pstmt.executeQuery();

				List<Task> searchedTasks = new ArrayList<>();
				while (rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String description = rs.getString("description");
					Date dueDate = rs.getDate("dueDate");
					int priority = rs.getInt("priority");
					int status = rs.getInt("status");
					String priorityText = "High";
					if (priority == 2) {
						priorityText = "Medium";
					} else if (priority == 3) {
						priorityText = "Low";
					}
					String statusText = "Not completed";
					if (status == 1) {
						statusText = "Completed";
					}
					Task task = new Task(id, title, description, dueDate, priorityText, statusText);
					searchedTasks.add(task);
				}

				conn.close();
				if (searchedTasks.isEmpty()) {
					request.setAttribute("nomatches", "true");
				}
				request.setAttribute("page", "Search Results");
				request.setAttribute("tasks", searchedTasks);
				request.getRequestDispatcher("/welcome.jsp").forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else {
			String errorMessage = "UnAuthorized! Please login first to search tasks";
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("link", "login.jsp");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

}
