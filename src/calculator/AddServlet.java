package calculator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addNums")

public class AddServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));

		int result = num1 + num2;

		request.setAttribute("result", result);
		PrintWriter out = response.getWriter();
		out.print("	<form action=\"dispatcher\" method=\"post\">\r\n"
				+ "		<label>num1</label> <input name=\"num1\"/> \r\n"
				+ "		<label>num2</label> <input name=\"num2\"/>\r\n" + "		<label>operation</label>'\r\n"
				+ "		 <select name=\"operation\">\r\n" + "            <option value=\"+\">+</option>\r\n"
				+ "            <option value=\"*\">*</option>\r\n" + "        </select>\r\n"
				+ "        <button type=\"submit\">Calculate</button>");
		out.print("<p>The result is</p>");
		out.print("<p>" + result + "</p>");

	}
}
