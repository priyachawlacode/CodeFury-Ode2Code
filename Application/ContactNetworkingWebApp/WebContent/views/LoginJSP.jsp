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
    
    <!-------------------------------------
				HEADER
	--------------------------------------->

    <ul class="nav justify-content-center bg-secondary">
      <li class="nav-item">
         <a class="nav-link" aria-current="page" href="#">
            <h5 class="nav-logo"><i class="fa fa-google"></i></h5>
         </a>
      </li>
      <li class="nav-item">
         <a class="nav-link" href="#">
            <h5 class="nav-logo"><i class="fa fa-facebook"></i></h5>
         </a>
      </li>
      <li class="nav-item">
         <a class="nav-link" href="#">
            <h5 class="nav-logo"><i class="fa fa-twitter"></i></h5>
         </a>
      </li>
      <li class="nav-item">
         <a class="nav-link">
            <h5 class="nav-logo"><i class="fa fa-instagram"></i></h5>
         </a>
      </li>
      <li class="nav-item" style="margin-left:50%">
         <a class="nav-link" href="${pageContext.request.contextPath}/views/LoginJSP.jsp">
            <h5 class="nav-logo">Login</h5>
         </a>
      </li>
      <li class="nav-item">
         <a class="nav-link" href="${pageContext.request.contextPath}/views/RegistrationJSP.jsp">
            <h5 class="nav-logo">Register</h5>
         </a>
      </li>
      <li class="nav-item">
         <a class="nav-link" href="${pageContext.request.contextPath}/views/AdminLogin.jsp">
            <h5 class="nav-logo">Admin</h5>
         </a>
      </li>
   </ul>

    <nav class="navbar navbar-dark bg-dark">

  	<!-- Navbar content -->
  	<div class="container-fluid">
            <a class="navbar-brand text-warning" href="#"><img src="${pageContext.request.contextPath}/resources/images/mainLogo.png" style="max-width:15%;max-height:15%;"></a>
            
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/home.html">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#about">About</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Download</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#footer">Contact Us</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

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
										<li><a href="${pageContext.request.contextPath}/home.html" class="text-warning"> Home</a></li>
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