<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="header.jsp" />

<div id="content">
	<section class="width1000" style="margin-top: 35px;">
		<jsp:include page="sidebar.jsp" />
		<div id="right">
			<ul class="right_content">
				<c:forEach var="image" begin="0" end="${images_size}" step="1"	items="${images}">
					<li class="imgSnapchat">
						<article id="id${image.cleeImg}" class="img_index">
							<div class="vote" id="vote_id${image.cleeImg}">
								<div title="like" class="vote_plus UDecide" data-vote="1" data-cleeImg="${image.cleeImg}" data-user_pseudo="<%= request.getSession().getAttribute("user_pseudo") %>"></div>
								<div title="dislike" class="vote_moins UDecide" data-vote="-1" data-cleeImg="${image.cleeImg}" data-user_pseudo="<%= request.getSession().getAttribute("user_pseudo") %>"></div>
							</div>
							<figure>
								<a href="image?id=${image.cleeImg}" title=""> 
									<img alt="${image.titreImg}" style="width: 135px; height: 135px; border: 3px solid white; float: left;" src="images/uploads/${image.cleeImg}${image.extImg}" />
								</a>
							</figure>
						</article>
						<aside id="img_id${image.cleeImg}" class="hover_index">
							<span class="wordwrap">${image.titreImg}</span>
						</aside>
					</li>
				</c:forEach>
				<c:if test="${images_size == 0 }">
					<h2 class="h2_right_content colorGray">Aucun résultat</h2>
				</c:if>
			</ul>
		</div>
		<div style="clear: both"></div>
	</section>
	
</div>

<!-- Footer -->
<jsp:include page="footer.jsp" />