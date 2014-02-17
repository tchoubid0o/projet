<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="header.jsp" />

<div id="content">
	<section class="width1000" style="margin-top: 35px;">
		<jsp:include page="sidebar.jsp" />
		<div id="right">
			<div class="right_content">
			<c:if test="${erreurs != '{}'}">
				<span class="erreur">${erreurs}</span>
			</c:if>
			<c:if test="${erreurs == '' || erreurs == '{}' }">
				<span class="erreur">${resultat}</span> <span class="erreur">${message}</span>
	<div class="content_right_white">
		<div style="text-align: center;">
			<h2	class="h2_right_content">Uploader une image</h2>
			<form action="profil" method="post" enctype="multipart/form-data">
				<fieldset>
					<!-- On limite le fichier à 100Ko -->
					<input type="hidden" name="MAX_FILE_SIZE" value="3145728">


					<input type='file' id="imgFileInput" name="file" /> <img
						id="imgSnapchat" src="#" style="display: none"
						alt="La référence en Snapchat HEI" />
					<br /> 
					<label for="titreImg">Titre de l'image</label><br /> 
					<input type="text" name="titreImg" id="titreImg" placeholder="Titre de l'image" required /><br /> 
					<label for="textImg">Texte de l'image</label><br /> 
					<input type="text" name="textImg" id="textImg" placeholder="Texte de l'image" /><br />
					
					<label for="categorie_select">Catégorie de l'image</label><br />
					<select name="categorie" id="categorie_select">
						<c:set var="count" value="1" scope="page" />
						<c:forEach var="categorie" items="${categories}">
							<option value="${count}">${categorie}</option>
							<c:set var="count" value="${count + 1}" scope="page"/>
						</c:forEach>
					</select>
					<div style="margin: auto;">
						<input class="submit" type="submit" value="Valider"/>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
	<div class="images_profil">
		<h2	class="h2_right_content">Vos Uploads</h2><br/>
		<c:choose>
			<c:when test="${images_size > 0}">
				<c:forEach var="image" begin="0" end="${images_size}" step="1"
					items="${images}">
					<li class="imgSnapchat">
						<article id="id${image.cleeImg}" class="img_index">
							<div class="vote" id="vote_id${image.cleeImg}">
								<div title="modifier" class="modify UrChoice" data-choice="1" data-extImg="${image.extImg}" data-imgLink="${image.cleeImg}${image.extImg}" data-imgTitre="${image.titreImg}" data-imgTexte="${image.textImg}" data-cleeImg="${image.cleeImg}"  data-user_pseudo="<%= request.getSession().getAttribute("user_pseudo") %>" data-idCategorie="${image.idCategorie}"></div>
								<div title="supprimer" class="delete UrChoice" data-choice="2" data-cleeImg="${image.cleeImg}" data-idUser="<%= request.getSession().getAttribute("idUser") %>"></div>
							</div>
							<figure>
								<a href="image?id=${image.cleeImg}" title=""> 
									<img alt="${image.titreImg}" class="profilImgClass" src="images/uploads/${image.cleeImg}${image.extImg}" />
								</a>
							</figure>
						</article>
						<aside id="img_id${image.cleeImg}" class="hover_index">
							<span class="wordwrap">${image.titreImg}</span>
						</aside>
					</li>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<span style="text-align: center">Vous n'avez encore uploadé	aucune image.</span>
			</c:otherwise>
		</c:choose>
		<div style="clear: both"></div>
	</div>
	<div class="content_right_white" id="editImage">
		<div style="text-align: center;">
			<h2	class="h2_right_content">Modifier une image</h2>
			<form action="modifimage" id="modifimageajax" method="post" enctype="multipart/form-data">
				<fieldset>
					<!-- On limite le fichier à 100Ko -->
					<input type="hidden" name="MAX_FILE_SIZE" value="3145728">


					<input type='file' id="imgFileInputEdit" name="file" /> <img
						id="imgSnapchatEdit" src="#" style="display: none"
						alt="La référence en Snapchat HEI" />
					<input type="hidden" id="cleePrevImg" name="cleePrevImg">
					<input type="hidden" id="extPrevImg" name="extPrevImg"><br /> 
					<label for="titreImgEdit">Titre de l'image</label><br /> 
					<input type="text" name="titreImg" id="titreImgEdit" placeholder="Titre de l'image" required /><br /> 
					<label for="textImgEdit">Texte de l'image</label><br /> 
					<input type="text" name="textImg" id="textImgEdit" placeholder="Texte de l'image" /><br />
					
					<label for="categorie_selectEdit">Catégorie de l'image</label><br />
					<select name="categorie" id="categorie_selectEdit">
						<c:set var="count" value="1" scope="page" />
						<c:forEach var="categorie" items="${categories}">
							<option value="${count}">${categorie}</option>
							<c:set var="count" value="${count + 1}" scope="page"/>
						</c:forEach>
					</select>
					<div style="margin: auto;">
						<input class="submit" type="submit" value="Valider"/>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
	</c:if>
				<div style="clear: both"></div>
			</div>
		</div>
		<div style="clear: both"></div>
	</section>
</div>

<script type="text/javascript" src="scripts/input_file.js"></script>
<!-- Footer -->
<jsp:include page="footer.jsp" />