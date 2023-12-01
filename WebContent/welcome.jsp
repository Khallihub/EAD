<%@ page import="java.util.List"%>
<%@ page import="task.Task"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
}

.container {
	max-width: 1200px;
	margin: 0 auto;
	padding: 20px;
	display: flex;
	flex-direction: column;
}

h2 {
	color: #333;
}

.task-list {
	width: 40%;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	padding: 15px;
	margin: auto;
	align-self: center;
}

.task-details {
	float: right;
	width: 55%;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	padding: 15px;
}

ul {
	list-style: none;
	padding: 0;
}

li {
	margin-bottom: 15px;
	border-bottom: 1px solid #eee;
	padding-bottom: 15px;
}

p {
	margin: 0;
	padding: 3px;
}

form {
	margin-top: 10px;
}

input[type="submit"], input[type="checkbox"] {
	padding: 8px 15px;
	border: none;
	background-color: #333;
	color: #fff;
	cursor: pointer;
	border-radius: 3px;
}

input[type="submit"]:hover, input[type="checkbox"]:hover {
	background-color: #555;
}

.task-items {
	list-style: none;
	padding: 0;
}

.task-item {
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	padding: 15px;
	margin-bottom: 20px;
}

.task-content {
	margin-bottom: 10px;
	word-wrap: break-word;
}

.task-actions {
	display: flex;
	justify-content: flex-end;
}

.action-btn {
	border: none;
	background-color: transparent;
	cursor: pointer;
	margin-left: 5px;
	font-size: 18px;
	color: #333;
}

.boldSpan {
	font-weight: bold;
}
.action-btn i {
	font-size: inherit;
}

.hoverGreen:hover {
	color: green;
}
.hoverRed:hover {
	color: red;
}
.hoverBlue:hover {
	color: blue;
}
</style>
</head>
<body>
	<div class="container">
		<%@ include file="header.jsp"%>
		<%
			List<Task> tasks = (List<Task>) request.getAttribute("tasks");
			String Title = (String) request.getAttribute("page");
			System.out.print(request.getParameter("nomatches"));
		%>
		<h2><%= Title %></h2>
		<div class="task-list">
			<% if (!tasks.isEmpty()) { %>
			
			<ul class="task-items">
				<%
					for (Task task : tasks) {
				%>
				<li class="task-item">
					<div class="task-content">
						<p>
							<span class="boldSpan"> Title: </span>
							<%=task.getTitle()%>
						</p>
						<p>
							<span class="boldSpan"> Description: </span>
							<%=task.getDescription()%>
						</p>
						<p>
							<span class="boldSpan"> Due Date: </span>
							<%=task.getDueDate()%>
						</p>
						<p>
							<span class="boldSpan"> Priority: </span>
							<%=task.getPriority()%>
						</p>
						<p>
							<span class="boldSpan"> Status: </span>
							<%=task.getStatus()%>
						</p>
					</div>
					<div class="task-actions">
						<form action="EditTask" method="post">
							<input type="hidden" name="id" value="<%=task.getId()%>"> 
							<input type="hidden" name="title" value="<%=task.getTitle()%>">
							<input type="hidden" name="description" value="<%=task.getDescription()%>">							
							<input type="hidden" name="dueDate" value="<%=task.getDueDate()%>">
							<input type="hidden" name="type" value="forward">
							<button class="action-btn edit-btn hoverBlue" type="submit">
								<i class="fas fa-edit "></i>
							</button>
						</form>
						<form action="DeleteTask" method="post">
							<input type="hidden" name="taskId" value="<%=task.getId()%>">
							<button class="action-btn delete-btn hoverRed" type="submit">
								<i class="fas fa-trash-alt "></i>
							</button>
						</form>
						<form action="MarkAsDone" method="post">
							<input type="hidden" name="taskId" value="<%=task.getId()%>">
							<input type="hidden" name="status" value="<%=task.getStatus()%>">

							<%
								if (task.getStatus().equals("Completed")) {
							%>
							<button class="action-btn mark-done-btn hoverGreen" type="submit">
								<i class="fas fa-check"></i>
							</button>
							<%
								} else {
							%>
							<button class="action-btn mark-done-btn" type="submit">
								<i class="fas fa-clock"></i>
							</button>
							<%
								}
							%>
						</form>

					</div>
				</li>
				<%
					}
				%>
			</ul>
			<%} else {%> 
			<% if (request.getAttribute("nomatches") != null ) {%>
			<p> No matches found </p>
			<% } else {%>
			<p>Add You haven't added tasks yet.</p>
			<%} %>
		</div>
		<%} %>



	</div>
</body>
</html>
