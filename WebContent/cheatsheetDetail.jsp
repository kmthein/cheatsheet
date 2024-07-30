<jsp:include page="layout/head.jsp"></jsp:include>
<jsp:include page="component/nav.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
			<div class="col">
				<h2>${cs.name}</h2>
				<p>${cs.description}</p>
				<p>${cs.content}</p>
			</div>
	</div>
</div>
<jsp:include page="layout/foot.jsp"></jsp:include>