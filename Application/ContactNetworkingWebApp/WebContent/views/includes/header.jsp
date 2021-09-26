<!-------------------------------------
            HEADER
   --------------------------------------->

	<ul class="nav justify-content-center bg-secondary">
		<li class="nav-item"><a class="nav-link" aria-current="page"
			href="#">
				<h5 class="nav-logo">
					<i class="fa fa-google"></i>
				</h5>
		</a></li>
		<li class="nav-item"><a class="nav-link" href="#">
				<h5 class="nav-logo">
					<i class="fa fa-facebook"></i>
				</h5>
		</a></li>
		<li class="nav-item"><a class="nav-link" href="#">
				<h5 class="nav-logo">
					<i class="fa fa-twitter"></i>
				</h5>
		</a></li>
		<li class="nav-item"><a class="nav-link">
				<h5 class="nav-logo">
					<i class="fa fa-instagram"></i>
				</h5>
		</a></li>
		<li class="nav-item" style="margin-left: 50%"><a class="nav-link"
			onclick="Search()">
				<h5 class="nav-logo">
					<i class="fa fa-search"></i>
				</h5>
		</a></li>
		<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/LogoutServlet">
				<h5 class="nav-logo">Logout</h5>
		</a></li>
	</ul>

	<nav class="navbar navbar-dark bg-dark">

		<!-- Navbar content -->
		<div class="container-fluid">
			<a class="navbar-brand text-warning" href=""><img src="${pageContext.request.contextPath}/resources/images/mainLogo.png" style="max-width:15%;max-height:15%;"></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="${pageContext.request.contextPath}/views/MainPage.jsp">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="#about">About</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#download">Download</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#footer">Contact
							Us</a></li>
				</ul>
			</div>
		</div>
	</nav>