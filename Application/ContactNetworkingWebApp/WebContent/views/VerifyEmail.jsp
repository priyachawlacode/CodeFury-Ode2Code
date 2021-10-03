<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
   <title>Verify Email</title>
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

</head>
<body>

   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

   <div>	
	<%@ include file="./includes/mainHeader.jsp"%>  
	</div>

   <!-------------------------------------
            Verify Email
   --------------------------------------->


   <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
      <ol class="breadcrumb alert alert-secondary">
         <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/views/MainPage.jsp" class="text-dark">Home</a></li>
         <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/views/VerifyEmail.jsp" class="text-dark">Verify Email</a></li>
      </ol>
   </nav>


   <div class="container-fluid">
      <div class="row justify-content-center">
         <div class="col-md-6 text-center" style="margin:10%;">
            <h1 style="font-family: 'Raleway', sans-serif;">
               <i class="fa fa-check" aria-hidden="true"></i>
               E-mail Verifier
            </h1>
            <p class="text-secondary">Verify the existence of any e-mail before adding Contact.</p>

            <div class="card bg-transparent" style="border:0px;">
               <div class="row g-0">
                  <div class="col-sm-12 text-center">
                     <div class="card-body pe-0">
                        <form action="${pageContext.request.contextPath}/CheckMailServlet" method="post">
                           <div class="input-group">
                              <input type="email" class="form-control" aria-label="E-mail Block" placeholder="abc@gmail.com" name="contactEmail" id="contactEmail">
                              <span><button class="btn-warning btn">Verify</button></span>
                           </div>
                        </form>
                     </div>  
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

</body>
</html>
