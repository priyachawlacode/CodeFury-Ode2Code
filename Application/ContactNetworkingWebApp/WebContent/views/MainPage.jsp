<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.hsbc.networking.model.User,java.util.Base64"%>
<!DOCTYPE html>
<html>
<head>

<title>Main Page</title>

<!-- <script type="text/javascript">
        function preventBack() { window.history.forward(); }
        setTimeout("preventBack()", 0);
        window.onunload = function () { null };
    </script> -->
    
 <script>
	history.pushState(null, null, null);
	window.addEventListener('popstate', function() {
		history.pushState(null, null, null);
	});
</script>
<%
String path = request.getContextPath();
response.setHeader("Cache-Control", "no-cache, must-revalidate");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

if (session.getAttribute("currentUser") == null) {
	response.sendRedirect("/views/LoginJSP.jsp");
}
%>

<!--Stylesheets-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/mainStyle.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/homeStyle.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/RegistrationStyle.css">

<!--Stylesheets-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/mainStyle.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/homeStyle.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/RegistrationStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/registration.js"></script>

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

.col-sm-6 {
    flex: 0 0 auto;
    width: 100%;
}
</style>


</head>
<body >

	<div>	
	<%@ include file="./includes/mainHeader.jsp"%>  
	</div>


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
					<a href="${pageContext.request.contextPath}/views/VerifyEmail.jsp" class="moving-link">
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
				<p style="color:red">${error}</p>
<form class='row g-3' method='post' enctype='multipart/form-data' action="<%=request.getContextPath()%>/UpdateUserServlet">
				<input type='hidden' id='profileFlag' name="profileFlag" value='true'>
				<div class="row">
					<div class="col-sm-12">
						<div class="row  justify-content-center">
							<div class="circle ps-0" style="width: 175px">
						
						<% 
							String url = "https://cdn3.iconfinder.com/data/icons/avatars-round-flat/33/avat-01-512.png";
							User u =(User)session.getAttribute("currentUser");
							if(u == null){
								request.getRequestDispatcher("http://localhost:8080/ContactNetworkingWebApp/views/LoginJSP.jsp");
							}
							if(!(u.getProfileImage()==null) && u.getProfileImage().length()>0 ){
								int blobLength = (int) u.getProfileImage().length();  
								byte[] blobAsBytes = u.getProfileImage().getBytes(1, blobLength);
								//System.out.println(Base64.getEncoder().encodeToString(blobAsBytes));
								
								System.out.println(session.getAttribute("profileFlag"));
								url="data:image/jpg;base64," + Base64.getEncoder().encodeToString(blobAsBytes);
							} 
							JspWriter jout = out;
							jout.print("<img id='photo' class='profile-pic' src='"+url+"'>");%>
							
							</div>

							<i class="fa fa-camera upload-button"></i>
                            <input class="file-upload" type="file" name="photo" 	accept="image/*"/>
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
						//System.out.println("User info on mainpage: "+u.toString());
						jout.print("<input type='hidden' class='form-control'value='" + u.getUserId()
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
								+ "<br><div class='form-check'><label for='dob' class='form-label'>"
								+ "Date of Birth</label><input type='date' class='form-control' id='dob' name='dob' placeholder='yyyy-MM-dd' value='"
								+ u.getDob() + "' readonly></div><br>"
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
								+ "<button type='submit' class='btn btn-warning m-3' value='Submit' onclick='confirmSubmit();'>Edit Details</button></div>");
						%>
						</div>
					</div>
</form>					
				</div>
			</div>
		</div>

		</div>
	</div>

	<div>	
	<%@ include file="./includes/birthdayModal.jsp"%>  
	</div>
	<div>	
	<%@ include file="./includes/searchModal.jsp"%>  
	</div>
	<div>	
	<%@ include file="./includes/mainFooter.jsp"%>  
	</div>
<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/js/confetti.js"></script>
  <script type="text/javascript">
  
  
    $('document').ready(function() {
      var isBirthday = ${sessionScope.isBirthday};
      if (isBirthday && ${sessionScope.loadCount}==0) {
        confetti.start()
        $('#bdayModal').modal('show');
        setTimeout(function() {
                confetti.stop()
            }, 3000);
      }
      <% session.setAttribute("loadCount", 1); %>
       
    });
  
    
  </script>

	<%-- <script type="text/javascript">
	
	
		$('document').ready(function() {
			var isBirthday = ${sessionScope.isBirthday};
			if (isBirthday && ${sessionScope.loadCount}==0) {
				$('#bdayModal').modal('show');
			}
			<% session.setAttribute("loadCount", 1); %>
		});
	
		
	</script> --%>
	
	<script type="text/javascript">

   
   $(document).ready(function () {
	   var readURL = function (input) {
	     if (input.files && input.files[0]) {
	       var reader = new FileReader();

	       reader.onload = function (e) {
	         $(".profile-pic").attr("src", e.target.result);
	         $('#profileFlag').val("false");
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