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
      div {
        width: calc(100% - 20px);
        padding: 10px;
        margin-bottom: 10px;
        border-radius: 3px;
        border: 1px solid #ccc;
        box-sizing: border-box;
      }
      input[type="submit"],
      div {
        background-color: #4caf50;
        color: #fff;
        cursor: pointer;
      }
      input[type="submit"]:hover,
      div {
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
    <h2>Login</h2>
    <form action="LoginServlet" method="post">
      Email: <input type="text" name="email" /><br />
      Password: <input type="password" name="password" /><br />
      <input type="submit" value="Login" /> <br />
      <div class="link-container">
        <a href="registration.jsp">Register</a>
      </div>
    </form>
  </body>
</html>
