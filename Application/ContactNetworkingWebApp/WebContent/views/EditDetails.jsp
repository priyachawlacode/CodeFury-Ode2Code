<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"
   import="com.hsbc.networking.model.Contact"%>

<!DOCTYPE html>
<html>
<head>
   <title>Edit Details</title>

   <!--Stylesheets-->
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/resources/css/homeStyle.css">

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

   <!--js-->
   <script type="text/javascript" src="js/registration.js"></script>
   
   <style type="text/css">
    
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


   <!-------------------------------------
            Edit Contact
   --------------------------------------->


   <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
      <ol class="breadcrumb alert alert-secondary">
         <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/views/MainPage.jsp" class="text-dark">Home</a></li>
         <li class="breadcrumb-item"><a href="" class="text-dark">Edit Details</a></li>
      </ol>
   </nav>

   <div class="modal fade" id="editModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" aria-hidden="true">
         <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
               <div class="modal-header border-bottom-0 justify-content-center mb-0" >
                  <h5 class="modal-title" id="exampleModalLabel">Edit Details</h5>
               </div>
               <form action="${pageContext.request.contextPath}/EditContactServlet" method="POST" >
                  <div class="modal-body mt-0">

                     <div class="row">
                        <div class="col-sm-12 justify-content-center">
                           <div class="form-group">
                              <label for="profileImage">Profile Image</label> 
                              <input type="file" class="form-control" name="profileImage" placeholder="profileImage">
                           </div>
                        </div>
                     </div>

                     <div class="row">
                        <div class="col-sm-6">

                           <div class="form-group">
                              <label for="name">Full Name</label> 
                              <input type="text" class="form-control" name="contactName" placeholder="Full Name"
                               required="required"  value="${contactToEdit.fullName }">
                           </div>

                           <div class="form-group">
                              <label for="email1">Email address</label> 
                              <input type="email" class="form-control" name="email" aria-describedby="emailHelp" 
                              value="${contactToEdit.email }" placeholder="Enter email" readonly="readonly"> 
                              <small name="emailHelp" class="form-text text-muted">Your information is safe with us.</small>
                           </div>

                           <div class="form-group">
                              <label for="phnNo">Contact No</label> 
                              <input type="number" class="form-control" name="phnNo" placeholder="Contact number" maxlength="10" minlength="10"
                              pattern="[789][0-9]{9}" value="${contactToEdit.contactNo }" required="required">
                           </div>
                           
                           <%String gender = "Male";
                           JspWriter jout = out;
                           Contact c = (Contact)session.getAttribute("contactToEdit");
                           request.setAttribute("contactId", c.getContactId());
                         System.out.println("Inside jout "+c.getContactId());
						if (c.getGender() == 'f') {
							gender = "Female";
						} else if (c.getGender() == 'o') {
							gender = "Other";
						}
							 %>
						<input type="hidden" id="contactId" value="<% jout.print(c.getContactId());%>" name="contactId">
	
                           <div class="form-check ms-4">
                              <label class="form-check-label">Gender</label>
                           </div><br>
							
                               <div class="form-check ms-4">
                                 <input class="form-check-input" type="text" name="gender" id="gender" 
									value="<% jout.print(gender);%>">
                  
                              </div>
                        
                            <!--   <div class="form-check ms-4">
                                 <input class="form-check-input" type="radio" name="gender" id="male" value="male" checked>
                                 <label class="form-check-label" for="male">Male</label>
                              </div>
                        
                              <div class="form-check ms-4">
                                 <input class="form-check-input" type="radio" name="gender" id="other" value="other">
                                 <label class="form-check-label" for="other">Other</label>
                              </div><br>  -->

                        </div>


                        <div class="col-sm-6">

                           <div class="form-group">
                              <label for="dob">Date of Birth</label> 
                              <input type="date" class="form-control" name="dob" placeholder="dd-mm-yyyy" 
                              value="${contactToEdit.dob }" readonly="readonly">
                           </div>

                           <div class="form-group">
                              <label for="address">Address</label> 
                              <input type="text" class="form-control" name="address" placeholder="Address"
                              value="${contactToEdit.address }" required="required">
                           </div>

                           <div class="form-group">
                              <label for="city">City</label> 
                              <input type="text" class="form-control" name="city" placeholder="city"
                              value="${contactToEdit.city }" required="required">
                           </div>

                           <div class="form-group">
                              <label for="state">State</label> 
                              <input type="text" class="form-control" name="state" placeholder="state"
                              value="${contactToEdit.state }" required="required">
                           </div>

                           <div class="form-group">
                              <label for="country">Country</label> 
                              <input type="text" class="form-control" name="country" placeholder="country"
                              value="${contactToEdit.country }" required="required">
                           </div>

                           <div class="form-group">
                              <label for="company">Company</label> 
                              <input type="text" class="form-control" name="company" placeholder="company"
                              value="${contactToEdit.company }" required="required">
                           </div>

                        </div>
                     </div>
                  </div>


                  <div class="modal-footer border-top-0 d-flex justify-content-center">
                     <button type="submit" class="btn btn-success">Save Changes</button>
                  </div>
               </form>
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

   $('document').ready(function() {
      $('#editModal').modal('show');
   });

   function Search() {
      // body...
      $('#searchModal').modal('show');
   }

      
   </script>

</body>
</html>
