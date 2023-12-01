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
 * Servlet implementation class tasks
 */
@WebServlet("/dashboard")
public class tasks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tasks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        if (email != null) {
        	try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(db_config.DB_URL, db_config.DB_USER, db_config.DB_PASSWORD);

                String query = "SELECT * FROM tasks WHERE email = ? ORDER BY dueDate";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, email);

                ResultSet rs = pstmt.executeQuery();
                
                List<Task> tasks = new ArrayList<>();
                while (rs.next()) {
                	int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    Date dueDate = rs.getDate("dueDate");
                    int priority = rs.getInt("priority");
                    int status = rs.getInt("status");
                    String priorityText = "High";
                    String statusText = "Not completed";
                    if (priority == 2) {
                    	priorityText = "Medium";
                    }
                    else if (priority == 3) {
                    	priorityText = "Low";
                    }
                    if (status == 1) {
                    	statusText = "Completed";
                    }
                    
                    Task task = new Task(id,title, description, dueDate, priorityText, statusText);
                    tasks.add(task);
                }
                System.out.print(tasks);

                conn.close();
                request.setAttribute("page", "Task List");
                request.setAttribute("tasks", tasks);
                request.getRequestDispatcher("/welcome.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        } else {
        	String errorMessage = "UnAuthorized! Please login first to view tasks";
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("link", "login.jsp");

			request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
