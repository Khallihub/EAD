package task;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
 * Servlet implementation class EditTask
 */
@WebServlet("/EditTask")
public class EditTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditTask() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String dueDateString = request.getParameter("dueDate");
		String action = request.getParameter("type");
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		if (email != null) {
			if (action.equals("forward")) {
				request.setAttribute("id", id);
				request.setAttribute("title", title);
				request.setAttribute("description", description);
				request.setAttribute("dueDate", dueDateString);
				request.getRequestDispatcher("/editTask.jsp").forward(request, response);
			} else {
				String d = request.getParameter("priority");
				System.out.print(d);
				int priority = Integer.parseInt(request.getParameter("priority"));
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

					String updateQuery = "UPDATE tasks SET title=?, description=?, dueDate=?, priority=? WHERE id=?";
					PreparedStatement pstmt = conn.prepareStatement(updateQuery);
					pstmt.setString(1, title);
					pstmt.setString(2, description);
					pstmt.setDate(3, dueDate);
					pstmt.setInt(4, priority);
					pstmt.setInt(5, id);

					int rowsAffected = pstmt.executeUpdate();

					if (rowsAffected > 0) {
						System.out.println("Task with ID " + id + " updated successfully!");
					} else {
						System.out.println("No task found with the ID " + id);
					}

					conn.close();
					RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginServlet");
					dispatcher.forward(request, response);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			String errorMessage = "UnAuthorized! Please login first to edit task";
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("link", "login.jsp");

			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}

	}

}
