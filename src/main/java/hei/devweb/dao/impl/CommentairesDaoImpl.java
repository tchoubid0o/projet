package hei.devweb.dao.impl;

import hei.devweb.dao.CommentairesDao;
import hei.devweb.model.Commentaires;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CommentairesDaoImpl implements CommentairesDao {
	
	public CommentairesDaoImpl() {
	}
	
	public List<Commentaires> listerCommentaires(String cleeImg) {
		List<Commentaires> liste = new ArrayList<Commentaires>();
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM commentaires WHERE cleeImg = ?");
			stmt.setString(1, cleeImg);
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				Commentaires commentaire = new Commentaires(
						results.getInt("commentId"),
						results.getDate("commentDate"),
						results.getString("commentCOntent"),
						results.getInt("idUser"),
						results.getString("cleeImg"));
				liste.add(commentaire);
				
			}			
			results.close();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}


		return liste;
	}
	
	public void ajoutCommentaire(String commentContent, Integer idUser, String cleeImg){
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `commentaires`(`commentDate`,`commentContent`,`idUser`,`cleeImg`) VALUES(NOW(),?,?,?)");
			stmt.setString(1, commentContent);
			stmt.setInt(2, idUser);
			stmt.setString(3, cleeImg);
			stmt.executeUpdate();
		
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
