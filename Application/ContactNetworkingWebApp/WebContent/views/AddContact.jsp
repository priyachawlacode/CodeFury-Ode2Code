<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"
   import="com.hsbc.networking.model.Contact,com.hsbc.networking.model.User, java.util.UUID" %>
 <%@ page isELIgnored = "false" %>
   

<!DOCTYPE html>
<html>
<head>
   <title>Add Contacts</title>
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
	response.sendRedirect(path + "/views/LoginJSP.jsp");
}
%>
   <!--Stylesheets-->
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/homeStyle.css">
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/RegistrationStyle.css">

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
   <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/registration.js"></script>

   <style type="text/css">
      .p-image{
         margin: 0px;
         position: relative;
      }
      .circle{
         position: relative;
      }
   </style>


</head>
<body>
	
   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   <% User u = (User)session.getAttribute("NewUser") ;%>
   
<div>	
	<%@ include file="./includes/mainHeader.jsp"%>  
	</div>

   <!-------------------------------------
            Add New Contact
   --------------------------------------->

   <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
      <ol class="breadcrumb alert alert-secondary">
         <li class="breadcrumb-item"><a href="MainPage.jsp" class="text-dark">Home</a></li>
         
      </ol>
   </nav>


   <div class="container-fluid">
      <div class="row">
         <div class="col-md-12">
            <form enctype='multipart/form-data' action="${pageContext.request.contextPath}/AddContactServlet" method="POST" >

                     <div class="row">
                        <div class="col-md-2">          
                           <div class="circle">
                            <!-- User Profile Image -->
                              <img class="profile-pic" src="https://cdn3.iconfinder.com/data/icons/avatars-round-flat/33/avat-01-512.png">
                           </div>
                           <div class="p-image">
                              <i class="fa fa-camera upload-button"></i>
                              <input class="file-upload" type="file" name="photo" id="photo" accept="image/*"/>
                           </div>
                      </div>
                     </div><br>

                     <div class="row">
                        <div class="col-sm-6">

                           <div class="form-group">
                              <label for="name">Full Name*</label> 
                              <input type="text" class="form-control" name="contactName" placeholder="Full Name" value="${NewUser.fullName}" required>
                           </div><br>

							
                           <% JspWriter jout = out;
                           Contact c = new Contact();
							c.setContactId(UUID.randomUUID().toString());
							 %>
						<input type="hidden" id="contactId" value="<% jout.print(c.getContactId());%>" name="contactId">

                           <div class="form-group">
                              <label for="email">Email address*</label> 
                              <input type="email" class="form-control" name="email" aria-describedby="emailHelp" placeholder="Enter email" value="<%=u.getEmail() %>" readonly="readonly" required> 
                              <small name="emailHelp" class="form-text text-muted">Your information is safe with us.</small>
                           </div><br>

                           <div class="form-group">
                              <label for="phnNo">Contact No</label> 
                              <input type="number" class="form-control" name="phnNo" placeholder="Contact number" value="${NewUser.contactNo}">
                           </div><br>

                           <div class="form-group">
                              <label for="gender">Gender : </label>

                              <input class="form-check-input" type="radio" name="gender" id="female" value="female" <% if(u.getGender()=='f'){ %>checked<% }%>>
                              <label class="form-check-label" for="female"> Female</label>
                              <br>

                              <input class="form-check-input" style="margin-left:13%;" type="radio" name="gender" id="male" value="male" <% if(u.getGender()=='m'){ %>checked<% }%>
                              >
                              <label class="form-check-label" for="male">Male</label><br>
                           
                              <input class="form-check-input" style="margin-left:13%;" type="radio" name="gender" id="other" value="other" <% if(u.getGender()=='o'){ %>checked<% }%>
                              >
                              <label class="form-check-label" for="other">Other</label>
                           </div><br>

                           <div class="form-group">
                              <label for="dob">Date of Birth</label> 
                              <input type="date" class="form-control" name="dob" placeholder="yyyy-mm-dd" value="${NewUser.dob}">
                           </div><br>

                           <div class="form-group">
                              <button type="submit" class="btn btn-warning">Add Contact</button>
                           </div>

                        </div>


                        <div class="col-sm-6">

                           <div class="form-group">
                              <label for="address">Address</label> 
                              <input type="text" class="form-control" name="address" placeholder="Address" value="${NewUser.address}">
                           </div><br>

                           <div class="form-group">
                              <label for="city">City</label> 
                              <input type="text" class="form-control" name="city" placeholder="city" value="${NewUser.city}">
                           </div><br>

                           <div class="form-group">
                              <label for="state">State</label> 
                              <input type="text" class="form-control" name="state" placeholder="state" value="${NewUser.state}">
                           </div><br>

                           <div class="form-group">
                              <label for="country">Country</label> 
                              <input type="text" class="form-control" name="country" placeholder="country" value="${NewUser.country}">
                           </div><br>

                           <div class="form-group">
                              <label for="company">Company</label> 
                              <input type="text" class="form-control" name="company" placeholder="company" value="${NewUser.company}">
                           </div>

                        </div>
                     </div>
            </form>
         </div>
      </div>
   </div>


  <div>	
	<%@ include file="./includes/searchModal.jsp"%>  
	</div>
 <div>	
 <br>
 <br>
<%@ include file="./includes/mainFooter.jsp"%>  
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
