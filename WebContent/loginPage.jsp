<jsp:include page="layout/head.jsp"></jsp:include>
<jsp:include page="component/nav.jsp"></jsp:include>
<form action="login" method="post" class=" col-4 mx-auto row g-3">
	<h2 class="center-title">Login User</h2>
	<div class="col-12">
		<label for="inputAddress" class="form-label">Email</label> <input
			type="text" id="email" class="form-control" name="email" value="${email}">
	</div>
	<div class="col-md-12">
		<label for="password" class="form-label">Password</label> <input
			type="password" id="password" class="form-control" name="password">
	</div>
	<p class="error-msg">${error}</p>
		<p style="padding-top: 15px; margin-bottom: 5px; text-align: center; font-size: 18px">Don't have an account? <a href="registerPage.jsp" style="text-decoration: underline">Sign up</a></p>
	<div class="col-12 d-flex justify-content-center my-4">
		<button type="submit" class="btn btn-secondary">Login</button>
	</div>
</form>
<jsp:include page="layout/foot.jsp"></jsp:include>