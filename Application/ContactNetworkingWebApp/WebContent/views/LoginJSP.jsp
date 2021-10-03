<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>

<!--Stylesheets-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/LoginStyle.css">
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
   
   <style>
    .img-fluid {
        width: 100%;
        height: 100%;
    }
   </style>
   
</head>
<body>
    
<div>	
<%@ include file="./includes/homeHeader.jsp"%>  
</div>
	<!-------------------------------------
				LOGIN FORM
	--------------------------------------->
                                
                                
        <div class="container-fluid">
            
            <div class="row">
                <div class="col-md-7 m-0">
                    <img src="https://blog.hootsuite.com/wp-content/uploads/2020/05/how-to-create-a-social-media-strategy.jpg" class="img-fluid rounded-start" alt="login">
                </div>
                
                <div class="col-md-5 m-0">
                    <div class="card-body">
                        <div class=""bg-danger>${error}</div>
                        <p style="font-family: 'Raleway', sans-serif;">Don't have an account? <a href="${pageContext.request.contextPath}/views/RegistrationJSP.jsp" class="text-warning">Register Here!</a></p>
            
                      <h3 style="font-family: 'Raleway', sans-serif;" class="p-4">Login Now</h3>

                      <form class="row" method="post" action="${pageContext.request.contextPath}/LoginServlet" name="signinform">

                        <div class="col-sm-12">

                            <div class="form-check">
                                <label for="username" class="form-label">User Name</label>
                                <input type="text" class="form-control" id="username" placeholder="Username" name="Username">
                            </div><br>

                            <div class="form-check">
                                <label for="password" class="form-label">Password</label>
                                <input type="password" class="form-control" id="password" placeholder="Password" name="password">
                            </div><br>
                            
                            <button type="submit" class="btn btn-warning m-3" value="Submit" onclick="validateform();">Login</button>
                        </div>
                      </form>

                  </div>
                </div>
                
            </div>
        </div>

 
<div>	
<%@ include file="./includes/homeFooter.jsp"%>  
</div>
            
   <script >
	function validateform()
	{
		var password=document.signinform.password.value;
		var username=document.signinform.Username.value;
		if(username=="")
		{
			alert("Username cannot be empty");
			return false;
		}
		else if(password==""||password==null)
		{
			alert("Password cannot be empty");
			return false;
		}
		else if(password.length<6)
		{
			alert("Password must be atleast 6 characters long.");
			return false;
		}
		else
		{
			window.open(${pageContext.request.contextPath}+"/LoginServlet");
		}		
	}
</script>
</body>
</html>