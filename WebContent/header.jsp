<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
    />

    <meta charset="UTF-8" />
    <title>Header</title>
    <style>
      /* Add your CSS styles for the header here */
      body {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: Arial, sans-serif;
      }

      .header {
        background-color: #333;
        color: #fff;
        padding: 10px;
        overflow: hidden;
      }

      .header-content {
        display: flex;
        justify-content: space-between;
        align-items: center;
      }

      .header-right {
        display: flex;
        align-items: center;
      }

      .add-task a {
        padding: 8px;
        border-radius: 3px;
        background-color: #4caf50;
        color: #fff;
        text-decoration: none;
      }

      .add-task a:hover {
        background-color: #45a049;
      }

      .search {
        height: min-content;
      }

      .search input[type="text"],
      .search button {
        padding: 8px;
        border-radius: 3px;
        border: none;
        margin-right: 5px;
      }

      .search input[type="text"] {
        width: 200px;
      }

      .search button {
        background-color: #4caf50;
        color: #fff;
        cursor: pointer;
      }

      .search button:hover {
        background-color: #45a049;
      }

      .account a {
        color: #fff;
        text-decoration: none;
        margin-left: 10px;
      }

      .account-icon {
        font-size: 12px;
        color: #336699;
        border: 2px solid #336699;
        border-radius: 50%;
        padding: 10px;
        cursor: pointer;
      }
    </style>
  </head>
  <body>
    <div class="header">
      <div class="header-content">
        <div class="add-task">
          <a href="addTask.jsp">Add Task</a>
        </div>
        <div class="header-right">
          <form action="Search" method="post" class="search">
            <input type="text" placeholder="Search..." name="input" />
            <button type="submit">Search</button>
          </form>
          <div class="account">
            <form action="account" method="post">
              <input type="hidden" name="action" value="view" />
              <button type="submit">
                <i class="fas fa-user account-icon"></i>
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
