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
				<form action="${pageContext.request.contextPath}/UserInfo"
					method="POST">
					<button type="submit" class="btn btn-primary">View All
						Friends</button>
					<br /> <br />
					<div class="form-group">
						<input type="text" class="form-control" name="name"
							placeholder="Search" value="${user.name}">
						<table>
							<tr>
								<th style="width: 60%;"></th>
							</tr>
						</table>
					</div>
					<button type="submit" class="btn btn-primary">View Details</button>
					<button type="submit" class="btn btn-primary">Remove</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>