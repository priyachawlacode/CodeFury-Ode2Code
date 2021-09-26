<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">


		<div class="row">
			<div class="col-md-4">
				<form action="${pageContext.request.contextPath}/AddContactServlet" method="POST">

					<div class="form-group">
						<input type="text" class="form-control" name="name"
							placeholder="Enter Full Name" value="${user.name}" required />
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="email"
							placeholder="Enter Email" value="${user.email}" required />
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="phone"
							placeholder="Enter phone" value="${user.phone}" />
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="gender"
							placeholder="Enter Email" value="${user.gender}" />
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="dob"
							placeholder="Enter dob" value="${user.dob}" />
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="address"
							placeholder="Enter address" value="${user.address}" />
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="city"
							placeholder="Enter city,state,country" value="${user.city}" />
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="company"
							placeholder="Enter Email" value="${user.company}" />
					</div>
					<%-- <div class="form-group">
						<input type="file" class="form-control" name="image"
							placeholder="Add image" value="${user.name}" /></input>
					</div> --%>
					<button type="submit" class="btn btn-primary">Add</button>
				</form>
			</div>
		</div>

	</div>
</body>
</html>