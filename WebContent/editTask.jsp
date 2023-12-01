<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Add Task</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
      }

      .container {
        max-width: 1200px;
        margin: 0 auto;
        padding: 20px;
      }

      .form-container {
        max-width: 400px;
        margin: 20px auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
      }

      h2 {
        text-align: center;
        color: #333;
      }

      .form-container label {
        display: block;
        margin-bottom: 8px;
        color: #333;
      }

      .form-container input[type="text"],
      .form-container input[type="date"],
      .form-container select,
      .form-container textarea {
        width: calc(100% - 10px);
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 3px;
      }

      .form-container textarea {
        resize: vertical;
      }

      .form-container input[type="submit"] {
        background-color: #4caf50;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 3px;
        cursor: pointer;
      }

      .form-container input[type="submit"]:hover {
        background-color: #45a049;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <%@ include file="header.jsp"%> <% int id =
      Integer.parseInt(request.getAttribute("id").toString()); String title =
      (String) request.getAttribute("title"); String description = (String)
      request.getAttribute("description"); String dueDate = (String)
      request.getAttribute("dueDate"); %>
      <h2>Add Task</h2>
      <div class="form-container">
        <form action="EditTask" method="post">
          <input type="hidden" name="id" value="<%=id%>" />
          <input type="hidden" name="type" value="change" />
          <label for="title">Title:</label>
          <input type="text" id="title" name="title" value="<%=title%>"  required/>
          <label for="description">Description:</label>
          <textarea id="description" name="description" rows="4" cols="50">
                <%=description%>
                </textarea
          >

          <label for="dueDate" >Due Date:</label>
          <input type="date" id="dueDate" name="dueDate" value="<%=dueDate%>" required/>
          <label for="priority">Priority:</label>
          <select id="priority" name="priority">
            <option value="1">1 - High</option>
            <option value="2">2 - Medium</option>
            <option value="3">3 - Low</option>
          </select>
          <input type="submit" value="Save changes" />
        </form>
      </div>
    </div>
  </body>
</html>
