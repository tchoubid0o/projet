package hei.devweb.dao;

import hei.devweb.model.Commentaires;

import java.util.List;

public interface CommentairesDao {
	
	public List<Commentaires> listerCommentaires(String cleeImg);

	public void ajoutCommentaire(String commentContent, Integer idUser,
			String cleeImg);
	
}
