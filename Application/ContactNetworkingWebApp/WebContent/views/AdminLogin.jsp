<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Log In</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/AdminLoginStyle.css">

<style>
    .login::after, .login>button{
        background-color:#FADA5E;
    }
    .login::before{
        background-color:grey;
    }
    .login > button:hover {
        background-color: grey;
    }
    body {
        background-image: url('https://cdn.wallpapersafari.com/88/58/RszVQx.jpg');
    }
   
</style>
</head>
<body>
    
    
    <h2><%
			if (null!=request.getAttribute("errorMessage"))
				{
					out.println(request.getAttribute("errorMessage"));
				}
			%></h2>
                        
    <form class="login" name="loginform" method="post" action="${pageContext.request.contextPath}/AdminLogin" >
        <input type="text" placeholder="Username" name="username">
        <input type="password" placeholder="Password" name="password">
        <button type="submit" value="Login" >Login</button>
    </form>
                        

</body>
</html>