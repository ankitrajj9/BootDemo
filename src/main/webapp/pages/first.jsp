<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Registration Form</title>
<script type="text/javascript">


function validation(){
	var retVal=true;
	$('[id^=err_]').each(
	function(){
		$(this).remove();
	}		
	);
	var studentName = $('#studentName').val();
	$("select option:selected").each(function () {
        if($(this).val() == 0 && $(this).prop("label") != 'Jan'){
        	retVal=false;
        	$(this).focus();
        	var varId = $(this).parent().attr("id");
        	var tst=$(this).closest('tr').children('td:first').text();
        	$('<tr id="err_'+varId+'"><td colspan="2" style="color:red;">Please Select '+tst.substring(0, tst.length - 2)+'</td></tr>').insertAfter($(this).closest('tr'));
        }
    });
	$('input[type="text"]').each(
	function(){
		if($(this).val() == undefined || $(this).val() == '' || $(this).val() == null){
			retVal=false;
			$(this).focus();
        	var varId = $(this).parent().attr("id");
        	var tst=$(this).closest('tr').children('td:first').text();
        	$('<tr id="err_'+varId+'"><td colspan="2" style="color:red;">Please Select '+tst.substring(0, tst.length - 2)+'</td></tr>').insertAfter($(this).closest('tr'));
		}
	}		
	);
	/* if($('#country').val()==0){
		var first = $('#country').closest("tr");
		first.append('<td><p style="color:red;">Please Select Country</p></td>');
		$('#country').focus();
		return false;
	}
	if($('#stateDiv').val()==0){
		var first = $('#stateDiv').closest("tr");
		first.append('<td><p style="color:red;">Please Select State</p></td>');
		$('#stateDiv').focus();
		return false;
	}
	if($('#cityDiv').val()==0){
		var first = $('#cityDiv').closest("tr");
		first.append('<td><p style="color:red;">Please Select City</p></td>');
		$('#cityDiv').focus();
		return false;
	} */
	return retVal;
}
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
	$('#regTab').removeClass("active");
	$("#studTab").addClass("active");
}
function getStatesByCountry(country){
	var countryId = $(country).val();
	if(countryId != 0){
	$.ajax({
		  method: "GET",
		  async:true,
		  url: "${pageContext.servletContext.contextPath}/getStateComboByCountryId/"+countryId,
		  success:function(result) { 
        $('#stateDiv').html(result);
        
      } ,
      
		});}else{$('#stateDiv').html('<option value="0">Please Select</option>');}
	$('#cityDiv').html('<option value="0">Please Select</option>');
	
}
function getCityByState(state){
	var stateId = $(state).val();
	$.ajax({
		  method: "GET",
		  async:true,
		  url: "${pageContext.servletContext.contextPath}/getCityComboByStateId/"+stateId,
		  success:function(result) { 
      $('#cityDiv').html(result);
      
    } ,
    
		});
}
</script>
</head>
<body>

<%-- <%@include file="../images/blue_abstract.jpg" %> --%>
<%@include file="head.jsp" %>
<div id="displayBox" style="display:none;">
  <img src="${pageContext.servletContext.contextPath}/images/gears-anim.gif" />
</div>
<ul class="tabs  primary-nav">
    <li class="tabs__item">
        <a href="${pageContext.servletContext.contextPath}/home" id="regTab" class="tabs__link active">Registration</a>
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
<!-- <div id="wait" style="display:none;width:69px;height:89px;border:1px solid black;margin-left: auto;margin-right: auto;padding:2px;">
  <img src="/images/loading_image.gif" />
</div> -->
<div id="mainPart">
<h1 style="text-align:center;">${studentdtBean.studentId eq 0 ? 'Register New Student' : 'Update Student'}</h1>
<table><tbody>
<c:choose>
<c:when test="${isEdit eq false}">
<c:set value="${pageContext.servletContext.contextPath}/savestudent" var="saveorupdate" />
</c:when>
<c:otherwise>
<c:set value="${pageContext.servletContext.contextPath}/updatestudent" var="saveorupdate" />
</c:otherwise>
</c:choose>
<form:form  method="post" modelAttribute="studentdtBean" action="${saveorupdate}" onsubmit="return validation();">
<tr><td colspan="2"><form:hidden path="studentId"  /></td></tr>
<tr>
			<td>Name :</td>
			<td><form:input path="studentName"/></td>
			
		</tr>
		<tr>
			<td>Email :</td>
			<td><form:input path="emailId"/></td>
			
		</tr>
		
		<tr>
		<td>Password : </td>
		<td><form:input type="password" path="password" placeholder="Password"></form:input></td>
		</tr>
		
		<tr>
		<td>Birth Date : </td>
		<td><form:input path="date" name="date" class="date" readonly="true" placeholder="DD/MM/YYYY"></form:input></td>
		</tr>
		
		<tr>
			<td>Gender :</td>
			<td><form:radiobutton path="gender" value="Male"  />Male&nbsp;<form:radiobutton
					path="gender" value="Female" />Female</td>
			</tr>
		
		<tr>
			<td>Hobbies:</td>
			<td><c:forEach items="${hobbies}" var="hobby" varStatus="h">
					<form:checkbox path="hobbies" value="${hobby}" />${hobby}
			</c:forEach></td>
		</tr>
		
		<tr>
			<td>Skills:</td>
			<td><form:select path="skills" multiple="true">
					<c:forEach items="${skills}" var="skill" varStatus="s">
		 <form:option value="${skill}" label="${skill}"/>
		 </c:forEach>
				</form:select></td>
		</tr>
		
		<tr>
			<td>Address:</td>
			<td><form:textarea path="address" /></td>
		</tr>
		
		<tr>
		<td>Country :</td>
		<td>
		<form:select path="country" onchange="getStatesByCountry(this);">
					<c:forEach items="${countries}" var="country">
					<form:option value="${country.countryId}" label="${country.countryName}" />
					</c:forEach>
				</form:select>
		</td></tr>
		<tr>
		<td>State :</td>
		<td>
		<form:select path="state" id="stateDiv" onchange="getCityByState(this);">
					<c:forEach items="${states}" var="state">
					<form:option value="${state.stateId}" label="${state.stateName}" />
					</c:forEach>
					</form:select>
		</td></tr>
		<tr>
			<td>City :</td>
			<td><form:select path="city" id="cityDiv">
					<c:forEach items="${cities}" var="city">
					<form:option value="${city.cityId}" label="${city.cityName}" />
					</c:forEach>
				</form:select></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:center;"><input type="submit" value="${isEdit == 'true' ? 'Update' : 'Save'}" /></td>
		</tr>
		<tr><td colspan="2" style="text-align:center;"><a href="${pageContext.servletContext.contextPath}/viewStudentsmapping/1">View All Students</a></td></tr>

</form:form>
</tbody>
</table>
</div>

</body>
<script type="text/javascript">

$(function() {
	 $( ".date" ).datepicker({
	 dateFormat : 'dd/mm/yy',
	 showOn: "both",
	 buttonImage: "/images/Calendar-icon.png",
	 buttonImageOnly: true,
	 buttonText: "Select date",
	 changeMonth: true,
	 changeYear: true,
	 yearRange: "-100:+0"
	 
	 }); 
	});
	
	<c:if test="${isEdit eq true}">
$(function() {
	var studentDate = '${studentdtBean.date}';
	var strArr = studentDate.split("-");
	var realDate = new Date();
	realDate.setDate(strArr[2].substring(0, 2))  //date 1 to 31
	var month = strArr[1]-1;
	realDate.setMonth(month);  //month 0 to 11
	realDate.setFullYear(strArr[0]);
	
	$('#datePicker').datepicker({ dateFormat: 'dd/mm/yy' });
	   $( ".date" ).datepicker("setDate",realDate);
	});
</c:if>
</script>

</html>