<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save User</title>
	
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
	<h3>Save User</h3>
	<% String isUpdate = (String)request.getAttribute("isUpdate"); 
		String action = isUpdate=="0"?"saveUser":"updateUser";
		boolean disabledField = isUpdate=="0"?false:true;
	%>

	<form:form action="<%= action %>" modelAttribute="user" method="POST" style="padding: 25px 50px;background-color: white;border-radius: 10px;" >
	
	<!-- need to associate this data with the given student id -->
	<!--<form:hidden path="username" />-->
	<table>
		<tbody>
			<tr>
				<td><label>Username:</label></td>
				<td><form:input path="username" disabled="<%= disabledField %>"/></td>
			</tr>
			
			<tr>
				<td><label>Password:</label></td>
				<td><form:input id="password" path="password" /></td>
			</tr>
			
			<tr>
				<td><label>Enabled:</label></td>
				<td><form:input id="enabled" path="enabled" style="display:none;" />
					<label class="switch">
					  <input form="fakeForm" id="checkboxEnalbed" onchange="EnabledChanged(this);" type="checkbox">
					  <span class="slider round"></span>
					</label>
				</td>
				
			</tr>
			
			<%-- 
			<tr>
				<td><label>Enabled </label></td>
				<td>
					<label class="switch">
					  <input form="fakeForm" id="checkboxEnalbed" type="checkbox" name="enabled" onchange="EnabledChanged(this);">
					  <span class="slider round"></span>
					</label>
					<form:input id="enabled" path="enabled" style="display:none;"/>
				</td>
			</tr> --%>

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

				
			
				window.addEventListener('load', function () {
					var isUpdate = "<%= (String)request.getAttribute("isUpdate") %>";
					//generate password
					var chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%&*()_+-=[]?";
					var pwd = "";
					var length = 12;
					for (var i = 0, n = chars.length; i < length; ++i) {
						pwd += chars.charAt(Math.floor(Math.random() * n));
				    }
				
					if(isUpdate=="0"){
						document.getElementById("username").value="";
						document.getElementById("password").value = pwd;
						document.getElementById("enabled").value="0";
					}
				
				});
				
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
		<a href="${pageContext.request.contextPath}/user/list">Back to List</a>
	</p>
	
</div>	
</div>


</body>

</html>