<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1" import="com.hsbc.networking.model.User, java.util.List"%>

<!DOCTYPE html>
<html>
<head>
   <title>Search Result</title>

   <!--Stylesheets-->
   <link rel="stylesheet" type="text/css" href="css/homeStyle.css">

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

   <style type="text/css">
      .table>:not(caption)>*>* {
         border-bottom-width: 0px;
      }
   </style>


</head>
<body>

   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

       	  <!-------------------------------------
            HEADER
   --------------------------------------->

	<ul class="nav justify-content-center bg-secondary">
		<li class="nav-item"><a class="nav-link" aria-current="page"
			href="#">
				<h5 class="nav-logo">
					<i class="fa fa-google"></i>
				</h5>
		</a></li>
		<li class="nav-item"><a class="nav-link" href="#">
				<h5 class="nav-logo">
					<i class="fa fa-facebook"></i>
				</h5>
		</a></li>
		<li class="nav-item"><a class="nav-link" href="#">
				<h5 class="nav-logo">
					<i class="fa fa-twitter"></i>
				</h5>
		</a></li>
		<li class="nav-item"><a class="nav-link">
				<h5 class="nav-logo">
					<i class="fa fa-instagram"></i>
				</h5>
		</a></li>
		<li class="nav-item" style="margin-left: 50%"><a class="nav-link"
			onclick="Search()">
				<h5 class="nav-logo">
					<i class="fa fa-search"></i>
				</h5>
		</a></li>
		<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/LogoutServlet">
				<h5 class="nav-logo">Logout</h5>
		</a></li>
	</ul>

	<nav class="navbar navbar-dark bg-dark">

		<!-- Navbar content -->
		<div class="container-fluid">
			<a class="navbar-brand text-warning" href=""><img src="${pageContext.request.contextPath}/resources/images/mainLogo.png" style="max-width:15%;max-height:15%;"></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="${pageContext.request.contextPath}/views/MainPage.jsp">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="#about">About</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#download">Download</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#footer">Contact
							Us</a></li>
				</ul>
			</div>
		</div>
	</nav>


   <%-- <!-------------------------------------
            Search Results from Contacts
   --------------------------------------->

   <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
      <ol class="breadcrumb alert alert-secondary">
         <li class="breadcrumb-item"><a href="MainPage.html" class="text-dark">Home</a></li>
         <li class="breadcrumb-item"><a href="SearchResult.jsp" class="text-dark">Search Results</a></li>
      </ol>
   </nav>

   <div class="container-fluid">
      <div class="row">
         <div class="col-md-12">
            <h4 style="font-family: 'Raleway', sans-serif;">From Your Contacts</h4>
            <table class="table user-list">
               <thead>
                  <tr>
                     <th>&nbsp;</th>
                     <th><span>Name</span></th>
                     <th><span>Email</span></th>
                     <th><span>Block</span></th>
                     <th><span>Unfriend</span></th>
                  </tr>
               </thead>
               <tbody>
                  <%
                           for (User u : (List<User>) request.getAttribute("FilteredSearchResult")) {
                              //System.out.println("Inside for loop for Search list," + u.toString());
                              JspWriter jout = out;

                              jout.print("<tr><td>&nbsp;</td><td><span>" + u.getFullName() + "</span></td>"
                              + "<td><span>" + u.getEmail() + "</span></td><td><span><form action='" + request.getContextPath()
                              + "/BlockUser' method='POST'>"+
                              "<input type='hidden' id='blockId' name='blockId' value='"+u.getUserId()+"'>"+"<button class='btn btn-danger' type='submit' >Block</button></form></span></td>"
                              + "<td><span><form action='" + request.getContextPath() + "/SendRequestServlet"
                              + "' method='POST'><button class='btn btn-warning' type='submit'>Unfriend</button></form></span></td></tr>");
                           }
                           %>
               </tbody>
            </table>
         </div>
      </div> --%>

   <!-------------------------------------
            Friend List from Users
   --------------------------------------->

      <div class="row">
         <div class="col-md-12 mt-4">
            <h4 style="font-family: 'Raleway', sans-serif;">  From All Users</h4>
            <table class="table user-list">
               <thead>
                  <tr>
                     <th>&nbsp;</th>
                     <th><span>Name</span></th>
                     <th><span>Email</span></th>
                     <th><span>Block</span></th>
                     <th><span>Send Friend Request</span></th>
                  </tr>
               </thead>
               <tbody>
                  <%
                           for (User u : (List<User>) request.getAttribute("FilteredSearchResult")) {
                              //System.out.println("Inside for loop for Search list," + u.toString());
                              JspWriter jout = out;

                              jout.print("<tr><td>&nbsp;</td><td><span>" + u.getFullName() + "</span></td>"
                              + "<td><span>" + u.getEmail() + "</span></td><td><span><form action='" + request.getContextPath()
                              + "/BlockUser' method='POST'>"+
                              "<input type='hidden' id='blockId' name='blockId' value='"+u.getUserId()+"'>"+"<button class='btn btn-danger' type='submit' >Block</button></form></span></td>"
                              + "<td><span><form action='" + request.getContextPath() + "/SendRequestServlet"
                              + "' method='POST'><input type='hidden' id='frndId' name='frndId' value='"+u.getUserId()+"'><input type='text' id='sendMessage' name='sendMessage'><button class='btn btn-success' type='submit'>Send Request</button></form></span></td></tr>");
                           }
                           %>
               </tbody>
            </table>
         </div>

      </div>
   </div>

   <!-------------------------------------
            Search Modal
      --------------------------------------->


   <div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
         <div class="modal-content">
               <div class="modal-body">
               <form>
                     <div class="input-group mb-3">
                     <label class="input-group-text" for="inputGroupSelect01">Search By</label>
                     <select class="form-select" id="inputGroupSelect01">
                        <option selected>Choose...</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                     </select>
                  </div>
                     <div class="mb-3">
                        <input type="text" aria-label="Last name" class="form-control" placeholder="Search">   
                     </div>
               </form>
               </div>
               <div class="modal-footer">
               <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
               <button type="button" class="btn btn-warning">Search</button>
               </div>
         </div>
      </div>
   </div>



<!-------------------------------------
            fOOTER
   --------------------------------------->

	<footer class="container-fluid bg-dark text-light py-5">
		<div class="container" id="footer">
			<div class="row">
				<div class="col-md-6">
					<div class="row">
						<div class="col-md-6 ">
							<div class="logo-part">
								<img src="${pageContext.request.contextPath}/resources/images/mainLogo.png" style="max-width:60%;max-height:60%;">
							</div>
						</div>
						<div class="col-md-6 px-4">
							<h6>About Us</h6>
							<p>Add, Connect & Engage.</p>
							<a href="#about" class="text-warning" class="btn-footer">
								More Info </a><br> <a href="#footer" class="text-warning"
								class="btn-footer"> Contact Us</a>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="row">
						<div class="col-md-6 px-4">
							<h6>Help us</h6>
							<div class="row ">
								<div class="col-md-12">
									<ul>
										<li><a href="${pageContext.request.contextPath}/views/MainPage.jsp" class="text-warning"> Home</a></li>
										<li><a href="#about" class="text-warning"> About</a></li>
										<li><a href="#download" class="text-warning">
												Download</a></li>
										<li><a href="#footer" class="text-warning"> Contact
												Us</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div class="col-md-6 ">
							<h6>Contact Us</h6>
							<div class="social">
								<a href="#" class="p-2"> <i class="fa fa-google nav-logo"
									aria-hidden="true"></i>
								</a> <a href="#" class="p-2"> <i class="fa fa-twitter nav-logo"
									aria-hidden="true"></i>
								</a> <a href="#" class="p-2"> <i class="fa fa-facebook nav-logo"
									aria-hidden="true"></i>
								</a> <a href="#" class="p-2"> <i
									class="fa fa-instagram nav-logo" aria-hidden="true"></i>
								</a>
							</div>
							<form class="form-footer my-3">
								<input type="mail" placeholder="Your E-mail" name="mail">
								<input type="textbox" placeholder="Write to us" name="query">
								<input type="button" value="Go" class="btn btn-warning">
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>


<script type="text/javascript">

   function Search() {
      // body...
      $('#searchModal').modal('show');
   }

      
   </script>

</body>
</html>