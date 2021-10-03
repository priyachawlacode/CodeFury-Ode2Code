<%@page contentType="text/html" pageEncoding="UTF-8"
	import="com.hsbc.networking.model.User, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<title>Blocked List</title>

<!--Stylesheets-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/homeStyle.css">

<!--Bootstrap style-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!--fONT sTYLE-->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@1,500&display=swap"
	rel="stylesheet">

<!--Bootstrap js-->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
	crossorigin="anonymous"></script>

<!--jquery-->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js"></script>


<!--js-->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/registration.js"></script>

<style type="text/css">
.upload-button {
	margin-top: 40%;
	margin-left: 74%;
	z-index: 200;
}
</style>

</head>
<body>

	<div>	
	<%@ include file="./includes/mainHeader.jsp"%>  
	</div>

	<!-------------------------------------
            Blocked List
   --------------------------------------->



	<nav style="-bs-breadcrumb-divider: '&gt;';" aria-label="breadcrumb">
		<ol class="breadcrumb alert alert-secondary">
			<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/views/MainPage.jsp"
				class="text-dark">Home</a></li>
			<li class="breadcrumb-item"><a href=""
				class="text-dark">Blocked</a></li>
		</ol>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<table class="table user-list">
				<thead>
					<tr>
					<th>&nbsp;</th>
						<th><span>Username</span></th>
						<th><span>Name</span></th>
						<th><span>Email</span></th>
						<th>&nbsp;</th>
						<th class="text-right"><span>Unblock</span></th>
					</tr>
				</thead>
				<tbody>


					<%
					for (User u : (List<User>) session.getAttribute("blockedList")) {
						System.out.println("Inside for loop for blocked list," + u.toString());
						JspWriter jout = out;

						jout.print("<tr><td>&nbsp;</td><td><span>" + u.getUserName() + "</span></td>" + "<td><span>" + u.getFullName() + "</span></td>"
						+ "<td><span>" + u.getEmail() + "</span></td><td>&nbsp;</td><td><span><form action='"
						+ request.getContextPath() + "/UnBlockServlet?blockId=" + u.getUserId()
						+ "' method='POST'><button type='submit' class='btn btn-warning'>Unblock</button></form></span></td><td>&nbsp;</td></tr>");
					}
					%>


				</tbody>
			</table>
		</div>
	</div>

	<div>	
	<%@ include file="./includes/searchModal.jsp"%>  
	</div>
<div>	
<%@ include file="./includes/mainFooter.jsp"%>  
</div>

</body>
</html>