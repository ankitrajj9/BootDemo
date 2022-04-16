<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<style>
/* body {
    background-image: url("/images/blue_abstract.jpg");
    /*
    Using Base64 Image As Background Image 
    background-image: url(data:image/jpeg;base64,${image}); 
    */
} */
</style>
</head>
<body>
<%@include file="head.jsp" %>
<div id="displayBox" style="display:none;">
  <img src="/images/gears-anim.gif" />
</div>
<h1 style="text-align:center;">Students List</h1>
<c:if test="${saveSuccess ne null and not empty saveSuccess}">
        <p style="color:#fff;text-align:center;">${saveSuccess}</p>
        </c:if>
<table width="100%">
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Email Id</th>
		<th>Gender</th>
		<th>Hobbies</th>
		<th>Skills</th>
		<th>Address</th>
		<th>Country</th>
		<th>State</th>
		<th>City</th>
		<th>Edit</th>
		<security:authorize url="/deletestudentprofile">
		<th>Delete</th>
		</security:authorize> 
		<th>Upload</th>
	</tr><tbody>
	<c:forEach var="stud" items="${list}" varStatus="status">
	<%--  <c:if test="${status.count % 2 eq 0}">
	<c:set var="n" value='0'></c:set>
	</c:if>  --%>
	
		<tr <%-- <c:choose> <c:when test="${status.count % 2 eq 0}">style="background:grey"</c:when><c:otherwise>style="background:white"</c:otherwise>   </c:choose> --%>>
			<td>${stud.studentId}</td>
			<td><a href="/viewstudentprofile/${stud.studentId}">${stud.studentName}</a></td>
			<td>${stud.emailId}</td>
			<td>${stud.gender}</td>

			<td><c:forEach var="h" items="${stud.hobbies}">
     ${h.hobbyName} <br/>   
    </c:forEach></td>

			<td><c:forEach var="s" items="${stud.skills}">
    ${s.skillName}<br/>
    </c:forEach></td>
			<td>${stud.address}</td>
			<td>${stud.country}</td>	
			<td>${stud.state}</td>
			<td>${stud.city}</td>
			<td><a href="${pageContext.servletContext.contextPath}/editstudentprofile/${stud.studentId}/1">Edit</a></td> 
			
       <security:authorize url="/deletestudentprofile"> 
    <td><a href="${pageContext.servletContext.contextPath}/deletestudentprofile/${stud.studentId}" onclick="return confirm('Are you sure ? ')">Delete</a></td>  
    </security:authorize>  



			<td><a href="${pageContext.servletContext.contextPath}/uploadimage/${stud.studentId}">Upload</a></td>
		</tr>
	</c:forEach>
	
	
	
	<tr><td colspan="13" style="text-align:center;"><a href="${pageContext.servletContext.contextPath}/home">Add New Student</a></td></tr></tbody>
</table>
<c:if test="${totalSize gt 10}">
<table style="width:100px"><tbody>
<tr>
<tr>
<c:choose>
<c:when test="${currentPage eq 1}">
 <td>Previous</td>
</c:when>
<c:otherwise>
 <td><a href="${pageContext.servletContext.contextPath}/viewStudentsmapping/${currentPage-1}">Previous</a></td>
</c:otherwise>
</c:choose>

<c:forEach var ="linkNo" begin = "1" end = "${totalLinks}">
<c:choose>
<c:when test="${currentPage eq linkNo}">
<td>${linkNo}</td>
</c:when>
<c:otherwise>
<td><a href="${pageContext.servletContext.contextPath}/viewStudentsmapping/${linkNo}">${linkNo}</a></td>
</c:otherwise>
         
         </c:choose>
      </c:forEach>

<c:choose>
<c:when test="${currentPage eq lastpage}">
 <td>Next</td>
</c:when>
<c:otherwise>
 <td><a href="${pageContext.servletContext.contextPath}/viewStudentsmapping/${currentPage+1}">Next</a></td>
</c:otherwise>
</c:choose>
</tr>
</tr>
</tbody></table>
</c:if>
<br />

</body>
</html>