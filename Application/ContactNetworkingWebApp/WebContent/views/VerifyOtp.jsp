<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
   <title>Verify Otp</title>

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
<%@ include file="./includes/homeHeader.jsp"%>  
</div>

   <!-------------------------------------
            Verify Email
   --------------------------------------->


   <div class="container-fluid">
      <div class="row justify-content-center">
         <div class="col-md-6 text-center" style="margin:10%;">
            <h1 style="font-family: 'Raleway', sans-serif;">
               <i class="fa fa-check" aria-hidden="true"></i>
               We have Sent you the Code. 
            </h1>
             <img src=" https://img.icons8.com/clouds/100/000000/handshake.png" width="125" height="120" />
            <p class="text-secondary">We're excited to have you get started. First, you need to confirm your account. Please Enter The <b>OTP</b></p>

            <div class="card bg-transparent" style="border:0px;">
               <div class="row g-0">
                  <div class="col-sm-12 text-center">
                     <div class="card-body pe-0">
                 <p style="color:red;"> ${error}</p>
                        <form action='${pageContext.request.contextPath}/CheckOtpServlet?method=doPost' method='post'>
                           <div class="input-group">
                              <input type="number" class="form-control" aria-label="OTP" name="otp" id="otp">
                              <span><button class="btn-warning btn" type='submit'>Verify OTP</button></span>
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
<%@ include file="./includes/homeFooter.jsp"%>  
</div>

</body>

</html>
