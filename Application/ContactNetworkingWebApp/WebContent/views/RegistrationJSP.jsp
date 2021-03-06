<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>

<!--Stylesheets-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/RegistrationStyle.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/homeStyle.css">

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

</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/RegistrationValidate.js"></script>
<body>

<div>	
<%@ include file="./includes/homeHeader.jsp"%>  
</div>

	<!-------------------------------------
        REGISTRATION FORM
  --------------------------------------->

	<div class="container-fluid  mt-4 pb-4 mb-4 text-center">

		<h2 style="font-family: 'Raleway', sans-serif;" class="p-4">Add
			Your Details</h2>
		<div class="" bg-danger>${error}</div>


		<p style="font-family: 'Raleway', sans-serif;">
			Already have an account? <a
				href="${pageContext.request.contextPath}/views/LoginJSP.jsp"
				class="text-warning">Login Here!</a>
		</p>

		<div class="row text-start">
			<form class="row g-3" method="post"
				action="${pageContext.request.contextPath}/RegistrationServlet" enctype='multipart/form-data' >
				
				<div class="col-md-2">
					<div class="circle">
						<!-- User Profile Image -->
						<img class="profile-pic"
							src="https://cdn3.iconfinder.com/data/icons/avatars-round-flat/33/avat-01-512.png">
					</div>
					<div class="p-image">
						<i class="fa fa-camera upload-button"></i> <input id="photo"
							class="file-upload" type="file" name="photo"  accept="image/*" />
					</div>
				</div>

				<div class="col-md-5">

					<div class="form-check">
						<label for="name" class="form-label">Name</label> <input
							type="text" class="form-control" id="name"
							placeholder="Full Name" name="full_name" onblur="validateName();" required="required">
							<span style = "color: red;" id="namerror"></span>
					</div>
					<br>

					<div class="form-check">
						<label for="username" class="form-label">User Name</label> <input
							type="text" class="form-control" id="username"
							placeholder="User Name" name="username" onblur="validateUserName();" required="required">
							<span style = "color: red;" id="usernameerror"></span>
					</div>
					<br>

					<div class="form-check">
						<label for="email" class="form-label">Email</label> <input
							type="email" class="form-control" id="email" name="email"
							placeholder="email@example.com" onblur="validateEmail();" required="required">
							<span style = "color: red;" id="emailerror"></span>
					</div>
					<br>

					<div class="form-check">
						<label for="password" class="form-label">Password</label> <input
							type="password" class="form-control" id="password" onkeyup='check();'
							placeholder="Password" name="password" required="required">
					</div>
					<br>

					<div class="form-check">
						<label for="cpassword" class="form-label">Confirm Password</label>
						<input type="password" class="form-control" id="cpassword" onkeyup='check();'
							placeholder="Confirm Password" name="cpassword" required="required">
							<span id='message'></span>
					</div>
					<br>

					<div class="form-check">
						<label for="contact" class="form-label">Contact Number</label> <input
							type="tel" class="form-control" id="contact" required="required" maxlength="10" minlength="10" 
							placeholder="Contact Number" name="contact" pattern="[789][0-9]{9}">
					</div>
					<br>

					<div class="form-check">
						<label for="dob" class="form-label">Date of Birth</label> <input
							type="date" class="form-control" id="dob"
							placeholder="dd-MM-yyyy" name="dob" required="required"> 
					</div>

				</div>

				<div class="col-md-5 mt-4">

					<div class="form-check ms-4">
						<input class="form-check-input" type="radio" name="gender"
							id="female" value="female"> <label
							class="form-check-label" for="female">Female</label>
					</div>

					<div class="form-check ms-4">
						<input class="form-check-input" type="radio" name="gender"
							id="male" value="male" checked> <label
							class="form-check-label" for="male">Male</label>
					</div>

					<div class="form-check ms-4">
						<input class="form-check-input" type="radio" name="gender"
							id="other" value="other"> <label class="form-check-label"
							for="other">Other</label>
					</div>
					<br>

					<div class="form-check">
						<label for="address" class="form-label">Address</label> <input
							type="text" class="form-control" id="address"
							placeholder="Address" name="address" required="required">
					</div>
					<br>

					<div class="form-check">
						<label for="city" class="form-label">City</label> <input
							type="text" class="form-control" id="city" placeholder="City"
							name="city" required="required">
					</div>
					<br>

					<div class="form-check">
						<label for="state" class="form-label">State</label> <input
							type="text" class="form-control" id="state" placeholder="State"
							name="state" required="required">
					</div>
					<br>

					<div class="form-check">
						<label for="country" class="form-label">Country</label> <input
							type="text" class="form-control" id="country"
							placeholder="Country" name="country" required="required">
					</div>
					<br>

					<div class="form-check">
						<label for="company" class="form-label">Company</label> <input
							type="text" class="form-control" id="company"
							placeholder="company" name="company" required="required">
					</div>
					<br>

					<button type="submit" class="btn btn-warning m-3" value="Submit"
					onclick="confirmSubmit();">Register</button>
				</div>
			</form>
		</div>
	</div>

<div>	
<%@ include file="./includes/homeFooter.jsp"%>  
</div>
<script type="text/javascript">

   
   $(document).ready(function () {
	   var readURL = function (input) {
	     if (input.files && input.files[0]) {
	       var reader = new FileReader();

	       reader.onload = function (e) {
	         $(".profile-pic").attr("src", e.target.result);
	       };

	       reader.readAsDataURL(input.files[0]);
	     }
	   };

	   $(".file-upload").on("change", function () {
	     readURL(this);
	   });

	   $(".upload-button").on("click", function () {
	     $(".file-upload").click();
	   });
	 });   
   
   </script>

</body>
</html>