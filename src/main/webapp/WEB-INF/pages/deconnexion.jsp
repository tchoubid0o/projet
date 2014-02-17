<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="header.jsp" />

<div id="content">
	<section class="width1000" style="margin-top: 35px;">
		<jsp:include page="sidebar.jsp" />
		<div id="right">
			<div class="right_content">
				Vous vous êtes bien déconnectez.
				<div style="clear: both"></div>
			</div>
		</div>
		<div style="clear: both"></div>
	</section>
</div>

<!-- Footer -->
<jsp:include page="footer.jsp" />