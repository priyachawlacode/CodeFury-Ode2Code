<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="com.hsbc.networking.model.User,java.util.List"%>
<%
	String path = request.getContextPath();
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin</title>

<!--Stylesheets-->
   <link rel="stylesheet" type="text/css" href='<%=path%>+"/resources/css/homeStyle.css"'>

	 <!--Bootstrap style-->
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

   <!--fONT sTYLE-->
   <link rel="preconnect" href="https://fonts.googleapis.com">
   <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
   <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@1,500&display=swap" rel="stylesheet">

   <!--Bootstrap js-->
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>

   <!--jquery-->
   <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

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
</head>
<body class="bg-secondary">
	

   <nav class="navbar navbar-dark bg-dark">
		<!-- Navbar content -->
		<div class="container-fluid">
			<a class="navbar-brand" href="#" style="color: #FADA5E;"><h3>Welcome
					Admin</h3></a>
			<ul class="nav ">
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="#"> <h5><a href="${pageContext.request.contextPath}"
						+"/AdminLogOutServlet" id="button" style="color: #FADA5E;"><i
							class="fa fa-sign-out" ></i>Logout</a></h5>
				</a></li>
			</ul>
		</div>
	</nav>

   <!-------------------------------------
            Admin Dashboard
   --------------------------------------->

	<div class="container-fluid mb-4">
		<br>

		<%
		if (session.getAttribute("username") == null) {
			response.sendRedirect(path + "/views/AdminLogin.jsp");
		}
		%>
		<div class="row">
			<div class="col-md-6">

				<div class="card mb-3 bg-dark text-light" style="max-width: 540px;">
					<div class="row g-0">
						<div class="col-md-4">
							<img style="margin-top:18%;margin-left:2%;'" height="150px" width="150px"
								src=<%out.println(session.getAttribute("url")); %>>
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

				<div class="card mb-3 bg-dark text-light" style="max-width: 540px;">
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
				<div class="card  mb-3 bg-dark text-light">
					<div class="card-header border-transparent">
						<h5 class="card-title">
							Disable Users
							<button onclick = "DisableSelected()" class="btn btn-danger" style="margin-left: 70%">Disable Selected</button>
							
						</h5>
					</div>
					<!-- /.card-header -->
					<div class="card-body p-0 disable-table-body">
						<div class="table-responsive">
						<form id="DisableForm">
						<input id="disValue" name="disValue" type='hidden'>
						</form>
							<table class="table  table-striped table-dark">
							
								<thead>
									<tr>
										<th scope="col">Select</th>
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
										jout.print("<tr><td><input type='checkbox'value="+u.getUserId()+" class='disable'style='width: 20px;height: 20px;'/></td><td >"+u.getUserId()+"</td>"
									+"<td>"+u.getCountry()+"</td>"
									+"<td><form action='"+path+"/DisableServlet' method='post'><input type='hidden' id='disableId' name='disableId' value='"+u.getUserId()+"'>"
									+"<button value='id' class='btn btn-warning'>Disable</button></form></td></tr>");
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
				<div class="card  mb-3 bg-dark text-light">
					<div class="card-header border-transparent">
						<h5 class="card-title">
							Delete Users
							<button onclick = "DeleteSelected()" class="btn btn-danger" style="margin-left: 70%">Delete Selected</button>
							
						</h5>
					</div>
					<!-- /.card-header -->
					<div class="card-body p-0 delete-table-body">
						<div class="table-responsive">
						<form id="DeleteForm">
						<input id="delValue" name="delValue" type='hidden'>
						</form>
							<table class="table  table-striped table-dark">
								<thead>
									<tr>
										<th scope="col">Select</th>
										<th scope="col">ID</th>
										<th scope="col">Country</th>
										<th scope="col">Delete</th>
									</tr>
								</thead>
								<tbody>
								<%
									
									List<User> delete = (List<User>) session.getAttribute("deleteUsers");
									for(User u: delete){
										jout.print("<tr><td><input type='checkbox'value="+u.getUserId()+" class='delete'style='width: 20px;height: 20px;'/></td><td>"+u.getUserId()+"</td>"
									+"<td>"+u.getCountry()+"</td>"
									+"<td><form action='"+path+"/DeleteServlet' method='post'><input type='hidden' id='deleteId' name='deleteId' value='"+u.getUserId()+"'>"
									+"<button value='id' class='btn btn-warning'>Delete</button></form></td></tr>");
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
	function callDeleteServlet(deleteList)
	{
	 document.getElementById("DeleteForm").action="${pageContext.request.contextPath}/DeleteBatch";
	 document.getElementById("delValue").setAttribute('value',deleteList);
	 document.getElementById("DeleteForm").method = "post";
	 document.getElementById("DeleteForm").submit();}
	
	function callDisableServlet(disableList)
	{
	 document.getElementById("DisableForm").action="${pageContext.request.contextPath}/DisableBatch";
	 document.getElementById("disValue").setAttribute('value',disableList);
	 document.getElementById("DisableForm").method = "post";
	 document.getElementById("DisableForm").submit();}
		
		function DisableSelected() {
        	//Create an Array.
        	var selected = new Array();
 
        	//Reference all the CheckBoxes in Table.
        	var chks = document.getElementsByClassName("disable");
 
        	// Loop and push the checked CheckBox value in Array.
        	for (var i = 0; i < chks.length; i++) {
            	if (chks[i].checked) {
                	selected.push(chks[i].value);
            	}
        	}
 
        	callDisableServlet(selected);
    	};
      
      	function DeleteSelected() {
        	//Create an Array.
        	var selected = new Array();
 
        	//Reference all the CheckBoxes in Table.
        	var chks = document.getElementsByClassName("delete");
 
        	// Loop and push the checked CheckBox value in Array.
        	for (var i = 0; i < chks.length; i++) {
            	if (chks[i].checked) {
                	selected.push(chks[i].value);
            	}
        	}
 			
        	callDeleteServlet(selected);
        	
    	};
      
	</script>
</body>
</html>