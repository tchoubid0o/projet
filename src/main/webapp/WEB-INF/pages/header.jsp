<!DOCTYPE html>
<html>
	<head>
		<title>Snapchat HEI - Partagez vos meilleurs Snapachats</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- Description de la page -->
		<meta name="description" content="Partagez vos meilleurs Snapchat HEI grâce à ce merveilleux site!" />
		<!-- Mots-clés de la page -->
		<meta name="keywords" content="Snapchat HEI, snapchat, HEI, hautes études d'ingénieurs, ecole, Lille, applications, web, java, javaEE, j2ee, devweb" />
			
		<!-- Géo Meta Tag -->
		<meta name="geo.region" content="FR-75" />
		<meta name="geo.placename" content="Lille" />
		<meta name="geo.position" content="50.62925;3.057256" />
		<meta name="ICBM" content="50.62925, 3.057256" />
		
		<meta name="viewport" content="width=device-width">
		
		<link rel="stylesheet" href="style.css" type="text/css" media="screen">
		<link rel="stylesheet" media="screen and (max-width: 975px) and (min-width: 660px)" href="style_975.css" type="text/css" />
		<link rel="stylesheet" media="screen and (max-width: 660px) and (min-width: 441px)" href="style_660.css" type="text/css" />
		<link rel="stylesheet" media="screen and (max-width: 440px)" href="style_440.css" type="text/css" />
		
		<script type="text/javascript" src="//code.jquery.com/jquery-1.10.2.min.js"></script>
		
		<link rel="icon" type="image/jpg" href="images/favicon.jpg" />
	</head>
	
	<body>
		<!-- Menu Inscription Caché -->
		<div id="registertop">
			<div class="registerh" style="text-align: center;">
				<h2 class="hidden_title">Inscription</h2>
				<form action="inscription" method="post">
					<label for="user_pseudo" style="text-align: left;">Votre pseudo</label><br />
					 <input type="text" placeholder="pseudo" name="user_pseudo" id="user_pseudo" required /><br /> 
					 <label	for="user_mail">Votre adresse mail</label><br /> 
					 <input	type="email" placeholder="Email" name="user_mail" id="user_mail" required /><br /> 
					 <label for="user_password">Votre mot de passe</label><br /> 
					 <input type="password" placeholder="Password..." name="user_password" id="user_password" required /><br /> 
					 <label	for="user_password_verif">Confirmez votre mot de passe</label><br />
					<input type="password" placeholder="Password..." name="user_password_verif" id="user_password_verif" required /><br /><br /> 
					
					<input type="hidden" name="inscription" value="1" />
					<div style="margin: auto;">
						<input class="submit" type="submit" value="Valider"	id="submitregister"	/>
					</div>
				</form>
			</div>
		</div>
		<!-- Menu Connexion Caché -->
		<div id="logintop">
			<div class="loginh" style="text-align: center;">
	
				<h2 class="hidden_title">Connexion</h2>
				<form action="connexion" method="post">
					<label for="user_pseudo2">Pseudo :</label><br />
					<div>
						<input type="text" name="user_pseudo" id="user_pseudo2"	placeholder="Pseudonyme" />
					</div>
					<br /> <label for="user_password2">Mot de passe :</label><br />
					<div>
						<input type="password" name="user_password" id="user_password2"	placeholder="Password..." />
					</div>
					<br /> <br /> <input type="hidden" name="connexion" value="1" />
					<div style="margin: auto;">
						<input class="submit" type="submit" value="Login !" id="submit2"/>
					</div>
				</form>
	
			</div>
		</div>
		<!-- Menu caché: Inscription et connexion si l'utilisateur clique sur le vote sans être connecté -->
		<div id="menu_insc_log_vote">
			<div style="float: left;">
				<div id="registertop2">
					<div class="registerh" style="text-align: center;">
						<h2 class="hidden_title">Inscription</h2>
						<form action="inscription" method="post">
							<label for="user_pseudo3" style="text-align: left;">Votre pseudo</label><br /> 
							<input type="text" placeholder="pseudo"	name="user_pseudo" id="user_pseudo3" required /><br /> 
							<label for="user_mail3">Votre adresse mail</label><br /> 
							<input type="email" placeholder="Email" name="user_mail" id="user_mail3"	required /><br /> 
							<label for="user_password3">Votre mot de passe</label><br />
							<input type="password" placeholder="Password..." name="user_password" id="user_password3" required /><br /> 
							<label for="user_password_verif3">Confirmez votre mot de passe</label><br />
							<input type="password" placeholder="Password..." name="user_password_verif" id="user_password_verif3" required /><br /><br /> 
							<input type="hidden" name="inscription" value="1" />
							
							<div style="margin: auto;">
								<input class="submit" type="submit" value="Valider"	id="submitregister3" />
							</div>
						</form>
					</div>
				</div>
			</div>
			<div id="logintop2">
				<div class="loginh" style="text-align: center;">
	
					<h2 class="hidden_title">Connexion</h2>
					<form action="connexion" method="post">
						<label for="user_pseudo4">Pseudo :</label><br />
						<div>
							<input type="text" name="user_pseudo" id="user_pseudo4"	placeholder="Pseudonyme" />
						</div>
						<br /> <label for="user_password4">Mot de passe :</label><br />
						<div>
							<input type="password" name="user_password" id="user_password4" placeholder="Password..." />
						</div>
						<br /> <br /> <input type="hidden" name="connexion" value="1" />
						<div style="margin: auto;">
							<input class="submit" type="submit" value="Login !" id="submit4" />
						</div>
					</form>
	
				</div>
			</div>
			<div style="clear: both"></div>
		</div>
		<header>
			<div id="head_center">
				<nav>
					<ul id="menu">
						<li class="img_upload"><a href="profil"><img src="images/upload.jpg" alt="" /></a></li>
						<li class="menu1"><a class="${pageName == 'index' ? 'head_active' : ''}" href="index" title="Snapchat HEI">Home</a></li>
							<%
							if (request.getSession().getAttribute("user_pseudo") == null) {
								%>
								<li class="menu2"><a class="${pageName == 'connexion' ? 'head_active' : ''}" href="connexion" title="Connexion">Connexion</a></li>
								<li class="menu3"><a class="${pageName == 'inscription' ? 'head_active' : ''}" href="inscription" title="Inscription">Inscription</a></li>
								<%
							} else {
								%>
								<li class="menu2"><a class="${pageName == 'profil' ? 'head_active' : ''}" href="profil" title="Profil">Profil</a></li>
								<li class="menu3"><a class="${pageName == 'deconnexion' ? 'head_active' : ''}" href="deconnexion" title="Deconnexion">Deconnexion</a></li>
								<%
							}
							%>
						<li class="menu4"><a class="${pageName == 'faq' ? 'head_active' : ''}" href="faq" title="faq">Faq</a></li>
					</ul>
				</nav>
			</div>
		</header>
		<div id="logosite">
			<h1>
				Partagez vos meilleurs <span class="colorBlue">Snapchat</span>
				HEI
			</h1>
		</div>
		<div id="banniere"></div>
		<div id="showhidebar" data-alt="hide_header">
			<div id="button_ban"></div>
		</div>
		<div id="infos_site">
			<div class="width1000">
				<div id="cat1">
					<div class="cat_left">
						<img class="img_cat" src="images/uploadic.png" alt="" />
					</div>
					<div class="cat_right">
						<span class="title_cat">Images Partag&eacute;es</span><br /> 
						<span class="nb_cat">${nbImages}</span>
					</div>
				</div>
				<div id="cat2">
					<div class="cat_left">
						<img class="img_cat" src="images/membersic.png" alt="" />
					</div>
					<div class="cat_right">
						<span class="title_cat">Membres</span><br /> 
						<span class="nb_cat">${nbUsers}</span>
					</div>
				</div>
				<div id="cat3">
					<div class="cat_left">
						<img class="img_cat" src="images/votesic.png" alt="" />
					</div>
					<div class="cat_right">
						<span class="title_cat">Votes</span><br /> 
						<span class="nb_cat">${nbVotes}</span>
					</div>
				</div>
			</div>
		</div>
	
		<%
			if (request.getSession().getAttribute("user_pseudo") == null) {
		%>
		<div id="registername"></div>
		<div id="loginname"></div>
		<%
			} else {
		%>
		<a href="deconnexion"><div id="deconnexionname"></div></a>
		<a href="profil"><div id="profilname"></div></a>
		<% } %>