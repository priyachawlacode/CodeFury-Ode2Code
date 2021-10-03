<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1" import="com.hsbc.networking.model.Contact,java.util.Base64, java.util.List"%>

<!DOCTYPE html>
<html>
<head>
   <title>View Contacts</title>
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
	response.sendRedirect(path + "/views/LoginJSP.jsp");
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
<style>
.img-circle{
	border-radius:50%;
	width:120px;
	padding:0px;
	margin-right:0%;
	align:centre;
}

</style>
   <!--jquery-->
   <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>


</head>
<body>

   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <div>	
	<%@ include file="./includes/mainHeader.jsp"%>  
	</div>

   <!-------------------------------------
            Contact List
   --------------------------------------->
<%
String url = "https://bootdey.com/img/Content/user_1.jpg";%>
   <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
      <ol class="breadcrumb alert alert-secondary">
         <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/views/MainPage.jsp" class="text-dark">Home</a></li>
         <li class="breadcrumb-item"><a href="" class="text-dark">My Contacts</a></li>
         <li  style="margin-left: 75%">
            <a class="nav-link dropdown-toggle text-dark" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">Sort Contacts</a>
            <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/SortContact?method=doGet&type=name&cond=t">Sort By Name (A-Z)</a></li>
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/SortContact?method=doGet&type=name&cond=f">Sort By Name (Z-A)</a></li>
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/SortContact?method=doGet&type=email&cond=t">Sort By Email (A-Z)</a></li>
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/SortContact?method=doGet&type=email&cond=f">Sort By Email (Z-A)</a></li>
            </ul>
         </li>
         <li><button onclick = "DeleteSelected()" class="btn btn-danger" id="batchDelete">Delete Selected</button></li>
      </ol>
   </nav>
<form id="DeleteForm">
<input type="hidden" id="delValue" name="delValue">
</form>
   <div class="container-fluid">

      	<div class="accordion" id="accordionPanelsStayOpenExample">
      	<% List<Contact> cList = (List<Contact>)session.getAttribute("contactList");
			int count = 0;%>
      		<c:forEach items="${sessionScope.contactList}" var="contact">
      			<div class="accordion-item">
    				<h2 class="accordion-header" id="panelsStayOpen-headingTwo">
      					<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#<%="a"+count%>" aria-expanded="false" aria-controls="<%="a"+count%>">
        					<div class="col-md-8">
        						<div class="info">
                           			<h5>${contact.fullName}</h5>
                           			<p class="text-muted">${contact.contactNo}</p>
                           			<input type="checkbox" value="${contact.contactId}" style="width: 20px;height: 20px;" />
                        		</div>
        					</div>

        					<div class="col-md-4 text-end btn-group-sm">

                           		<a href="${pageContext.request.contextPath}/EditContactServlet?method=doGet&contactId=${contact.contactId}" class="btn btn-success tooltips" data-placement="top" data-toggle="modal" data-target="#form"
                              	data-original-title="Edit"> 
                              		<i class="fa fa-pencil"></i>
                           		</a> 
                           		<a href="${pageContext.request.contextPath}/DeleteContactServlet?method=doGet&contactId=${contact.contactId}" class="btn btn-danger tooltips" data-placement="top"
                              	data-toggle="tooltip" data-original-title="Delete"> 
                              		<i class="fa fa-close"></i>
                           		</a>
                     </div>
      					</button>
    				</h2>
    				<div id="<%="a"+count%>" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingTwo">
                  		<div class="container-fluid">
                     		<div class="row m-0 p-0 accordion-body">
									<%
										if(!(cList.get(count).getProfileImage()==null) && cList.get(count).getProfileImage().length()>0 ){
											int blobLength = (int) cList.get(count).getProfileImage().length();  
											byte[] blobAsBytes = cList.get(count).getProfileImage().getBytes(1, blobLength);
											url="data:image/jpg;base64," + Base64.getEncoder().encodeToString(blobAsBytes);
									} %>
						    	
    
                        		<div class="col-sm-4">
                           			<img class="thumb-lg img-circle bx-s" id=""photo name='photo'
                           			src=<%=url%> alt="">
                        		</div>

                        		<div class="col-sm-4">
                           			<ul class="list-group list-group-flush">
		                              	<li class="list-group-item">${contact.email }</li>

        		                      	<li class="list-group-item">${contact.gender }</li>

                		              	<li class="list-group-item">${contact.dob }</li>

                              			<li class="list-group-item">${contact.address }</li>

                           			</ul>
                        		</div>

                        		<div class="col-sm-4">
                           			<ul class="list-group list-group-flush">
		                              	<li class="list-group-item">
                              			${contact.city }</li>

        		                      	<li class="list-group-item">${contact.state }</li>

                		              	<li class="list-group-item">${contact.country }</li>

                              			<li class="list-group-item">${contact.company }</li>
                              
                           			</ul>
                        		</div>

                     		</div>

                  		</div>                 
               		</div>
  				</div>
  		<% count++; %>
  			</c:forEach>
		</div>
	</div>
 <div>	
	<%@ include file="./includes/searchModal.jsp"%>  
	</div>

<div>	
<%@ include file="./includes/mainFooter.jsp"%>  
</div>


<script type="text/javascript">

function callDeleteServlet(deleteList)
{
 document.getElementById("DeleteForm").action="${pageContext.request.contextPath}/deleteContactBatchServlet";
 document.getElementById("delValue").setAttribute('value',deleteList);
 document.getElementById("DeleteForm").method = "post";
 document.getElementById("DeleteForm").submit();}

   function DeleteSelected() {
        //Create an Array.
        var selected = new Array();
 
        //Reference all the CheckBoxes in Table.
        var chks = document.getElementsByTagName("INPUT");
 
        // Loop and push the checked CheckBox value in Array.
        for (var i = 0; i < chks.length; i++) {
            if (chks[i].checked) {
                selected.push(chks[i].value);
            }
        }

        callDeleteServlet(selected);
   }
 
   </script>

</body>
</html>