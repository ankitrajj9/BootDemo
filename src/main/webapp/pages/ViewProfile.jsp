<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Profile</title>
</head>
<body>
<%@include file="head.jsp" %>
<div id="mainPart">
<h1 style="text-align:center;">View Profile</h1>
<table><tbody>
<tr>
			<td>Name </td>
			<td style="font-weight: bold;">:</td>
			<td>${student.studentName}</td>
			
		</tr>
		<tr>
			<td>Email</td><td style="font-weight: bold;">:</td>
			<td>${student.emailId}</td>
			
		</tr>
		<tr>
		<td>Birth Date  </td><td style="font-weight: bold;">:</td>
		<td>${student.date}</td>
		</tr>
		<tr>
			<td>Gender </td><td style="font-weight: bold;">:</td>
			<td>${student.gender}</td>
			</tr>
			
			<tr>
			<td>Hobbies</td><td style="font-weight: bold;">:</td>
			<td><c:forEach items="${student.hobbies}" var="hobby" >
					${hobby.hobbyName}<br/> 
			</c:forEach></td>
		</tr>
		
		<tr>
			<td>Skills</td><td style="font-weight: bold;">:</td>
			<td>
					<c:forEach items="${student.skills}" var="skill" >
					${skill.skillName}<br/> 
		 </c:forEach>
				</td>
		</tr>
		
		<tr>
			<td>Address</td><td style="font-weight: bold;">:</td>
			<td>${student.address}</td>
		</tr>
		
		<tr>
		<td>Country </td><td style="font-weight: bold;">:</td>
		<td>${student.country}
		</td></tr>
		<tr>
		<td>State </td><td style="font-weight: bold;">:</td>
		<td>${student.state}
		</td></tr>
		<tr>
			<td>City </td><td style="font-weight: bold;">:</td>
			<td>${student.city}</td>
		</tr>
</tbody>
</table>
</div>

</body>
</html>