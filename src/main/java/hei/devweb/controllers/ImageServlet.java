package hei.devweb.controllers;

import hei.devweb.metier.CommentairesManager;
import hei.devweb.metier.ImagesManager;
import hei.devweb.metier.UsersManager;
import hei.devweb.model.Commentaires;
import hei.devweb.model.Images;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends GlobalInformationsServlet{

	private static final long serialVersionUID = -2586689199205662670L;

	@Override
	public void getRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cleeImg = request.getParameter("id");
		if(cleeImg.equalsIgnoreCase("random")){
			cleeImg = ImagesManager.getInstance().generateCleeImg();
			
			Images image = ImagesManager.getInstance().getImage(cleeImg);		
			request.setAttribute("image", image);
			
			//On récupère les commentaires de l'image
			
			List<Commentaires> commentaires = CommentairesManager.getInstance().listerCommentaires(image.getCleeImg());
			request.setAttribute("commentaires", commentaires);
			request.setAttribute("commentaires_size", commentaires.size());
		}
		else{
			Images image = ImagesManager.getInstance().getImage(cleeImg);		
			request.setAttribute("image", image);
			
			//On récupère les commentaires de l'image
			
			List<Commentaires> commentaires = CommentairesManager.getInstance().listerCommentaires(image.getCleeImg());
			request.setAttribute("commentaires", commentaires);
			request.setAttribute("commentaires_size", commentaires.size());
		}
		request.setAttribute("prevImg", ImagesManager.getInstance().getPrevCleeImg(cleeImg));
		request.setAttribute("nextImg", ImagesManager.getInstance().getNextCleeImg(cleeImg));
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/image.jsp");
		view.forward(request, response);
	}
	
	//Si on ajoute un commentaire alors on passe dans le doPost
	@Override
	public void postRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getParameter("commentContent") != null){
		
		Map<String, String> erreursMessage = new HashMap<String, String>();

		Integer idUser = UsersManager.getInstance().getTheIdUser((String)request.getSession().getAttribute("user_pseudo"));
		String commentContent = request.getParameter("commentContent");
		String cleeImg = request.getParameter("cleeImg");
		
		String resultat = "";

		//Validation connexion
        try {
            validationIdUser(idUser);
        } catch ( Exception e ) {
            erreursMessage.put("idUser", e.getMessage() );
        }
        //Validation contenu commentaire
        try {
            validationContenu(commentContent);
        } catch ( Exception e ) {
            erreursMessage.put("commentContent", e.getMessage() );
        }

		if ( erreursMessage.isEmpty() ) {
			try {
				CommentairesManager.getInstance().ajoutCommentaire(commentContent, idUser, cleeImg);
				resultat = "<div class='erreur_message' style='color: green !important;'>Le commentaire a correctement été ajouté.</div>";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			resultat = "<div class='erreur_message'>Erreur dans l'ajout du commentaire.</div>";
		}

		//Stockage des erreurs et du résultat
		request.setAttribute("erreurs", erreursMessage );
		request.setAttribute("resultat", resultat );
		
		Images image = ImagesManager.getInstance().getImage(cleeImg);		
		request.setAttribute("image", image);
		List<Commentaires> commentaires = CommentairesManager.getInstance().listerCommentaires(image.getCleeImg());
		request.setAttribute("commentaires", commentaires);
		request.setAttribute("commentaires_size", commentaires.size());
		
		request.setAttribute("prevImg", ImagesManager.getInstance().getPrevCleeImg(cleeImg));
		request.setAttribute("nextImg", ImagesManager.getInstance().getNextCleeImg(cleeImg));
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/image.jsp?id="+cleeImg);
		view.forward(request, response);
		}
	}
	
	private void validationIdUser(Integer idUser) throws Exception {
		if ( idUser == null) {
			throw new Exception( "<div class='erreur_message'>Vous devez être connecté.</div>" );
		}
	}
	
	private void  validationContenu(String commentContent) throws Exception{
		if(commentContent == null){
			throw new Exception("<div class='erreur_message'>Le commentaire ne doit pas être vide.</div>" );
		}
	}

}
