<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
/* .circular--square {
  border-radius: 50%;
}
td,h1{text-align: center}
table.center {
margin-left : auto;
margin-right : auto;} */

</style>
<meta charset="ISO-8859-1">
<title>Upload Photos</title>
</head>
<body>
<%@include file="head.jsp" %>
<h1 style="text-align:center;">Upload Photos</h1>
<div align="center">
        
        <form method="post" action="${pageContext.servletContext.contextPath}/saveuploadimage" enctype="multipart/form-data">
            <table width="50%" ><tbody>
            <tr><td>Image Description</td><td><input type="text" name="studentPhoto"/></td></tr>
                <tr>
                    <td>Pick file :</td>
                    <td><input type="file" name="studentImage" size="50" /></td>
                </tr>
                <input type="hidden" name="hdstudentId" value="${studentId}" />
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Upload" /></td>
                </tr>
                </tbody>
            </table>
        </form>
        <h1 style="text-align:center;">Uploaded Photos</h1>
        <c:if test="${uploadSuccess ne null and not empty uploadSuccess}">
        <p style="color:green">${uploadSuccess}</p>
        </c:if>
        <c:if test="${encodedImage ne null and not empty encodedImage}">
         <table width="50%" ><tbody>
        <c:forEach items="${encodedImage}" var="image" varStatus="num">
       
        <tr><td><a href="${pageContext.servletContext.contextPath}/downloadimage/${studentId}/${imageId[num.index]}/view"><img alt="img" src="data:image/jpeg;base64,${image}" class="circular--square" width="100" height="100" /></a></td>
        <td><a href="${pageContext.servletContext.contextPath}/downloadimage/${studentId}/${imageId[num.index]}/download">Download</a></td>
        </tr>
        
        </c:forEach>
        </tbody>
        </table>
        </c:if>
</body>
</html>