<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
<form  action="reg">
<center>
<table border="1" Width="30%" cellpadding="5">
<thead>
<tr>
<th colspan="2">Enter Information Here</th>
</tr>
</thead>
<tbody>
<tr>
<td> Name</td>
<td><input type="text" name="name" /></td>
</tr>
<tr>
<td>Address</td>
<td><input type=""text" name="address"/></td>
</tr>
<tr>
<td>UserName</td>
<td><input type="text" name="username" /></td>
</tr>
<tr>
<td>Password</td>
<td><input type="text" name="password" /></td>
</tr>
<tr>
<td><input type="submit" value="Register"/></td>
<td><input type="reset" value="Reset"/></td>
</tr>
<tr>
<td colspan="2">Already registered!!<a href="/">Login Here</a></td>
</tr>
</tbody>
</table>
</center>
</form>

</body>
</html>