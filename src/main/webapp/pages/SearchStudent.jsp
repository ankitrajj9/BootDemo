<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Student</title>
<script type="text/javascript">
function getData(){
	 /* $(document).ajaxStart(function(){
	    $("#wait").css("display", "block");
	  }); */ 
	$.ajax({
		  method: "GET",
		  async:true,
		  url: "${pageContext.servletContext.contextPath}/viewStudentsmapping/1",
		   beforeSend: function(){
			    // Show image container
			    $.blockUI({ message: $('#displayBox'),css: {  
		            border: 'none', 
		            padding: '15px', 
		            backgroundColor: '#ffffff00', 
		            '-webkit-border-radius': '10px', 
		            '-moz-border-radius': '10px', 
		            opacity: .5, 
		            color: '#fff' 
		         } });
			  /* $("#wait").css("display", "block"); */
			   }, 
		  success:function(result) { 
         $('#mainPart').html(result);
         
       } ,
       complete:function(data){
           // Hide image container
       	/* $("#wait").css("display", "none"); */
       	$.unblockUI();
          } 
		});
	 /* $(document).ajaxComplete(function(){
	    $("#wait").css("display", "none");
	  }); */ 
	  $('#searchTab').removeClass("active");
	$('#regTab').removeClass("active");
	$("#studTab").addClass("active");
}

function searchInfo(obj){
	var searchVal = $(obj).val();
	$.ajax({
		  method: "GET",
		  async:true,
		  url: "${pageContext.servletContext.contextPath}/getStudentBySearchCriteria/"+searchVal,
		  success:function(result) { 
    $('#resultDiv').html(result);
    
  } ,
  
		});
}


</script>
</head>
<body>
<%@include file="head.jsp" %>
<div id="displayBox" style="display:none;">
  <img src="${pageContext.servletContext.contextPath}/images/gears-anim.gif" />
</div>
<ul class="tabs  primary-nav">
    <li class="tabs__item">
        <a href="/home" id="regTab" class="tabs__link active">Registration</a>
    </li>
    <li class="tabs__item" >
        <a href="javascript:void(0);" id="studTab" class="tabs__link" onclick="return getData();">View Students</a>
    </li>
    <li class="tabs__item">
        <a href="${pageContext.servletContext.contextPath}/searchStudentmapping" id="searchTab" class="tabs__link">Search</a>
    </li>
    <li class="tabs__item">
        <a href="${pageContext.servletContext.contextPath}/logout" class="tabs__link">Logout</a>
    </li>
</ul>
<div id="mainPart">
<h1 style="text-align:center;">Search Students</h1>
<table><tbody>

<form>
<tr>
			<td>Search :</td>
			<td><input type="text" name="search" id="search" size="50" onkeyup="searchInfo(this);" /></td>
			
		</tr>
		<tr><td>Result :</td><td id="resultDiv"></td></tr>
</form>


</tbody></table></div>
</body>
<script type="text/javascript">
$(function() {
	$('#regTab').removeClass("active");
	$("#studTab").removeClass("active");
	$("#searchTab").addClass("active");
});
</script>
</html>