<%-- <%@ page import=com.model.Student %> --%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">
function del()
{
document.my.action="delete";
document.my.submit();
	}
	
function edit()
{
document.my.action="edit";
document.my.submit();
	}
	
	
function add()
{
document.my.action="regpage";
document.my.submit();
	}
	</script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form name="my">
<table border="2">
<tr>
<th>Name</th>
<th>Address</th>
<th>Username</th>
<th>Password</th>
<th>Select</th>
</tr>
 <c:forEach items="${key}" var="stu">
<tr>
<td><c:out value="${stu.name}"></c:out></td>
<td><c:out value="${stu.address}"></c:out></td>
<td><c:out value="${stu.username}"></c:out></td>
<td><c:out value="${stu.password}"></c:out></td>
<td><input type="radio" name="id" value="${stuu.id}"></td>

</tr>
</c:forEach> 
<%-- <tr>
<th>
${key.name}
</th>
<th>
${key.address}
</th>
<th>
${key.username}
</th>
<th>
${key.password}
</th>
<td><input type="radio" name="id" value="$ {stu.id}"></td>
</tr>
 --%>
</table>

<input type="button" value="DELETE" onclick="del()">
<input type="button" value="EDIT" onclick="edit()">
<input type="button" value="ADD" onclick="add()">
<div>
<a href="http://localhost:8084/api/student/download/student.xlsx">Student .XLSX</a>

</div>
<div>
<a href="/pdfreport">Student.pdf</a>
</div>
</form>
</body>
</html>