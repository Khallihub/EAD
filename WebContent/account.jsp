<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>User Profile</title>
    <style>
      /* Base styles */
      body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 20px;
      }

      h1 {
        color: #333;
        text-align: center;
      }

      /* Profile info container */
      .profile-info {
        background-color: #fff;
        border-radius: 5px;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        margin: 0 auto;
        max-width: 400px;
      }

      /* Edit button styles */
      .edit-button label {
        display: block;
        text-align: center;
        padding: 8px 15px;
        border: none;
        background-color: #336699;
        color: #fff;
        border-radius: 3px;
        cursor: pointer;
        transition: background-color 0.3s ease;
      }

      .edit-button label:hover {
        background-color: #4caf50;
      }

      /* Hide the edit form by default */
      .edit-form {
        display: none;
        background-color: #fff;
        border-radius: 5px;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        margin: 0 auto;
        max-width: 400px;
      }

      /* Edit form input styles */
      .edit-form input[type="text"],
      .edit-form input[type="password"] {
        width: calc(100% - 12px);
        padding: 8px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 3px;
      }

      #edit-toggle {
        display: none;
      }

      /* Show edit form when checkbox is checked */
      #edit-toggle:checked ~ .profile-info .edit-form {
        display: block;
      }

      /* Hide the profile info when checkbox is checked */
      #edit-toggle:checked ~ .profile-info .edit-button label {
        display: none;
      }

      .cancel {
        font-size: 0.9em;
      }

      .btns {
        display: flex;
        align-items: center;
        gap: 1em;
      }

      .btns * {
        padding: 10px 15px;
        border: none;
        background-color: #336699;
        color: #fff;
        border-radius: 3px;
        cursor: pointer;
        transition: background-color 0.3s ease;
      }

      .btns *:hover {
        background-color: #4caf50;
      }
    </style>
  </head>
  <body>
    <h1>User Profile</h1>
    <% String name = (String) request.getAttribute("name"); String email =
    (String) request.getAttribute("email"); String password = (String)
    request.getAttribute("password"); %>
    <input type="checkbox" id="edit-toggle" />
    <div class="profile-info">
      <% if (email != null) { %>
      <p>Current Name: <%=name%></p>
      <p>Current Email: <%=email%></p>
      <p>Current Password: <%=password%></p>
      <div class="edit-button">
        <label for="edit-toggle">Edit Profile</label>
      </div>
      <% } else { %>
      <p>No user information found.</p>
      <% } %>
      <div class="edit-form">
        <form action="account" method="post">
          <input type="hidden" name="action" value="edit" />
          <label for="name">New Name:</label>
          <input type="text" id="name" name="name" value="<%=name%>" /><br />
          <br />
          <label for="email">New Email:</label>
          <input type="text" id="email" name="email" value="<%=email%>" /><br />
          <br />
          <label for="password">New Password:</label>
          <input
            type="password"
            id="password"
            name="password"
            value="<%=password%>"
          /><br />
          <br />
          <% String error = request.getParameter("error"); if (error != null) {
          if (error.equals("password_length")) { %>
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
          <div class="btns">
            <input type="submit" value="Update Profile" />
            <label for="edit-toggle" class="cancel">Cancel </label>
          </div>
        </form>
      </div>
    </div>
  </body>
</html>
