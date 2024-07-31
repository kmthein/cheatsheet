<nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
	<div class="container">
		<a class="navbar-brand" href="index.html">Cheatography</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			Menu <i class="fas fa-bars"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ms-auto py-4 py-lg-0">
				<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
					href="index.jsp">Home</a></li>
				<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
					href="cheetsheets">Cheetsheet</a></li>
				<%
				String user = (String) session.getAttribute("user");
				if (user != null) {
				%>
				<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="createCheatsheet.jsp">Create</a></li>
				<%
				} else {
				%>
				<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="loginPage.jsp">Login</a></li>
				<li class="nav-item"><a  class="nav-link px-lg-3 py-3 py-lg-4" href="registerPage.jsp">Register</a></li>
				<%
				}
				%>
			</ul>
		</div>
	</div>
</nav>
<!-- Page Header-->
<header class="masthead"
	style="background-image: url('assets/img/home-bg.jpg')">
	<div class="container position-relative px-4 px-lg-5">
		<div class="row gx-4 gx-lg-5 justify-content-center">
			<div class="col-md-10 col-lg-8 col-xl-7">
				<div class="site-heading">
					<h1>Cheatsheets</h1>
					<span class="subheading">Easily Create Your Cheatsheet Now</span>
				</div>
			</div>
		</div>
	</div>
</header>