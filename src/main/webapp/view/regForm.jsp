<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1 style="color: red">User Registration Form</h1>
	<form:form action="register" modelAttribute="user" method="POST"
		id="registerForm">
		<table border="1" style="background-color: cyan; text-align: center">

			<tr>
				<th>User Name::</th>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<th>User Name::</th>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<th>User Email::</th>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<th>User Mobile::</th>
				<td><form:input path="mobileNo" /></td>
			</tr>
			<tr>
				<th>User Address ::</th>
				<td><form:input path="addrs" /></td>
			</tr>
			<tr>
				<th>User PostalCode ::</th>
				<td><form:input path="pincode" /></td>
			</tr>
			<tr>
				<th>User Date Of Birth::</th>
				<td><form:input path="dob" /></td>
			</tr>
			<tr>
				<th>User Date Of Joinning::</th>
				<td><form:input path="doj" /></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="registration"></td>
			</tr>
		</table>
	</form:form>
	${msg}



</body>
</html>