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
   <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/registration.js"></script>
   
   <style type="text/css">
    
   </style>

</head>
<body>

   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

   	 <div>	
	<%@ include file="./includes/mainHeader.jsp"%>  
	</div>
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
               <form action="${pageContext.request.contextPath}/EditContactServlet" enctype='multipart/form-data' method="POST" >
                  <input name="contactFlag" id="contactFlag" value="true" type="hidden">
                  <div class="modal-body mt-0">

                     <div class="row">
                        <div class="col-sm-12 justify-content-center">
                           <div class="form-group">
                              <label for="profileImage">Profile Image</label> 
                             <input class="file-upload" type="file" name="photo" 	accept="image/*"/>
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
                                 <input class="form-check-input" type="radio" name="gender" id="gender" checked>
                                 <label class="form-check-label"><% jout.print(gender);%></label>
                  
                              </div>
                        
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

 <div>	
	<%@ include file="./includes/searchModal.jsp"%>  
	</div>

<div>	
<%@ include file="./includes/mainFooter.jsp"%>  
</div>


<script type="text/javascript">

   $('document').ready(function() {
      $('#editModal').modal('show');
   });
   
   $(document).ready(function () {
	   var readURL = function (input) {
	     if (input.files && input.files[0]) {
	       var reader = new FileReader();

	       reader.onload = function (e) {
	         $(".profile-pic").attr("src", e.target.result);
	         $('#contactFlag').val("false");
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
