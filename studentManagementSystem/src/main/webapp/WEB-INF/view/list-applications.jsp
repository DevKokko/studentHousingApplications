<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>

<html>

<c:url value="../user/list" var="userUrl"/>
<c:url value="../student/list" var="studentUrl"/>
<c:url value="../application/list" var="applicationUrl"/>
<c:url value="../daterange/list" var="daterangeUrl"/>
<c:url value="../limit/list" var="limitUrl"/>

<head>
	<title>List Applications</title>
	
	<!-- reference our style sheet -->

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />
		  
		  <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
		  
		  <!-- Reference Bootstrap files -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
	<script src="https://kit.fontawesome.com/6626873403.js" crossorigin="anonymous"></script>

	
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
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Actions
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="${userUrl}">User's List</a>
          <a class="dropdown-item" href="${studentUrl}">Student's List</a>
          <a class="dropdown-item" href="${applicationUrl}">Application's List</a>
          <a class="dropdown-item" href="${daterangeUrl}">Date Range List</a>
          <a class="dropdown-item" href="${limitUrl}">Limit's List</a>
          <!--<div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a>-->
        </div>
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
			<h2>List of applications: </h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
		
			<p>
				User: <b><security:authentication property="principal.username" /></b>, Role(s): <b><security:authentication property="principal.authorities" /></b>
			</p>	
		
			<!--  add our html table here -->
		
			<table id="applicationsTable" class="table table-striped table-bordered">
				<thead>
				<tr>
					<th>Student id</th>
					<th>Student has no income</th>
					<th>Family income</th>
					<th>Unemployed parents </th>
					<th>Studying siblings </th>
					<th>Comes from another city </th>
					<th>Score </th>
					<th>Approved</th>
					<th>Year</th>
					<th>File Url</th>
					
					
					<%-- Only show "Action" column for managers or admin --%>
					<security:authorize access="hasAnyRole('MANAGER', 'ADMIN', 'EMPLOYEE')">
					
						<th>Action</th>
					
					</security:authorize>
					
				</tr>
				<thead>
				<tbody>
					<!-- loop over and print our students -->
					<c:forEach var="tempApplication" items="${applications}">
					
						<!-- construct an "update" link with student id -->
						<c:url var="updateLink" value="/application/showFormForUpdate">
							<c:param name="applicationId" value="${tempApplication.id}" />
						</c:url>					
	
						<!-- construct an "delete" link with student id -->
						<c:url var="deleteLink" value="/application/delete">
							<c:param name="applicationId" value="${tempApplication.id}" />
						</c:url>	
						<c:url var="approveLink" value="/application/approve">
							<c:param name="applicationId" value="${tempApplication.id}" />
						</c:url>	
						<c:url var="rejectLink" value="/application/reject">
							<c:param name="applicationId" value="${tempApplication.id}" />
						</c:url>
						<c:url var="pendingLink" value="/application/pending">
							<c:param name="applicationId" value="${tempApplication.id}" />
						</c:url>		
						
						<tr>
						<td> ${tempApplication.student_id} </td>
						<td> 
							<c:choose>
								<c:when test="${tempApplication.student_income =='1'}">
							       	<span style="">Has no income</span>
							    </c:when> 
							    <c:when test="${tempApplication.student_income=='0'}">
							       	<span style="">Has income</span>
							    </c:when>    
							</c:choose>
						</td>
						<td> ${tempApplication.family_income} </td>
						<td> 
							<c:choose>
								<c:when test="${tempApplication.unemployeed_parents =='1'}">
							       	<span style="">Both parents unemployeed</span>
							    </c:when> 
							    <c:when test="${tempApplication.unemployeed_parents =='0'}">
							       	<span style="">Both parents not unemployeed</span>
							    </c:when>    
							</c:choose>
						</td>
						<td> ${tempApplication.studying_siblings} </td>
						<td> 
							<c:choose>
								<c:when test="${tempApplication.is_from_another_city =='1'}">
							       	<span style="">Yes</span>
							    </c:when> 
							    <c:when test="${tempApplication.is_from_another_city =='0'}">
							       	<span style="">No</span>
							    </c:when>    
							</c:choose>
						</td>
						<td> ${tempApplication.score} </td>
						<td>  
							<c:choose>
								<c:when test="${tempApplication.approved=='1'}">
							       	<span style="color:green;">Accepted</span>
							    </c:when> 
							    <c:when test="${tempApplication.approved=='0'}">
							       	<span style="color:darkgoldenrod;">Pending</span>
							    </c:when>    
							    <c:otherwise>
							        <span style="color:red;">Rejected</span>
							    </c:otherwise>
							</c:choose>
						</td>
						<td> ${tempApplication.year} </td>
						<td> <a href="http://project2-group4.alwaysdata.net/api/${tempApplication.fileUrl}" target="_blank">File</a> </td>
						
						
						
							<!--  display the update link -->
						
							<security:authorize access="hasAnyRole('MANAGER', 'ADMIN', 'EMPLOYEE')">
							
								<td>
									<security:authorize access="hasAnyRole('MANAGER')">
										<!-- display the update link -->
										<a href="${updateLink}"><i class="fas fa-user-edit" style="color:orange;"></i></a>
									</security:authorize>
		
									<security:authorize access="hasAnyRole('MANAGER')">
										<a href="${deleteLink}"
										   onclick="if (!(confirm('Are you sure you want to delete this application?'))) return false"><i class="fas fa-user-times" style="color: red;"></i></a>
									</security:authorize>
									<security:authorize access="hasAnyRole('EMPLOYEE')">
										<!-- display the update link -->
										<a href="${approveLink}"><i class="fa fa-check-circle" style="color:green;"></i></a>
									</security:authorize>
									<security:authorize access="hasAnyRole('EMPLOYEE')">
										<!-- display the update link -->
										<a href="${pendingLink}"><i class="fa fa-clock-o" aria-hidden="true" style="color:darkgoldenrod;"></i></a>
									</security:authorize>
									<security:authorize access="hasAnyRole('EMPLOYEE')">
										<!-- display the update link -->
										<a href="${rejectLink}"><i class="fa fa-times-circle" style="color:red"></i></a>
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
		    $('#applicationsTable').DataTable({
		    		dom: 'Bflrtip',
		    		"order": [[6,'DESC']]
		    });
		    
		} );
		</script>
</body>

</html>
