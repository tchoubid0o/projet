package hei.devweb.controllers;

import hei.devweb.metier.ImagesManager;
import hei.devweb.metier.VotesManager;
import hei.devweb.model.Images;
import hei.devweb.model.Users;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VoteServlet extends GlobalInformationsServlet{

	private static final long serialVersionUID = -2813511777591775567L;

	@Override
	public void getRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cleeImg = request.getParameter("id");
		Images image = ImagesManager.getInstance().getImage(cleeImg);		
		request.setAttribute("image", image);
		
		Map<String, String> erreursMessage = new HashMap<String, String>();
		String resultat = "";
		
		//Pour voter, on passe en GET l'id de l'image, et le vote
		
		Integer ptsVote = Integer.parseInt(request.getParameter("vote"));
		
		String user_pseudo = (String) request.getSession().getAttribute("user_pseudo");
		//Validation connexion
        try {
            validationConnexion(user_pseudo);
        } catch ( Exception e ) {
            erreursMessage.put("user_connexion", e.getMessage() );
        }
        
        if ( erreursMessage.isEmpty() ) {
        	try{
        		Users user = VotesManager.getInstance().getUserVoted(user_pseudo);
        		resultat = VotesManager.getInstance().setMyVote(user, cleeImg, ptsVote, user_pseudo);
        		erreursMessage.put("user_connexion", resultat );
        	}catch(Exception e){
        		erreursMessage.put("user_result", e.getMessage() );
        	}
        }
        
      //Stockage des erreurs et du résultat
      	request.setAttribute("erreurs", erreursMessage );
      	request.setAttribute("resultat", resultat );
		
      	Images image2 = ImagesManager.getInstance().getImage(cleeImg);		
		request.setAttribute("image", image2);
      	
		request.setAttribute("prevImg", ImagesManager.getInstance().getPrevCleeImg(cleeImg));
		request.setAttribute("nextImg", ImagesManager.getInstance().getNextCleeImg(cleeImg));
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/vote.jsp");
		view.forward(request, response);
	}
	
	@Override
	public void postRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	private void validationConnexion(String user_pseudo) throws Exception {
		if ( user_pseudo == null) {
			throw new Exception( "<div class='erreur_message'>Vous devez être connecté pour pouvoir voter.</div>" );
		}
	}

}
