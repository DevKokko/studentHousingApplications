<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save Application</title>
	
	<link type="text/css"
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/style.css">
			
			
	<link type="text/css"
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/add-student-style.css">		
			
</head>

<body>

	<div id="wrapper">
	
	<div id="container">
	
		<div id="header">
			<h2>Student Housing Application</h2>
		</div>
	
	</div>
	
	</div>

<div id="container">
<div id="content">
	<h3>Save Application</h3>
	<% String isUpdate = (String)request.getAttribute("isUpdate"); 
		String action = isUpdate=="0"?"saveApplication":"updateApplication";
		%>

	<form:form action="<%= action %>" modelAttribute="applications" method="POST" style="padding: 25px 50px;background-color: white;border-radius: 10px;" >
	
	<!-- need to associate this data with the given student id -->
	<form:hidden path="id" />
	<table>
		<tbody>
			<tr>
				<td><label>Student income:</label></td>
				<td><form:input path="student_income" /></td>
			</tr>
			
			<tr>
				<td><label>Family income:</label></td>
				<td><form:input path="family_income" /></td>
			</tr>
			
			<tr>
				<td><label>Unemployeed Parents: </label></td>
				<td><form:input path="unemployeed_parents" /></td>
			</tr>
			
			<tr>
				<td><label>Studying siblings:</label></td>
				<td><form:input path="studying_siblings" /></td>
			</tr>
			
			<tr>
				<td><label>is From Another City</label></td>
				<td><form:input  path="is_from_another_city" /></td>
			</tr>
			
			<tr>
				<td><label>Score</label></td>
				<td><form:input path="score" /></td>
			</tr>
			
			<tr>
				<td><label>Approved </label></td>
				<td><form:input path="approved" /></td>
			</tr>
			
			<tr>
				<td><label>year </label></td>
				<td><form:input path="year" /></td>
			</tr>
			
			
			
			<script>
				function EnabledChanged(element){
					if(element.checked){
						document.getElementById("enabled").value = "1";
					}
					else
						document.getElementById("enabled").value = "0";
				}
				if(document.getElementById("enabled").value == "1")
					document.getElementById("checkboxEnalbed").checked = true;
				else
					document.getElementById("checkboxEnalbed").checked = false;
			</script>
			
		
		
<%-- 

			<tr>
				<td><label>Has no income:</label></td>
				<td><input onchange="Calculate();" id="studentIncome" type="checkbox" placeholder="" /></td>
			</tr>
			<tr>
				<td><label>Both parents are unemployed:</label></td>
				<td><input onchange="Calculate();" id="parents" type="checkbox" placeholder="" /></td>
			</tr>
			<tr>
				<td><label>Family income:</label></td>
				<td><input onchange="Calculate();" id="familyIncome" type="number" value="0" placeholder="" /></td>
			</tr>
			<tr>
				<td><label>How many siblings who study:</label></td>
				<td><input onchange="Calculate();" id="siblings" type="number" value="0" placeholder="" /></td>
			</tr>
			<tr>
				<td><label>Comes from another city:</label></td>
				<td><input onchange="Calculate();" id="fromAnotherCity" type="checkbox" placeholder="" /></td>
			</tr>
			<tr>
				<td><label>How many years already:</label></td>
				<td><input onchange="Calculate();" id="years" type="number" placeholder="" value="0" /></td>
			</tr>
			
			<tr>
				<td><label>score </label></td>
				<td><form:input id="score" path="score" /></td>
			</tr> --%>
			
			<script>
				if(isUpdate=="0"){
					document.getElementById("department").value="";
					document.getElementById("registrationYear").value="";
					document.getElementById("semester").value="";
				//	document.getElementById("score").value="";
				}
				
				/*function Calculate(){
					var score = 0;
					
					if(document.getElementById("familyIncome").value < 10000){
						score+=100;
					}
					else if(document.getElementById("familyIncome").value < 15000){
						score+=30;
					}
					
					score += document.getElementById("siblings").value*20;
					
					if(document.getElementById("fromAnotherCity").checked){
						score+=50;
					}
					
					score -= document.getElementById("years").value*10;
										
					if(document.getElementById("studentIncome").checked && document.getElementById("parents").checked){
						score = 10000000;
					}
					if(document.getElementById("semester").value>8){
						score = -10000000;
					}
					document.getElementById("score").value = score;
				}*/
			</script>
			
			
			
			
			<tr>
				<td><label></label></td>
				<td><input type="submit" value="Save" class="save" /></td>
			</tr>
		
		</tbody>
	
	
	</table>
	
	
	</form:form>


	<div style="clear; both;"> </div>
	
	<p>
		<a href="${pageContext.request.contextPath}/application/list">Back to List</a>
	</p>
	
</div>	
</div>


</body>

</html>