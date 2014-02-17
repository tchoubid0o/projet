<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- Header -->
<jsp:include page="header.jsp" />

<div id="content">
	<section class="width1000" style="margin-top: 35px;">
		<jsp:include page="sidebar.jsp" />
		<div id="right">
			<div class="right_content">
				${erreurs['user_connexion']}
				${erreurs['user_resultat']}
				<section id="contentGrandeImage">
					<div class="keyboardControl">Utiliser <span class="arrowControl">←</span> et <span class="arrowControl">→</span> pour naviguer</div>
					<h2	class="h2_right_content">${image.titreImg}</h2>
					<img id="imageGrande" src="images/uploads/${image.cleeImg}${image.extImg}" alt="${image.titreImg}" />
					<div id="prevImg" class="hideMe">${prevImg}</div>
					<div id="nextImg" class="hideMe">${nextImg}</div>
					<div id="voteGrandeImage">
						<div title="like" class="vote_plus UDecide floatLeft" data-vote="1" data-cleeImg="${image.cleeImg}" data-user_pseudo="<%= request.getSession().getAttribute("user_pseudo") %>"></div>
						<div title="dislike" class="vote_moins UDecide floatLeft" data-vote="-1" data-cleeImg="${image.cleeImg}"	data-user_pseudo="<%= request.getSession().getAttribute("user_pseudo") %>"></div>
						<div class="ptsImg">${image.ptsImg}	points</div>
						<div style="clear: both"></div>
					</div>
					<section id="commentGrandeImage">
						<c:choose>
							<c:when test="${commentaires_size > 0}">
								<c:forEach var="commentaire" items="${commentaires}">
									<span class="colorBlue"><fmt:formatDate	value="${commentaire.commentDate}" pattern="dd MMMM yyyy" /></span>
									<br />
									<span class="date_news">${commentaire.commentContent}</span>
									<br />
									<div class="sepComment"></div>
									<br />
								</c:forEach>
							</c:when>
							<c:otherwise>
								<span style="text-align: center">Il n'y a aucun commentaire à afficher.</span>
							</c:otherwise>
						</c:choose>
						<%
						if (request.getSession().getAttribute("user_pseudo") != null) {
						%>
						<form id="ajout_commentaire" class="width303comment" action="image?id=${image.cleeImg}" method="post">
							<input type="hidden" name="cleeImg" value="${image.cleeImg}" />
							<label for="commentContent">Message :</label><br />
							<textarea id="commentContent" class="width303comment" name="commentContent"></textarea>
							<br />
							<br /> <input type="submit" id="submitcom" class="submit" name="send" value="envoyer" />
						</form>
						<%
						}
						%>
					</section>
				</section>
				<div style="clear: both"></div>
			</div>
		</div>
		<div style="clear: both"></div>
	</section>
</div>

<!-- Footer -->
<script type="text/javascript" src="scripts/keyboard.js"></script>
<jsp:include page="footer.jsp" />