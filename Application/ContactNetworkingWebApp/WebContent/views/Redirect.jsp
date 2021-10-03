<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1" import="com.hsbc.networking.model.User"%>

<!DOCTYPE html>
<html>
<head>
   <title>Redirect</title>

   <!--Stylesheets-->
   <link rel="stylesheet" type="text/css" href="css/homeStyle.css">
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/homeStyle.css">

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
      #modal1, #modal2{
         pointer-events: none;}

      .modal-dialog{
         pointer-events: all;
      }
      .card{
      	border: 0px;
      }
      .card:hover{
      	transform: scale(1.1);
      }
      a{
      	text-decoration: none;
      }
   </style>

</head>
<body>

   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>	
	<%@ include file="./includes/mainHeader.jsp"%>  
	</div>
   <!-------------------------------------
            Redirect
   --------------------------------------->


   <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
      <ol class="breadcrumb alert alert-secondary">
         <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/views/MainPage.jsp" class="text-dark">Home</a></li>
         <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/views/Redirect.jsp" class="text-dark">Redirect</a></li>
      </ol>
   </nav>


   <!-------------------------------------
            Modal 1 - ContactMailExists
   --------------------------------------->



   <div class="modal fade" id="modal1" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" aria-hidden="true">
         <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
               <div class="modal-header border-bottom-0 justify-content-center">

                	<div class="card-group">
  					<div class="card">
  						<a href="${pageContext.request.contextPath}/views/AddContact.jsp">
    					<div class="card-body text-center text-dark">
    						<h1><i class="fa fa-address-card" style="color: green;" aria-hidden="true"></i></h1>
      						<h5 class="card-title" style="font-family: 'Raleway', sans-serif;">Add Contact</h5>
      						<p>Contact Exists. Continue to Add to Contact.</p>
    					</div>
    					</a>
  					</div>

  					
  					<div class="card">
  						<a href="${pageContext.request.contextPath}/views/EditDetails.jsp">
    					<div class="card-body text-center text-dark">
    						<h1><i class="fa fa-pencil-square-o" style="color: blue;" aria-hidden="true"></i></h1>
      						<h5 class="card-title" style="font-family: 'Raleway', sans-serif;">Edit Contact</h5>
      						<p>Contact Exists. Want to Edit Contact?</p>
    					</div>
    					</a>
  					</div>

  					
  					<div class="card">
  						<a href="${pageContext.request.contextPath}/views/MainPage.jsp">
    					<div class="card-body text-center text-dark">
    						<h1><i class="fa fa-times-circle" style="color: red;" aria-hidden="true"></i></h1>
      						<h5 class="card-title" style="font-family: 'Raleway', sans-serif;">Abort</h5>
      						<p>Abort Any Operation. Return to main Page.</p>
    					</div>
    					</a>
  					</div>

				   </div>
               </div>
               
            </div>
         </div>
      </div>


    <!-------------------------------------
            Modal 2 - UserMailIdExists
   --------------------------------------->


    <div class="modal fade" id="modal2" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" aria-hidden="true">
         <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
               <div class="modal-header justify-content-center">

                  <div class="card-group">

  					<div class="card">
  						<a href="${pageContext.request.contextPath}/views/AddContact.jsp">
    					<div class="card-body text-center text-dark">
    						<h1><i class="fa fa-address-card" style="color: green;" aria-hidden="true"></i></h1>
      						<h5 class="card-title" style="font-family: 'Raleway', sans-serif;">Add Contact</h5>
      						<p>User Exist. Continue to Add to Contact.</p>
    					</div>
    					</a>
  					</div>

  					<%User u =(User)session.getAttribute("NewUser"); %>
  					<div class="card">
  						<a href="${pageContext.request.contextPath}/SendRequestServlet?frndId=<%=u.getUserId()%>&sendMessage='Hi there!'">
    					<div class="card-body text-center text-dark">
    						<h1><i class="fa fa-plus" style="color: blue;" aria-hidden="true"></i></h1>
      						<h5 class="card-title" style="font-family: 'Raleway', sans-serif;">Send Request</h5>
      						<p>User Exist. Want to Send Friend Request?</p>
    					</div>
    					</a>
  					</div>

  					
  					<div class="card">
  						<a href="${pageContext.request.contextPath}/views/MainPage.jsp">
    					<div class="card-body text-center text-dark">
    						<h1><i class="fa fa-times-circle" style="color: red;" aria-hidden="true"></i></h1>
      						<h5 class="card-title" style="font-family: 'Raleway', sans-serif;">Abort</h5>
      						<p>Abort Any Operation. Return to main Page.</p>
    					</div>
    					</a>
  					</div>

				   </div>
               </div>
               
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

	function ready()
	{
	var verified = <%=request.getAttribute("verified")%>;
	console.log("value: "+verified)
		if (verified) {
			$('#modal1').modal('show'); //contact
		}else{
			$('#modal2').modal('show'); //user
		}
	}
	document.addEventListener("DOMContentLoaded", ready);


   
      
   </script>

</body>
</html>
