<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1" import="java.util.List,com.hsbc.networking.model.User,java.util.Base64"%>

<!DOCTYPE html>
<html>
<head>
   <title>Friend List</title>

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

 
<style>
.img-circle{
	border-radius:50%;
	width:100px;
	padding:0px;
	margin-right:0%;
	align:centre;
}
td{
    vertical-align: middle;
}
tr{
margin:2%;
}

</style>

</head>
<body>

   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   <% 
   	List<User> uList = (List<User>)session.getAttribute("friendList");
   JspWriter jout = out;
   
   %>

    <div>	
	<%@ include file="./includes/mainHeader.jsp"%>  
	</div>
   <!-------------------------------------
            Friend List
   --------------------------------------->

   <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
      <ol class="breadcrumb alert alert-secondary">
         <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/views/MainPage.jsp" class="text-dark">Home</a></li>
         <li class="breadcrumb-item"><a href="" class="text-dark">My Friends</a></li>
      </ol>
   </nav>

   <div class="container-fluid">
      <div class="row">
         <table class="table user-list">
            <thead>
               <tr>
                  <th>&nbsp;</th>
                  <th><span>Profile Image</span></th>
                   <th><span>Username</span></th>
                  <th><span>Name</span></th>
                  <th><span>Contact No</span></th>
                  <th><span>Email</span></th>
                  <th><span>Address</span></th>
                  <th><span>City</span></th>
                  <th><span>State</span></th>
                  <th><span>Country</span></th>
                  <th><span>Company</span></th>
               </tr>
            </thead>
            
            <tbody>
             <% 
            
             String url = "https://cdn3.iconfinder.com/data/icons/avatars-round-flat/33/avat-01-512.png";
             for(User user: uList) {
         		//System.out.println("INside friendLIst jsp rec list of friends:"+user.toString()); 
         		
				if(!(user.getProfileImage()==null) && user.getProfileImage().length()>0 ){
					int blobLength = (int) user.getProfileImage().length();  
					byte[] blobAsBytes = user.getProfileImage().getBytes(1, blobLength);
					url="data:image/jpg;base64," + Base64.getEncoder().encodeToString(blobAsBytes);
			} 
    	
              jout.print("<tr>"+
            		  "<td>&nbsp;</td>"
            	  +"<td><img class='thumb-lg img-circle bx-s' id='photo' name='photo' src="+url+" ></td>"
            	  +"<td><span>"+user.getUserName()+"</span></td>"
            	  +"<td><span>"+user.getFullName()+"</span></td>"
            	  +"<td><span>"+user.getContactNo()+"</span></td>"
                  +"<td><span>"+user.getEmail()+"</span></td>"
                  +"<td><span>"+user.getAddress()+"</span></td>"
                  +"<td><span>"+user.getCity()+"</span></td>"
                  +"<td><span>"+user.getState()+"</span></td>"
                  +"<td><span>"+user.getCountry()+"</span></td>"
                  +"<td><span>"+user.getCompany()+"</span></td>"
                  +"<td class='text-right'>"
                  +"<form action='" + request.getContextPath()+ "/BlockUser' method='POST'>"
				  +"<input type='hidden' id='blockId' name='blockId' value='"+user.getUserId()+"'>"
                  +"<button type='submit' class='btn btn-danger' >Block</button></form></td>"
        		  +"<td class='text-right'>"
                  +"<form action='" + request.getContextPath()+ "/UnfriendServlet' method='POST'>"
				  +"<input type='hidden' id='removeId' name='removeId' value='"+user.getUserId()+"'>"
                  +"<button type='submit' class='btn btn-danger' >UnFriend</button></form></td>"
               +"</tr>");
             }
              %>
            </tbody>
         </table>
      </div>
   </div>

   <div>	
	<%@ include file="./includes/searchModal.jsp"%>  
	</div>

<div>	
<%@ include file="./includes/mainFooter.jsp"%>  
</div>

</body>
</html>