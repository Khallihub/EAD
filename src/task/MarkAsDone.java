package task;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MarkAsDone
 */
@WebServlet("/MarkAsDone")
public class MarkAsDone extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MarkAsDone() {
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
		int id = Integer.parseInt(request.getParameter("taskId"));
		String status = request.getParameter("status");
		System.out.print(status);
		int updatedStatus = 1;
		if (status.equals("Completed")) {
			updatedStatus = 0;
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(db_config.DB_URL, db_config.DB_USER, db_config.DB_PASSWORD);
			String updateQuery = "UPDATE tasks SET status = ? WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, updatedStatus);
			pstmt.setInt(2, id);
			int rowsUpdated = pstmt.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Task status updated successfully!");
			} else {
				System.out.println("No task found with the given ID.");
			}

			conn.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginServlet");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
