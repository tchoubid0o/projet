package hei.devweb.controllers;

import hei.devweb.metier.ImagesManager;
import hei.devweb.metier.UsersManager;
import hei.devweb.model.Images;

import java.io.IOException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConnexionServlet extends GlobalInformationsServlet{

	private static final long serialVersionUID = -4927724527514530512L;

	@Override
	public void getRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Nécessaire à l'affichage des dernières images publiées
		List<Images> images = ImagesManager.getInstance().listerImages();
		request.setAttribute("images", images);
		// Fin dernière images publiées

		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/connexion.jsp");
		view.forward(request, response);
	}

	@Override
	public void postRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Nécessaire à l'affichage des dernières images publiées
		List<Images> images = ImagesManager.getInstance().listerImages();
		request.setAttribute("images", images);
		// Fin dernière images publiées

		Map<String, String> erreursMessage = new HashMap<String, String>();

		String user_pseudo = request.getParameter("user_pseudo");
		String user_password = request.getParameter("user_password");
		String resultat = "";

		//Validation pseudo
		try {
			validationPseudo(user_pseudo);
		} catch ( Exception e ) {
			erreursMessage.put("user_pseudo", e.getMessage() );
		}
		//Validation password
		try {
			validationMotsDePasse(user_pseudo, user_password);
		} catch ( Exception e ) {
			erreursMessage.put("user_password", e.getMessage() );
		}

		if ( erreursMessage.isEmpty() ) {
			resultat = "Succès connexion.";
			request.getSession().setAttribute("user_pseudo",user_pseudo);
			request.getSession().setAttribute("idUser",UsersManager.getInstance().getTheIdUser(user_pseudo));
			
			response.sendRedirect("profil");
		} else {
			resultat = "Échec de la connexion.";
			//Stockage des erreurs et du résultat
			request.setAttribute("erreurs", erreursMessage );
			request.setAttribute("resultat", resultat );

			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/connexion.jsp");
			view.forward(request, response);
		}

	}

	private void validationPseudo(String user_pseudo) throws Exception {
		Integer nbPseudo = UsersManager.getInstance().countUsersPseudoUsed(user_pseudo);
		if(nbPseudo == 0){
			throw new Exception( "<div class='erreur_message'>Le pseudo n'existe pas.</div>" );
		}
	}

	private void validationMotsDePasse(String user_pseudo, String user_password) throws Exception {
		String passwordStockedInBdd = UsersManager.getInstance().GetPasswordConnexion(user_pseudo);
		//On hash le password posté par l'utilisateur pour vérifier les deux 
		String user_passwordHashed = UsersManager.getInstance().HashMyPassword(user_password);
		if(!(user_passwordHashed.equals(passwordStockedInBdd))){
			throw new Exception( "<div class='erreur_message'>Vous avez entré un mauvais mot de passe.</div>" );
		}
	}

}
