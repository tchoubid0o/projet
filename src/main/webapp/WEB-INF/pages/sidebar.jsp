<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<aside id="left">
	<div class="left_menu" id="left_recent_image">
		<div class="left_menu_title">Images r&eacute;centes</div>			
		<c:forEach var="image" begin="0" end="4" step="1" items="${imagesn}">
			<a class="title_news choice_left_menu" href="image?id=${image.cleeImg}" title="${image.titreImg}"><span>${image.titreImg}</span></a>
		</c:forEach>
	</div>
	<div class="left_menu" id="left_search_form">
		<form action="index" method="post">
			<input type="text" name="sendResearch" class="" placeholder="Rechercher..." required/>
			<input type="submit" class="submitClass2">
		</form>
	</div>
	<div class="left_menu">
		<div class="left_menu_title">Cat&eacute;gories</div>
			<a class="choice_left_menu" href="index">Toutes</a>
		<c:set var="count" scope="session" value="1"/>
		<c:forEach var="categorie" items="${categories}">
			<a class="choice_left_menu" href="index?sortby=cat&amp;cat=${count}">${categorie}</a>
			<c:set var="count" value="${count + 1}" scope="page"/>
		</c:forEach>
	</div>
	<div class="left_menu">
		<div class="left_menu_title">Trier par:</div>
		<c:choose>
	   		<c:when test="${cat == 1 || cat == 2 || cat == 3}">
				<a class="choice_left_menu" href="index?sortby=popularity&amp;cat=${cat}">Les mieux not&eacute;s</a>
				<a class="choice_left_menu" href="index?sortby=newest&amp;cat=${cat}">Nouveaux en premier</a>
				<a class="choice_left_menu" href="image?id=random&amp;cat=${cat}">Image al&eacute;atoire</a>
			</c:when>
			<c:otherwise>
				<a class="choice_left_menu" href="index?sortby=popularity&amp;cat=0">Les mieux not&eacute;s</a>
				<a class="choice_left_menu" href="index?sortby=newest&amp;cat=0">Nouveaux en premier</a>
				<a class="choice_left_menu" href="image?id=random&amp;cat=0">Image al&eacute;atoire</a>
			</c:otherwise>
		</c:choose>
	</div>
</aside>