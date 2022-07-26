<%@ page import="com.model.Student" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form  action="update">
<% Student stu=(Student)request.getAttribute("Student"); %>
<center>
<table border="1" Width="30%" cellpadding="5">
<thead>
<tr>
<th colspan="2">Enter Information Here</th>
</tr>
</thead>
<tbody>
<input type="hidden" name="id" value=<%=stu.getId() %>>
<tr>
<td>Name</td>
<td><input type="text" name="name" value=<%=stu.getName() %>></td>
</tr>
<tr>
<td>Address</td>
<td><input type="text" name="address" value=<%=stu.getAddress() %>></td>
</tr>
<tr>
<td>UserName</td>
<td><input type="text" name="username" value=<%=stu.getUsername() %>></td>
</tr>
<tr>
<td>Password</td>
<td><input type="text" name="password" value=<%=stu.getPassword() %>></td>
</tr>
<tr>
<td><input type="submit" value="UPDATE"/></td>


</body>
</html>