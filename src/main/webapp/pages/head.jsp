<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<meta charset="ISO-8859-1">
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/script_css/jquery.min.js"/></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/script_css/jquery.blockUI.js"/></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/script_css/jquery_ui.js"/></script>
<link href="${pageContext.servletContext.contextPath}/script_css/jquery_ui.css" rel="stylesheet" type="text/css">
<style>


@import url(https://fonts.googleapis.com/css?family=Patua+One|Open+Sans);

* { 
  -moz-box-sizing: border-box; 
  -webkit-box-sizing: border-box; 
    box-sizing: border-box; 
}

body {
  background:#353a40;
}

table {
  border-collapse: collapse;
  background:#fff;
  @include border-radius(5px);
  margin:50px auto;
  @include box-shadow(0px 0px 5px rgba(0,0,0,0.3));
  width:50%;
}
h1{
 background:#353a40;
  font-family: 'Patua One', cursive;
  font-size:20px;
  font-weight:400;
  color:#fff;
  @include text-shadow(1px 1px 0px rgba(0,0,0,0.5));
  text-align:left;
  padding:20px;
  @include background-image(linear-gradient(#646f7f, #4a5564));
  border-top:1px solid #858d99;
  
  &:first-child {
   @include border-top-left-radius(5px); 
  }

  &:last-child {
    @include border-top-right-radius(5px); 
  }
}
p{
font-family: 'Patua One', cursive;
  font-size:15px;
  font-weight:400;
  color:#fff;
}


 th {
 background:#353a40;
  font-family: 'Patua One', cursive;
  font-size:18px;
  font-weight:400;
  color:#fff;
  @include text-shadow(1px 1px 0px rgba(0,0,0,0.5));
  text-align:left;
  padding:20px;
  @include background-image(linear-gradient(#646f7f, #4a5564));
  border-top:1px solid #858d99;
  
  &:first-child {
   @include border-top-left-radius(5px); 
  }

  &:last-child {
    @include border-top-right-radius(5px); 
  }
}

tbody tr td {
  font-family: 'Patua One', cursive;
  font-weight:400;
  color:#5f6062;
  font-size:14px;
  padding:20px 20px 20px 20px;
  border-bottom:1px solid #e0e0e0;
  
}

tbody tr:nth-child(2n) {
  background:#f0f3f5;
}

tbody tr:last-child td {
  border-bottom:none;
  &:first-child {
    @include border-bottom-left-radius(5px);
  }
  &:last-child {
    @include border-bottom-right-radius(5px);
  }
}

tbody:hover > tr td {
  @include opacity(0.5);
  
  /* uncomment for blur effect */
  /* color:transparent;
  @include text-shadow(0px 0px 2px rgba(0,0,0,0.8));*/
}

tbody:hover > tr:hover td {
  @include text-shadow(none);
  color:#2d2d2d;
  @include opacity(1.0);
}
a{
color:#353a40;
}
a:hover{
font-size:15px;
}

.tabs {
    margin:  0;
    padding: 0;
    list-style: none;
    display: table; /* [1] */
    table-layout: fixed; /* [2] */
    width: 100%; /* [3] */
}

    .tabs__item {
        display: table-cell; /* [4] */
    }

        .tabs__link {
            display: block; /* [5] */
        }

.primary-nav {
    text-align: center;
    border-radius: 4px;
    overflow: hidden; /* [1] */
}

        .primary-nav a {
        border-radius: 4px;
            padding: 1em;
            background-color: #fff;
            color:#2d2d2d;
            font-weight: bold;
            text-decoration: none;
        }

        .primary-nav a:hover {
            background-color: #A3C43B;
        }
html {
    font: 0.75em/1.5 sans-serif;
}
.active{
font-size:15px;
display: table-cell;
display: block;
 background-color: green;
}
 input[type=submit] {
  background-color: grey;
  border: none;
  color: white;
  padding: 10px 20px;
  text-decoration: none;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 4px;
} 
 input[type=text],textarea,select {
  border-radius: 4px;
}
select{
width: 35%;
}
      
</style>
</head>

</html>