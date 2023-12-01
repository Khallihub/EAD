<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Error Page</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 20px;
      }

      .error-container {
        max-width: 500px;
        margin: 0 auto;
        background-color: #fff;
        border-radius: 5px;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        text-align: center;
      }

      h1 {
        color: #333;
      }

      p {
        color: #555;
      }
    </style>
  </head>
  <body>
    <div class="error-container">
      <h1>Error</h1>
      <p>
        <% String errorMessage = (String)request.getAttribute("errorMessage");
        String ept = ""; %> <%= errorMessage != null ? errorMessage : "An error occurred." %>
      </p>
      <p>
        <% String link = (String)request.getAttribute("link"); %>
        <a href="<%= link != null ? link :  ept %>">Login</a>
      </p>
    </div>
  </body>
</html>
