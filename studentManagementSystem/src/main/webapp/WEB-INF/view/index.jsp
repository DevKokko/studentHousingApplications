<%-- <% response.sendRedirect("student/list"); %> 
 --%>
<%--  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>


<c:url value="/" var="homeUrl"/>
<c:url value="user/list" var="userUrl"/>
<c:url value="student/list" var="studentUrl"/>
<c:url value="application/list" var="applicationUrl"/>

<div class="menu">
	<ul>
		<li><a href="${homeUrl}">Home</a></li>
		<li><a href="${userUrl}">User List</a></li>
		
	
		<li><a href="${studentUrl}">Student's List</a></li>

		<li><a href="${applicationUrl}">Application's List</a></li>
	</ul>
	<span id="menu-username"><%=SecurityContextHolder.getContext().getAuthentication().getName()%></span>
	<br style="clear:left"/>
</div>  --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html>


<c:url value="user/list" var="userUrl"/>
<c:url value="student/list" var="studentUrl"/>
<c:url value="application/list" var="applicationUrl"/>
<c:url value="daterange/list" var="daterangeUrl"/>
<c:url value="limit/list" var="limitUrl"/>

<head>
	<title>List of actions: </title>
	
	<!-- reference our style sheet -->

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />
		  
		  <!-- Reference Bootstrap files -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
	<script src="https://kit.fontawesome.com/6626873403.js" crossorigin="anonymous"></script>

	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css"></link>
</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Student Management System</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="/spring-crm-with-security">Home <span class="sr-only">(current)</span></a>
      </li>
    <!--   <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li> -->
      <!--<li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Dropdown
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Action</a>
          <a class="dropdown-item" href="#">Another action</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
      </li>-->
    <%--   <li>
	      <security:authorize access="hasAnyRole('ADMIN')">
	      	<a class="nav-link" href="${pageContext.request.contextPath}/register/showRegistrationForm" ">Register User</a>
	      </security:authorize>
      </li> --%>
    </ul>
     <form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" class="btn btn-outline-danger my-2 my-sm-0" />
	</form:form> 
  </div>
</nav>

	
	<div id="wrapper">
		<div id="header">
			<h2>List of actions: </h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
		
				<p>
					User: <b><security:authentication property="principal.username" /></b>, Role(s): <b><security:authentication property="principal.authorities" /></b>
				</p>
					
		
			<!--  add our html table here -->
		
			<table id="actionsTable" class="table table-striped table-bordered">
				<thead>
				
						<ul class="list-group">
						
							
						  		<li class="list-group-item"><a href="${userUrl}">User's List</a></li>
						  		
						  	
						  		<li class="list-group-item"><a href="${studentUrl}">Student's List</a></li>
						
						  		<li class="list-group-item"><a href="${applicationUrl}">Application's List</a></li>							
								<li class="list-group-item"><a href="${daterangeUrl}">Date range List</a></li>
								<li class="list-group-item"><a href="${limitUrl}">Limit's List</a></li>							
															
								
						</ul>
				
    
				<thead>
				<tbody>
		
				</tbody>
						
			</table>
				
		</div>
	
	</div>
	
	
</body>

</html>
