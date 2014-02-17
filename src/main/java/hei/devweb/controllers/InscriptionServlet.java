package hei.devweb.controllers;

import hei.devweb.metier.ImagesManager;
import hei.devweb.metier.UsersManager;
import hei.devweb.model.Images;
import hei.devweb.model.Users;

import java.io.IOException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InscriptionServlet extends GlobalInformationsServlet{

	private static final long serialVersionUID = -4927724527514530512L;

	@Override
	public void getRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		//Nécessaire à l'affichage des dernières images publiées
		List<Images> images = ImagesManager.getInstance().listerImages();
		request.setAttribute("images", images);
		// Fin dernière images publiées
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/inscription.jsp");
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
		String user_mail = request.getParameter("user_mail");
		String user_password = request.getParameter("user_password");
		String user_password_verif = request.getParameter("user_password_verif");
		String resultat = "";

		//Validation pseudo
        try {
            validationPseudo(user_pseudo);
        } catch ( Exception e ) {
            erreursMessage.put("user_pseudo", e.getMessage() );
        }
        //Validation mail
        try {
            validationEmail(user_mail);
        } catch ( Exception e ) {
            erreursMessage.put("user_mail", e.getMessage() );
        }
		//Validation password
		try {
			validationMotsDePasse(user_password,user_password_verif);
		} catch ( Exception e ) {
			erreursMessage.put("user_password", e.getMessage() );
		}

		if ( erreursMessage.isEmpty() ) {
			//On crypte le password
			try {
				String password = UsersManager.getInstance().HashMyPassword(user_password);
				UsersManager.getInstance().ajouterUser(new Users(null, user_pseudo, user_mail, password, null, null));
				request.getSession().setAttribute("idUser", UsersManager.getInstance().getTheIdUser(user_pseudo));
				request.getSession().setAttribute("user_pseudo",user_pseudo);
				request.getSession().setAttribute("user_mail",user_mail);
				resultat = "<div class='erreur_message' style='color: green !important;'>Succès de l'inscription.</div>";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			resultat = "<div class='erreur_message'>Échec de l'inscription.</div>";
		}

		//Stockage des erreurs et du résultat
		request.setAttribute("erreurs", erreursMessage );
		request.setAttribute("resultat", resultat );
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/inscription.jsp");
		view.forward(request, response);
	}

	private void validationPseudo(String user_pseudo) throws Exception {
		if ( user_pseudo != null && user_pseudo.trim().length() < 3 ) {
			throw new Exception( "<div class='erreur_message'>Le nom d'utilisateur doit contenir au moins 3 caractères.</div>" );
		}
		else{
			Integer nbPseudo = UsersManager.getInstance().countUsersPseudoUsed(user_pseudo);
			if(nbPseudo != 0){
				throw new Exception( "<div class='erreur_message'>Le pseudo est déjà utilisé.</div>" );
			}
		}
	}

	private void validationEmail(String user_mail) throws Exception {
		if ( user_mail != null && user_mail.trim().length() != 0 ) {
			if ( !user_mail.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
				throw new Exception( "<div class='erreur_message'>Merci de saisir une adresse mail valide.</div>" );
			}
			else{
				Integer nbMail = UsersManager.getInstance().countUsersMailUsed(user_mail);
				if(nbMail != 0){
					throw new Exception( "<div class='erreur_message'>L'adresse mail est déjà utilisée.</div>" );
				}
			}
		} else {
			throw new Exception( "<div class='erreur_message'>Merci de saisir une adresse mail.</div>" );
		}
	}


	private void validationMotsDePasse(String user_password,
			String user_password_verif) throws Exception {
		if (user_password != null && user_password.trim().length() != 0 && user_password_verif != null && user_password_verif.trim().length() != 0) {
			if (!user_password.equals(user_password_verif)) {
				throw new Exception("<div class='erreur_message'>Les mots de passe entrés sont différents, merci de les saisir à nouveau.</div>");
			} else if (user_password.trim().length() < 3) {
				throw new Exception("<div class='erreur_message'>Les mots de passe doivent contenir au moins 3 caractères.</div>");
			}
		} else {
			throw new Exception("<div class='erreur_message'>Merci de saisir et confirmer votre mot de passe.</div>");
		}
	}

}
