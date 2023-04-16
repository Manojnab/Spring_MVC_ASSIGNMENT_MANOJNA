<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
	<link href="resources/css/login.css" rel="stylesheet" type="text/css">
<style>
.error {
	color: red;
}
</style>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="index.jsp">CMS</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="index.jsp">Home</a></li>
					<%
					String id = (String) session.getAttribute("email");
		
					if (id == null) {
					%>
					<li class="nav-item"><a class="nav-link" href="login">Login</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="register">Register</a>
					</li>
					<%
					}
					if (id != null) {
					%>
					<li class="nav-item"><a class="nav-link" href="logout">Logout</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="bookListLike">Liked List</a>
					</li>
					<%
					}
					%>
				</ul>
			</div>
		</div>
	</nav>


					<Table class="table" action="readLater" method="GET">
							<!--  JSP scriplets -->
							<%-- <% 
			 						List<String> roles = (List)request.getAttribute("roles");
									for(String role:roles)
									{
								%>
							
								<option value=<%=role %>><%=  role%></option>
								<% 
									}
								%> --%>
							<!--  JSTL JSP expression language -->
							READ LATER BOOKS
							<thead class="thead-dark">
						          <tr>
						             <th>Book Name</th>&nbsp
						             <th>Author</th>
						          </tr>
						      </thead>
						      <tbody>
						      
							<c:forEach items="${readLater }" var="readLater">
								<tr>
								<td value=${readLater }>${readLater.getAuthorName() }</td>
								<td value=${readLater }>${readLater.getBname() }</td>

								</tr>
								
							</c:forEach>
							</tr>
							</tbody>

					
					</Table>

</body>
</html>