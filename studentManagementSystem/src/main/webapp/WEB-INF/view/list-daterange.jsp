<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>

<html>

<head>
	<title>List Date Range</title>
	
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
    
    </ul>
      <form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" class="btn btn-outline-danger my-2 my-sm-0" />
	</form:form>
  </div>
</nav>
	
	<div id="wrapper">
		<div id="header">
			<h2>List date range : </h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
		
			<p>
				User: <b><security:authentication property="principal.username" /></b>, Role(s): <b><security:authentication property="principal.authorities" /></b>
			</p>
		

			<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
			
				<!-- put new button: Add Student -->
			
				<input type="button" value="Add Date Range"
					   onclick="window.location.href='showFormForAdd'; return false;"
					   class="add-button"
				/>
			
			</security:authorize>
	
		
			<!--  add our html table here -->
		
			<table id="daterangeTable" class="table table-striped table-bordered">
				<thead>
				<tr>
					
					<th>Start Date</th>
					<th>End Date</th>
					<th> Year</th>
	
					
					<%-- Only show "Action" column for managers or admin --%>
					<security:authorize access="hasAnyRole('MANAGER', 'ADMIN', 'EMPLOYEE')">
					
						<th>Action</th>
					
					</security:authorize>
					
				</tr>
				<thead>
				<tbody>
					<!-- loop over and print our students -->
					<c:forEach var="tempDaterange" items="${applicationDateRange}">
					
						<!-- construct an "update" link with student id -->
						<c:url var="updateLink" value="/daterange/showFormForUpdate">
							<c:param name="dateRangeId" value="${tempDaterange.id}" />
						</c:url>					
	
						<!-- construct an "delete" link with student id -->
						<c:url var="deleteLink" value="/daterange/delete">
							<c:param name="dateRangeId" value="${tempDaterange.id}" />
						</c:url>		
						
						<tr>
						<td> ${tempDaterange.start_date} </td>
						<td> ${tempDaterange.end_date} </td>
						<td> ${tempDaterange.year} </td>
					
						
						
							<!--  display the update link -->
						
							<security:authorize access="hasAnyRole('MANAGER', 'ADMIN', 'EMPLOYEE')">
							
								<td>
									<security:authorize access="hasAnyRole('MANAGER', 'ADMIN', 'EMPLOYEE')">
										<!-- display the update link -->
										<a href="${updateLink}"><i class="fas fa-user-edit" style="color:orange;"></i></a>
									</security:authorize>
		
									<security:authorize access="hasAnyRole('ADMIN')">
										<a href="${deleteLink}"
										   onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false"><i class="fas fa-user-times" style="color: red;"></i></a>
									</security:authorize>
								</td>
	
							</security:authorize>
													
						</tr>
					
					</c:forEach>
				</tbody>
						
			</table>
				
		</div>
	
	</div>
	
	<p></p>
		<script>
		$(document).ready( function () {
		    $('#studentsTable').DataTable({
		    		dom: 'Bflrtip',
		    		"order": [[7,'DESC']]
		    });
		    
		} );
		</script>
</body>

</html>