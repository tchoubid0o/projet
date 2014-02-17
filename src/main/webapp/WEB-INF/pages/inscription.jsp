<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="header.jsp" />

<div id="content">
	<section class="width1000" style="margin-top: 35px;">
		<jsp:include page="sidebar.jsp" />
		<div id="right">
			<div class="right_content">
				<div class="content_right_white">
					<div style="text-align: center;">
						<h2 class="h2_right_content">Inscription</h2>
						<form action="inscription" method="post">
							<fieldset>
								<label for="user_pseudo" style="text-align: left;">Votre pseudo</label><br /> 
								<input type="text" value="<c:out value="${param.user_pseudo}"/>" placeholder="pseudo"	name="user_pseudo" id="user_pseudo" required /><br />
								<span class="erreur">${erreurs['user_pseudo']}</span>	 
								 
								<label for="user_mail">Votre adresse mail</label><br /> 
								<input type="email" value="<c:out value="${param.user_mail}"/>" placeholder="Email" name="user_mail" id="user_mail" required /><br /> 
								<span class="erreur">${erreurs['user_mail']}</span>
								 
								<label for="user_password">Votre mot de passe</label><br /> 
								<input type="password" placeholder="Password..." name="user_password" id="user_password" required /><br /> 
								<span class="erreur">${erreurs['user_password']}</span>
								
								<label for="user_password_verif">Confirmez votre mot de passe</label><br />
								<input type="password" placeholder="Password..." name="user_password_verif" id="user_password_verif" required /><br /><br /> 
								
								<input type="hidden" name="inscription" value="1" />
								
								<div style="margin: auto;">
									<input class="submit" type="submit" value="Valider"	id="submitregister" />
								</div>
								<p style="${empty erreurs ? 'color=green;' : 'color=red;'}">${resultat}</p>
							</fieldset>
						</form>
					</div>
				</div>
				<div style="clear: both"></div>
			</div>
		</div>
		<div style="clear: both"></div>
	</section>
</div>

<!-- Footer -->
<jsp:include page="footer.jsp" />