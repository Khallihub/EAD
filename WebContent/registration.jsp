<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Registration Form</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h2>Registration Form</h2>
	<form action="RegistrationServlet" method="post">
		Name: <input type="text" name="name"> 
		<br> 
		Email: <input
			type="text" name="email"> 
		<br> 
		Password: <input
			type="password" name="password">
			<br> 
		<input
			type="submit" value="Register">
	</form>
	<%!// Declaration - User class definition%>
	<%
		final class User {
		private String name;
		private String email;
		private String password;
		// Constructors, getters, and setters
	}
	%>
</body>
</html>