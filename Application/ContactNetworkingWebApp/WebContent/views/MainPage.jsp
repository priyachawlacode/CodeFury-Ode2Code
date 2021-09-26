<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.hsbc.networking.model.User,java.util.Base64"%>
<!DOCTYPE html>
<html>
<head>
<title>Main Page</title>

<!--Stylesheets-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/mainStyle.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/homeStyle.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/RegistrationStyle.css">

<!--Stylesheets-->
<link rel="stylesheet" type="text/css" href="css/mainStyle.css">
<link rel="stylesheet" type="text/css" href="css/homeStyle.css">
<link rel="stylesheet" type="text/css" href="css/RegistrationStyle.css">
<script type="text/javascript" src="js/registration.js"></script>

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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/registrationValidate.js"></script>
	

<style type="text/css">
.upload-button {
	margin-top: 20%;
	margin-bottom: 10%;
	margin-left: 70%;
	z-index: 200;
}
</style>

</head>
<body >

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
		<li class="nav-item" style="margin-left: 40%"><a class="nav-link"
			onclick="Search()">
				<h5 class="nav-logo">
					<i class="fa fa-search"></i>
				</h5>
		</a></li>
		<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/LogoutServlet">
				<h5 class="nav-logo">Logout</h5>
		</a></li>
		<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/DeactivateAccServlet">
				<h5 class="nav-logo">Deactivate</h5>
		</a></li>
	</ul>

	<nav class="navbar navbar-dark bg-dark">

		<!-- Navbar content -->
		<div class="container-fluid">
			<a class="navbar-brand text-warning" href="#"><h2>Navbar</h2></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#home">Home</a></li>
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
            WELCOME BANNER
   --------------------------------------->

	<nav style="-bs-breadcrumb-divider: '&gt;';" aria-label="breadcrumb">
		<ol class="breadcrumb alert alert-secondary">
			<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/views/MainPage.jsp" class="text-dark">Home</a></li>
		</ol>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-7" style="margin-bottom: 10%">
				<div class="moving-div">
					<a href="${pageContext.request.contextPath}/views/AddContact.jsp" class="moving-link">
						<h4 style="font-family: 'Raleway', sans-serif;" class="	text-dark m-3 p-4">Add Contacts</h4>
					</a>
				</div>
				<div class="moving-div">
					<a href="${pageContext.request.contextPath}/ViewContactServlet" class="moving-link">
						<h4 style="font-family: 'Raleway', sans-serif;" class="	text-dark m-3 p-4">View Contacts</h4>
					</a>
				</div>
				<div class="moving-div">
					<a href="${pageContext.request.contextPath}/FriendListServlet" class="moving-link">
						<h4 style="font-family: 'Raleway', sans-serif;" class="	text-dark m-3 p-4">Friend List</h4>
					</a>
				</div>
				<div class="moving-div">
					<a href="${pageContext.request.contextPath}/FriendRequestServlet" class="moving-link">
						<h4 style="font-family: 'Raleway', sans-serif;" class="	text-dark m-3 p-4">Friend Requests</h4>
					</a>
				</div>
				<div class="moving-div">
					<a href="${pageContext.request.contextPath}/BlockedListServlet" class="moving-link">
						<h4 style="font-family: 'Raleway', sans-serif;" class="	text-dark m-3 p-4">Blocked List</h4>
					</a>
				</div>
			</div>

			<div class="col-md-5" style="margin-left:-20%;">
				<div class="row"><div class="bg-danger">${error}</div></div>

				<div class="row">
					<div class="col-sm-12">
						<div class="row  justify-content-center">
							<div class="circle ps-0" style="width: 175px">

						<% 
							String url = "https://cdn3.iconfinder.com/data/icons/avatars-round-flat/33/avat-01-512.png";
							User u =(User)session.getAttribute("currentUser");
							if(!(u.getProfileImage()==null) && u.getProfileImage().length()>0 ){
								int blobLength = (int) u.getProfileImage().length();  
								byte[] blobAsBytes = u.getProfileImage().getBytes(1, blobLength);
								//System.out.println(Base64.getEncoder().encodeToString(blobAsBytes));
								
								url="data:image/jpg;base64," + Base64.getEncoder().encodeToString(blobAsBytes);
							} 
							JspWriter jout = out;
							jout.print("<img id='photo' class='profile-pic' src='"+url+"'>");%>
							
							</div>

							<i class="fa fa-camera upload-button"></i>
                            <input class="file-upload" type="file" name="file" 	accept="image/*"/>
						</div>
					</div>
				</div>

				<div class="row mt-4">
					<div class="col-sm-12">
						<%
						
						
						String gender = "Male";
						if (u.getGender() == 'f') {
							gender = "Female";
						} else if (u.getGender() == 'o') {
							gender = "Other";
						}

						jout.print("<form class='row g-3' method='post' action='"+request.getContextPath()+"/UpdateUserServlet'>"
								
								+"<input type='hidden' class='form-control'value='" + u.getUserId()
								+ "' id='userId' name='userId' >"
								+"<div class='col-sm-6'>"
								+ "<div class='form-check'><label for='username' class='form-label'>Username</label><input "
								+ "type='text' class='form-control'value='" + u.getUserName()
								+ "' id='username'placeholder='username' name='userName' readonly> </div>"
								+ "<div class='form-check'><label for='name' class='form-label'>Name</label><input "
								+ "type='text' class='form-control'value='" + u.getFullName()
								+ "' id='fullName'placeholder='Full Name' name='fullName'>"
								+ "</div>"
								+"<div class='form-check'><label for='emailId' class='form-label'>EmailID</label><input "
								+ "type='text' class='form-control'value='" + u.getEmail()
								+ "' id='emailId'placeholder='email@example.com' name='emailId' readonly> </div>"
								+"<div class='form-check'><label for='contactNo' class='form-label'>Contact No</label><input "
								+ "type='text' class='form-control'value='" + u.getContactNo()
								+ "' id='contactNo'placeholder='contact No' name='contactNo'></div>"
								+ "<br><div class='form-check'><label for='dob' class='form-label'"
								+ ">Date of Birth</label><input type='date' class='form-control' id='dob'  placeholder='dd-mm-yyyy' value='"
								+ u.getDob() + "' name='dob' readonly></div><br>"
								+ "<div class='form-check'><label for='gender' class='form-label'>Gender</label><input "
								+ "type='text' class='form-control'value='" + gender
								+ "' id='gender'placeholder='gender' name='gender' readonly> </div>"
								+ "<br></div><div class='col-sm-6'><div class='form-check'><label for='address' class='form-label'> Address" + "</label><input type='text' "
								+ "class='form-control' id='address'	placeholder='Address' class='address' name='address'value='"
								+ u.getAddress() + "'></div><br>"
								+ "<div class='form-check'><label for='city' class='form-label'>" + "City"
								+ "</label><input type='text' class='form-control' id='city'" + " placeholder='City' name='city'value='"
								+ u.getCity() + "'></div><br><div class='form-check'><label for='state' class='form-label'>" + "State"
								+ "</label><input type='text' class='form-control'value='" + u.getState()
								+ "' id='state' placeholder='State' name='state'></div><br>"
								+ "<div class='form-check'><label for='country' class='form-label'>Country</label><input	type='text' class='form-control' id='country'"
								+ "placeholder='Country' name='country' value='" + u.getCountry()
								+ "'></div><br><div class='form-check'><label for='company' class='form-label'>Company</label>"
								+ "<input type='text' class='form-control' id='company' placeholder='company' name='company' value='"
								+ u.getCompany() + "'></div><br>"
								+ "<button type='submit' class='btn btn-warning m-3' value='Submit' onclick='confirmSubmit();'>Edit Details</button></div></form>");
						%>
						</div>
					</div>
				</div>
			</div>
		</div>

		</div>
	</div>



	<!-------------------------------------
            Search Modal
   	--------------------------------------->


	<div class="modal fade" id="searchModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<form action="${pageContext.request.contextPath}/SearchServlet"
						method="POST">
						<div class="input-group mb-3">
							<label class="input-group-text" for="inputGroupSelect01">Search
								By</label> <select name="type" class="form-select" id="type">
								<option selected>Choose...</option>
								<option value="username">Username</option>
								<option value="emailId">Email</option>
								<option value="city">City</option>
								<option value="state">State</option>
								<option value="country">Country</option>
								<option value="company">Company</option>
							</select>
						</div>
						<div class="mb-3">
							<input type="text" name="value" aria-label="Last name"
								class="form-control" placeholder="Search">
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Search</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>






	<!-------------------------------------
            Birthday Card Modal
   	--------------------------------------->

	<!-- Modal -->
	<div class="modal fade" id="bdayModal" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel">Wishing you a
						very Happy Birthday</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<img
						src="https://raw.githubusercontent.com/DenverCoder1/Responsive-Birthday-Card/main/birthday.svg"
						alt="birthday" class="birthday">
				</div>
				<div class="modal-footer text">
					<p>We hope you have a wonderful birthday</p>
					<p class="muted">- Contact Pool</p>
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
				var isBirthday = ${sessionScope.isBirthday};
				if (isBirthday) {
					$('#bdayModal').modal('show');
				}
			});

			function Search() {
				// body...
				$('#searchModal').modal('show');
			}
		</script>
</body>
</html>