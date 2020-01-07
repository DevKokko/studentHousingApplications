<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save Student</title>
	
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
	<h3>Save Student</h3>


	<form:form action="saveStudent" modelAttribute="student" method="POST" style="padding: 25px 50px;background-color: white;border-radius: 10px;" >
	
	<!-- need to associate this data with the given student id -->
	<form:hidden path="id" />
	<table>
		<tbody>
			<tr>
				<td><label>First Name:</label></td>
				<td><form:input path="firstName" /></td>
			</tr>
			
			<tr>
				<td><label>Last Name:</label></td>
				<td><form:input path="lastName" /></td>
			</tr>
			
			<tr>
				<td><label>Email:</label></td>
				<td><form:input path="email" /></td>
			</tr>
			
			<tr>
				<td><label>Phone Number:</label></td>
				<td><form:input path="phoneNumber" /></td>
			</tr>
			
			<tr>
				<td><label>Department</label></td>
				<td><form:input id="department" path="department" /></td>
			</tr>
			
			<tr>
				<td><label>Registration Year</label></td>
				<td><form:input id="registrationYear" path="registrationYear" /></td>
			</tr>
			
			<tr>
				<td><label>Semester </label></td>
				<td><form:input onchange="Calculate();" id="semester" path="semester" /></td>
			</tr>
			
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
			</tr>
			
			<script>
				var isUpdate = "<%= (String)request.getAttribute("isUpdate") %>";
				if(isUpdate=="0"){
					document.getElementById("department").value="";
					document.getElementById("registrationYear").value="";
					document.getElementById("semester").value="";
					document.getElementById("score").value="";
				}
				
				function Calculate(){
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
				}
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
		<a href="${pageContext.request.contextPath}/student/list">Back to List</a>
	</p>
	
</div>	
</div>


</body>

</html>