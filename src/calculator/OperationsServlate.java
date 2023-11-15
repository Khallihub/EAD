package calculator;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dispatcher")

public class OperationsServlate extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");

		if (operation.equals("+")) {
			// Forward the request to AddServlet
			RequestDispatcher addDispatcher = request.getRequestDispatcher("/addNums");
			try {
				addDispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else if (operation.equals("*")) {
			// Forward the request to MultiplyServlet
			RequestDispatcher multiplyDispatcher = request.getRequestDispatcher("/multiply");
			try {
				multiplyDispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else {
			// Handle unsupported operation
			// For example, display an error message or redirect back to the form
		}
	}

}
