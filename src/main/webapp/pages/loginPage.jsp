<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Form</title>
</head>
<body>
<%@include file="head.jsp" %>
<h1 style="text-align:center;">Please LogIn To Continue</h1>
<table>
<tbody>

<form  method="post" action="${pageContext.servletContext.contextPath}/loginSave" >


<tr>
			<td>Email :</td>
			<td><input type="text" name="username" id="username"/></td>
			
		</tr>
		
		<tr>
		<td>Password : </td>
		<td><input type="password" name="password" id="password" placeholder="Password"></input></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:center;"><input type="submit" value="Submit" /></td>
		</tr>
		<tr><td colspan="2" style="text-align:center;"><a href="${pageContext.servletContext.contextPath}/home">Register</a></td></tr>
</form>
</tbody>
</table>
</body>
</html>