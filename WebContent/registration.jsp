<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Login</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
        box-sizing: border-box;
      }

      h2 {
        color: #333;
        text-align: center;
        margin-top: 50px;
      }

      form {
        width: 300px;
        margin: 0 auto;
        background-color: #fff;
        padding: 30px;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
      }

      input[type="text"],
      input[type="password"],
      input[type="submit"],
      form div {
        width: calc(100% - 20px);
        padding: 10px;
        margin-bottom: 10px;
        border-radius: 3px;
        border: 1px solid #ccc;
        box-sizing: border-box;
      }

      input[type="submit"],
      form div {
        background-color: #4caf50;
        color: #fff;
        cursor: pointer;
      }

      input[type="submit"]:hover,
      form div {
        background-color: #45a049;
      }

      .link-container {
        text-align: center;
      }

      a {
        text-decoration: none;
        color: #333;
      }
    </style>
  </head>
  <body>
    <header>
      <h1>Welcome to our Registration Page</h1>
    </header>
    <form action="RegistrationServlet" method="post">
      Name: <input type="text" name="name" />
      <br />
      Email: <input type="text" name="email" />
      <br />
      Password: <input type="password" name="password" /><br />

      <% String error = request.getParameter("error"); if (error != null) { if
      (error.equals("password_length")) { %>
      <p style="color: red">Password must be at least 8 characters long.</p>
      <% } else if (error.equals("uppercase_required")) { %>
      <p style="color: red">
        Password must contain at least one uppercase letter.
      </p>
      <% } else if (error.equals("lowercase_required")) { %>
      <p style="color: red">
        Password must contain at least one lowercase letter.
      </p>
      <% } else if (error.equals("digit_required")) { %>
      <p style="color: red">Password must contain at least one digit.</p>
      <% } else if (error.equals("special_character_required")) { %>
      <p style="color: red">
        Password must contain at least one special character.
      </p>
      <% } else if (error.equals("invalid_email")) { %>
      <p style="color: red">
        Invalid email format. Please enter a valid email.
      </p>
      <% } else if (error.equals("missing_fields")) { %>
      <p style="color: red">Please fill in all fields.</p>
      <% } else if (error.equals("duplicate_entry")) { %>
      <p style="color: red">
        Email already exists. Please change email or login
      </p>
      <% } } %>

      <input type="submit" value="Register" /> <br />

      <div class="link-container">
        <a href="login.jsp">Login</a>
      </div>
    </form>
  </body>
</html>
