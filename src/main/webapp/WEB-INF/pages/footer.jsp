		<footer>
			<div id="footer-content">
				<div id="test_center">
				<div class="footer-col1" id="col1_footer">
					<a href="index" style="border-top: none;" title="Accueil">Accueil</a>
					<%
						if (request.getSession().getAttribute("user_pseudo") == null) {
					%>
					<a href="inscription" title="Inscrivez-vous gratuitement!">Inscription</a>
					<a href="connexion" title="Connectez-vous Ã  votre compte">Connexion</a>
					<%
						} else {
					%>
					<a href="deconnexion" title="déconnectez-vous">Deconnexion</a>
					<a href="profil" title="Envoyez nous vos Snapchats">Profil</a>
					<% } %>
					<a href="faq" title="La FAQ de Snapchat HEI">Faq</a>
					<a href="image?id=random&amp;cat=${cat}">Image al&eacute;atoire</a>
				</div>
				<div class="footer-col1" id="col1_newsletter" style="width: 282px;">
					<h2 class="h2_right_content">Newsletter:</h2>
					<form id="newsletter_form" style="text-align: center; margin-top: 15px;" action="ajaxnewsletter">
					 	<input type="email" name="email" id="newsletter_email" placeholder="Adresse Email" required>
						<input type="submit" class="submitClass2">	
					</form>
					<div id="newsletter_result" style="display: none"></div>
				</div>	
				<div class="footer-col1">	
					<h2 class="h2_right_content">Contactez nous :</h2>
					<form action="ajaxcontact" id="contact_form" style="text-align: center;" method="post">
						<input type="email" style="width: 250px;" name="email" placeholder="Votre email..." />
						<input type="text" style="width: 250px;" name="subject" placeholder="Sujet de votre message..." />
						<textarea name="message" style="width: 250px;" placeholder="Votre message..."></textarea><br/>
						<input type="submit" class="submit" name="submit" value="Envoyer" />
					</form>
					<div id="contact_result" style="display: none"></div>
				</div>
				<div style="clear: both"></div>
				</div>
				<div id="copyright">Copyright © 2014-2015 <a style="color: #2db3e8; font-weight: bold; text-decoration: none;" href="http://www.snapchat-hei.fr">Snapchat-hei.fr</a>.Tous droits réservés. Designed by <a style="color: #2db3e8; font-weight: bold; text-decoration: none;" href="http://michael-rupp.fr">Michaël RUPP</a>.</div>
				<div id="img_valid">
					<img src="images/w3c.jpg" style="height: 30px;margin-right: 30px;margin-top: -10px;" alt="Html5 Html 5 Css3 Css 3" />
					<img src="images/html5css3.png" style="height: 30px;margin-right: 30px;margin-top: -10px;" alt="Html5 Html 5 Css3 Css 3" />
				</div>
				<div style="clear: both"></div>
			</div>
		</footer>
		
		<div id="global"></div>
		<a id="goTop" href="#"></a>
		
		<!-- à Compresser plus tard -->
		<script type="text/javascript" src="scripts/insc.js"></script>
		<script type="text/javascript" src="scripts/ban.js"></script>
		<script type="text/javascript" src="scripts/ajaxdeleteimage.js"></script>
		<script type="text/javascript" src="scripts/ajaxnewsletter.js"></script>
		<script type="text/javascript" src="scripts/ajaxcontact.js"></script>
		<script type="text/javascript" src="scripts/voteshow.js"></script>
		<script type="text/javascript" src="scripts/manage_toolbar.js"></script>
	</body>
</html>