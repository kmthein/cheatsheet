<nav class="navbar navbar-expand-lg navbar-light" id="mainNav"
	style="position: relative">
	<div class="container">
		<a class="navbar-brand" style="color: black" href="index.html">Cheatography</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			Menu <i class="fas fa-bars"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ms-auto py-4 py-lg-0">
				<li class="nav-item"><a style="color: black"
					class="nav-link px-lg-3 py-3 py-lg-4" href="index.jsp">Home</a></li>
				<li class="nav-item"><a style="color: black"
					style="color: black" style="color: black" style="color: black"
					class="nav-link px-lg-3 py-3 py-lg-4" href="cheetsheets">Cheatsheet</a></li>
				<%
				String user = (String) session.getAttribute("user");
				if (user != null) {
				%>
				<li class="nav-item"><a style="color: black"
					class="nav-link px-lg-3 py-3 py-lg-4" href="createCheatsheet.jsp">Create</a></li>
				<%
				} else {
				%>
				<li class="nav-item"><a style="color: black"
					class="nav-link px-lg-3 py-3 py-lg-4" href="loginPage.jsp">Login</a></li>
				<li class="nav-item"><a style="color: black"
					class="nav-link px-lg-3 py-3 py-lg-4" href="registerPage.jsp">Register</a></li>
				<%
				}
				%>
			</ul>
		</div>
	</div>
</nav>