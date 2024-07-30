<jsp:include page="layout/head.jsp"></jsp:include>
<jsp:include page="component/nav.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
	<h2 class="center-title">All Cheatsheets</h2>
	<div class="row">
		<c:forEach var="cs" items="${cheatsheets}">
			<div class="col-4">
				<a href="cheetsheetDetail?id=${cs.id}"><h5 style="font-size: 17px">${cs.name}</h2></a>
				<span style="font-size: 15px">${cs.updatedAtFormatted}</span>
				<p>${fn:substring(cs.description, 0, 100)}...</p>
			</div>
		</c:forEach>
	</div>
</div>
<jsp:include page="layout/foot.jsp"></jsp:include>