<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="com.hsbc.networking.model.User,java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin</title>
<script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet" crossorigin="anonymous">
<!-- integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"  -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>
<!-- integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" -->

<style>
.nav-link:hover {
	color: #FADA5E;
}

.nav-link {
	color: white;
}

#button {
	color: yellow;
}
</style>
<script>
	history.pushState(null, null, null);
	window.addEventListener('popstate', function() {
		history.pushState(null, null, null);
	});
</script>
<%
response.setHeader("Cache-Control", "no-cache, must-revalidate");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
</head>
<body class="bg-secondary" style="background-color: black;">
	<%
	String path = request.getContextPath();
	%>
	<nav class="navbar navbar-dark bg-dark">
		<!-- Navbar content -->
		<div class="container-fluid">
			<a class="navbar-brand" href="#" style="color: #FADA5E;"><h3>Welcome
					Admin</h3></a>
			<ul class="nav ">
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="#"> <a href="${pageContext.request.contextPath}"
						+"/AdminLogOutServlet" id="button"><i
							class="fas fa-info-circle fa-lg"></i>Logout</a>
				</a></li>
			</ul>
		</div>
	</nav>

	<div class="container-fluid mb-4">
		<br>

		<%
		if (session.getAttribute("username") == null) {
			response.sendRedirect(path + "/views/AdminLogin.jsp");
		}
		%>
		<div class="row">
			<div class="col-md-6">

				<div class="card mb-3 bg-dark text-white" style="max-width: 540px;">
					<div class="row g-0">
						<div class="col-md-4">
							<img height="150px" width="175px"
								src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRZmo31GedbWaUo43LNqqiXBqQ72HhFuJKcpQ&usqp=CAU">
						</div>
						<div class="col-md-8  justify-content-end">
							<div class="card-body justify-content-center">
								<h5 class="card-title">
									<u>NAME</u>
								</h5>
								<h5>
									<%
									out.println(session.getAttribute("name"));
									%>
								</h5>
								<h5 class="card-title">
									<u>E-MAIL</u>
								</h5>
								<h5>
									<%
									out.println(session.getAttribute("emailId"));
									%>
								</h5>
								<h5 class="card-title">
									<u>PHONE NO</u>
								</h5>
								<h5>
									<%
									out.println(session.getAttribute("mobileNo"));
									%>
								</h5>
							</div>
						</div>
					</div>
				</div>

			</div>
			<div class="col-md-6">

				<div class="card mb-3 bg-dark text-white" style="max-width: 540px;">
					<div class="row g-0">
						<div class="col-md-4">
							<img height="150px" width="175px"
								src="https://www.seekpng.com/png/detail/44-443160_group-icon-user-icon-png-yellow.png">
						</div>
						<div class="col-md-8">
							<div class="card-body">
								<h5 class="card-title">Total Users</h5>
								<p class="card-text">
								<h5>
									<%
									out.println(session.getAttribute("TotalUsers"));
									%>
								</h5>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6">

				<!-- TABLE: LATEST ORDERS -->
				<div class="card  mb-3 bg-dark text-white">
					<div class="card-header border-transparent">
						<h5 class="card-title">
							Disable Users
							<button type="button" class="btn btn-tool text-white"
								onclick="collapse1()" style="margin-left: 44%">
								<i class="fas fa-minus"></i>
							</button>
						</h5>
					</div>
					<!-- /.card-header -->
					<div class="card-body p-0 disable-table-body">
						<div class="table-responsive">
							<table class="table  table-striped table-dark">
								<thead>
									<tr>
										<th scope="col">ID</th>
										<th scope="col">Country</th>
										<th scope="col">Disable</th>
									</tr>
								</thead>
								<tbody>
									<%
									
									List<User> disable = (List<User>) session.getAttribute("disableUsers");
									JspWriter jout = out;
									for(User u: disable){
										jout.print("<tr><th scope='row'>"+u.getUserId()+"</th>"
									+"<td>"+u.getCountry()+"</td>"
									+"<td><form action='"+path+"/DisableServlet' method='post'><input type='hidden' id='disableId' name='disableId' value='"+u.getUserId()+"'>"
									+"<button value='id' class='btn btn-danger'>Disable</button></form></td></tr>");
									} %>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->
			</div>


			<div class="col-md-6">

				<!-- TABLE: LATEST ORDERS -->
				<div class="card  mb-3 bg-dark text-white">
					<div class="card-header border-transparent">
						<h5 class="card-title">
							Delete Users
							<button type="button" class="btn btn-tool text-white"
								onclick="collapse2()" style="margin-left: 44%">
								<i class="fas fa-minus"></i>
							</button>
						</h5>
					</div>
					<!-- /.card-header -->
					<div class="card-body p-0 delete-table-body">
						<div class="table-responsive">
							<table class="table  table-striped table-dark">
								<thead>
									<tr>
										<th scope="col">ID</th>
										<th scope="col">Country</th>
										<th scope="col">Delete</th>
									</tr>
								</thead>
								<tbody>
								<%
									
									List<User> delete = (List<User>) session.getAttribute("deleteUsers");
									for(User u: disable){
										jout.print("<tr><th scope='row'>"+u.getUserId()+"</th>"
									+"<td>"+u.getCountry()+"</td>"
									+"<td><form action='"+path+"/DeleteServlet' method='post'><input type='hidden' id='deleteId' name='deleteId' value='"+u.getUserId()+"'>"
									+"<button value='id' class='btn btn-danger'>Delete</button></form></td></tr>");
									} %>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->

			</div>
		</div>
	</div>


	<script>
		var minimize1 = true;
		function collapse1() {
			if (minimize1 === false) {
				document.getElementsByClassName("disable-table-body")[0].style.display = 'none';
				minimize1 = true;
			} else {
				document.getElementsByClassName("disable-table-body")[0].style.display = 'block';
				minimize1 = false;
			}
		}
		var minimize2 = true;
		function collapse2() {
			if (minimize2 === false) {
				document.getElementsByClassName("delete-table-body")[0].style.display = 'none';
				minimize2 = true;
			} else {
				document.getElementsByClassName("delete-table-body")[0].style.display = 'block';
				minimize2 = false;
			}
		}
	</script>
</body>
</html>