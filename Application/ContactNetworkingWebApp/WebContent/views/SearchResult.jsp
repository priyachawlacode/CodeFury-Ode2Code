<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1" import="java.util.Base64,com.hsbc.networking.model.User, java.util.Dictionary,java.util.Hashtable,java.util.Map.Entry,java.util.Map"%>

<!DOCTYPE html>
<html>
<head>
   <title>Search Result</title>

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

    <div>	
	<%@ include file="./includes/mainHeader.jsp"%>  
	</div>
  
   <!-------------------------------------
            SEARCH RESULT
   --------------------------------------->

      <div class="row" style="margin-left: 10%;margin-right:5%;  text-align: center;">
         <div class="col-md-12 mt-4">
            <h4 style="font-family: 'Raleway', sans-serif;">  Search Result</h4>
            <table class="table user-list" >
               <thead>
                  <tr style:"border:2px;">
                  <th><span>Profile Image</span></th>
                     <th><span>Name</span></th>
                     <th><span>Block</span></th>
                     <th><span>Status</span></th>
                  </tr>
               </thead>
               <tbody>
                  <%
                  int count = 0;
                  Dictionary<User, Integer> map = (Hashtable<User,Integer>) request.getAttribute("FilteredSearchResult");
                           for (Entry<User, Integer> entry : ((Hashtable<User, Integer>) map).entrySet()) {
                        	   User u = entry.getKey();
                  		    	int status = entry.getValue();
                              //System.out.println("Inside for loop for Search list," + u.toString());
                              JspWriter jout = out;
                             String url = "https://cdn3.iconfinder.com/data/icons/avatars-round-flat/33/avat-01-512.png";
								if(!(u.getProfileImage()==null) && u.getProfileImage().length()>0 ){
									int blobLength = (int) u.getProfileImage().length();  
									byte[] blobAsBytes = u.getProfileImage().getBytes(1, blobLength);
									url="data:image/jpg;base64," + Base64.getEncoder().encodeToString(blobAsBytes);} 
                              jout.print("<tr>"
                              +"<td><img class='thumb-lg img-circle bx-s' id='photo' name='photo' src="+url+" alt=''></td>"
                              +"<td><span>" + u.getFullName() + "</span></td>"
                              +"<td><span><form action='" + request.getContextPath()+"/BlockUser' method='POST'>"
                              +"<input type='hidden' id='blockId' name='blockId' value='"+u.getUserId()+"'>"
                              +"<button class='btn btn-danger' type='submit' >Block</button></form></span></td>"
                              
                              + "<td style='text-align:center;'><span>"
                              +"<form action='" + request.getContextPath() + "/SendRequestServlet' method='POST'>"
                              +"<input type='hidden' id='frndId"+count+"' name='frndId' value='"+u.getUserId()+"'>"
                              +"<input type='text' id='sendMessage"+count+"' name='sendMessage' placeholder='Write a message'>"
                              +"<button id='sendReq"+count+"' class='btn btn-success' type='submit'>Send Request</button></form>"
                              +"</span></td>"
                              +"</tr>");
                              count++;
                           }
                           %>
               </tbody>
            </table>
         </div>

      </div>
   </div>
   
  <script type="text/javascript">
  
 	function ready()
		  {
 		var count = 0;
	 <% for (Entry<User, Integer> entry : ((Hashtable<User, Integer>) map).entrySet()) {
   	      	int status = entry.getValue();
   	      	//System.out.println(status);%>
   	       var status = "<%=status%>";
	  		if(status == 0)
	  		{
	  			//isPending 
	  			document.getElementById("sendMessage"+count).style.visibility="visible";
	  			document.getElementById("sendMessage"+count).style.backgroundColor ="#f9facf";
	  			document.getElementById("sendMessage"+count).style.border ="0px";
	  			document.getElementById("sendMessage"+count).value = "Request Pending";
	  			document.getElementById("sendMessage"+count).readOnly= true;
	  			document.getElementById("frndId"+count).style.visibility="hidden";
	  			//document.getElementById("sendMessage"+count).style.visibility="hidden";
	  			document.getElementById("sendReq"+count).style.visibility="hidden";
	  		}
	  		else if(status == 1)
	  		{
	  			//Already a friend
	  			document.getElementById("sendMessage"+count).style.visibility="visible";
	  			document.getElementById("sendMessage"+count).style.backgroundColor ="#cfcffa";
	  			document.getElementById("sendMessage"+count).style.border ="0px";
	  			document.getElementById("sendMessage"+count).value = "Already a friend!";
	  			document.getElementById("sendMessage"+count).readOnly= true;
	  			document.getElementById("frndId"+count).style.visibility="hidden";
	  			//document.getElementById("sendMessage"+count).style.visibility="hidden";
	  			document.getElementById("sendReq"+count).style.visibility="hidden";
	  		}
	  		else{
	  			//Not a friend
	  			document.getElementById("frndId"+count).style.visibility="visible";
	  			document.getElementById("sendMessage"+count).style.visibility="visible";
	  			document.getElementById("sendReq"+count).style.visibility="visible"
	  			document.getElementById("sendMessage"+count).readOnly= false;
	  			document.getElementById("sendMessage"+count).style.border ="2px";
	  			//document.getElementById("label"+count).style.visibility="hidden";
	  			
	  		}
	  		count = count+1;
	  		<%}%>
		  }
  document.addEventListener("DOMContentLoaded", ready);
	</script>

<div>	
	<%@ include file="./includes/searchModal.jsp"%>  
</div>
<div>	
	<%@ include file="./includes/mainFooter.jsp"%>  
</div>

</body>
</html>