package hei.devweb.metier;

import hei.devweb.dao.CommentairesDao;
import hei.devweb.dao.impl.CommentairesDaoImpl;
import hei.devweb.model.Commentaires;

import java.util.List;

public class CommentairesManager {

	private static CommentairesManager instance;

	private CommentairesDao commentairesDao = new CommentairesDaoImpl();

	private CommentairesManager() {
	}

	public static CommentairesManager getInstance() {
		if (instance == null) {
			instance = new CommentairesManager();
		}
		return instance;
	}

	public List<Commentaires> listerCommentaires(String cleeImg) {
		return commentairesDao.listerCommentaires(cleeImg);
	}
	
	public void ajoutCommentaire(String commentContent, Integer idUser, String cleeImg){
		commentairesDao.ajoutCommentaire(commentContent, idUser, cleeImg);
	}
	
}
