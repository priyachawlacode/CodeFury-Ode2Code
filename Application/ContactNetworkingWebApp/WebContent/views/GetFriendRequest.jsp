<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.hsbc.networking.model.User, java.util.HashMap, java.util.Map ,com.hsbc.networking.model.FriendRequest,java.util.Base64"%>

<!DOCTYPE html>
<html>
<head>
<title>Friend List</title>

<!--Stylesheets-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/homeStyle.css">

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

<style type="text/css">
.table>:not(caption)>*>* {
	border-bottom-width: 0px;
}
</style>
<style>
.profile-pic{
	border-radius:50%;
	width:100px;
	padding:0px;
	margin-right:0%;
	align:centre;
}
.profile-td{
	width:25%;
	height:25%;
}


</style>
<style>

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

	<div>	
	<%@ include file="./includes/mainHeader.jsp"%>  
	</div>
	<!-------------------------------------
            Friend List
   --------------------------------------->

	<nav style="-bs-breadcrumb-divider: '&gt;';" aria-label="breadcrumb">
		<ol class="breadcrumb alert alert-secondary">
			<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/views/MainPage.jsp"
				class="text-dark">Home</a></li>
			<li class="breadcrumb-item"><a href=""
				class="text-dark">Add Friends</a></li>
		</ol>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<table class="table user-list">
				<thead>
					<tr>
						<th>&nbsp;</th>
						<th><span>Profile Image</span></th>
						<th><span>Name</span></th>
						<th><span>Message</span></th>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</thead>
				<tbody>
					<%
					String url = "https://cdn3.iconfinder.com/data/icons/avatars-round-flat/33/avat-01-512.png";
					
					JspWriter jout = out;
					HashMap<FriendRequest, User> map = (HashMap<FriendRequest, User>) request.getAttribute("friendRequestList");
					for (Map.Entry<FriendRequest, User> f : map.entrySet()) {
						//ToSet image
						if(!(f.getValue().getProfileImage()==null) && f.getValue().getProfileImage().length()>0){
							int blobLength = (int) f.getValue().getProfileImage().length();  
							byte[] blobAsBytes = f.getValue().getProfileImage().getBytes(1, blobLength);
							url="data:image/jpg;base64," + Base64.getEncoder().encodeToString(blobAsBytes);
						} 
						
						jout.print("<tr>"
								+"<td>&nbsp;</td>"
								+"<td class='profile-td'>"
								+"<!-- User Profile Image --><img id='photo' class='profile-pic' src='"+url+"'>"
								+"</td>"
								+"<td><span>"+f.getValue().getFullName()+"</span></td>"
								+"<td><span>"+f.getKey().getMessage()+"</span></td>"
								+"<td><form action='"+request.getContextPath()+"/AcceptDeclineRequest' method='post'>"
								/* +"<input type='hidden' id='frReq' name='frReq' value='"+f.getKey()+"'>" */
								+"<input type='hidden' id='friendId' name='friendId' value='"+f.getKey().getFriend_id()+"'>"
								+"<input type='hidden' id='userId' name='userId' value='"+f.getKey().getUser_id()+"'>"
								+"<input type='hidden' id='message' name='message' value='"+f.getKey().getMessage()+"'>"
								+"<button type='submit' class='btn btn-success' name='accept' id= 'accept'  value='accept'>Accept</button>"
								+"</form></td>"
								+"<td><form action='"+request.getContextPath()+"/AcceptDeclineRequest' method='post'>"
								/* +"<input type='hidden' id='frReq' name='frReq' value='"+f.getKey()+"'>" */
								+"<input type='hidden' id='friendId' name='friendId' value='"+f.getKey().getFriend_id()+"'>"
								+"<input type='hidden' id='userId' name='userId' value='"+f.getKey().getUser_id()+"'>"
								+"<input type='hidden' id='message' name='message' value='"+f.getKey().getMessage()+"'>"
								+"<button type='submit' class='btn btn-warning' name='decline' id= 'decline'  value='decline'>Decline</button>"
								+"</form></td>"
								+"<td><form action='"+request.getContextPath()+"/BlockUser' method='post'>"
								+"<input type='hidden' id='blockId' name='blockId' value='"+f.getKey().getFriend_id()+"'>"
								+"<button type='submit' class='btn btn-danger' >Block</button></form>"
								+"</td></tr>");
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