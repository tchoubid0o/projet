package hei.devweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hei.devweb.dao.VotesDao;
import hei.devweb.metier.UsersManager;
import hei.devweb.model.Users;

public class VotesDaoImpl implements VotesDao {

	public VotesDaoImpl() {
	}

	public Integer getPtsImage(String cleeImg){
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			PreparedStatement stmt = connection.prepareStatement("SELECT ptsImg FROM images WHERE cleeImg = ?");
			stmt.setString(1, cleeImg);

			ResultSet results = null;
			try {
				results = stmt.executeQuery();

				if(results.next()){
					return results.getInt(1);
				}

			} catch (SQLException e1) {
				System.out.println("<div class='erreur_message'>Erreur lors de l'exécution de la requête permettant de récupérer les pts de l'image</div>");
				e1.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//retourne une erreur
		return -1;
	}

	public Users getUserVoted(String user_pseudo){
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE user_pseudo = ?");
			stmt.setString(1, user_pseudo);
			ResultSet results = stmt.executeQuery();

			while (results.next()) {
				Users user = new Users(
						results.getInt("idUser"),
						results.getString("user_pseudo"),
						results.getString("user_mail"),
						results.getString("user_password"),
						results.getString("user_ip"),
						results.getDate("user_registered"));

				results.close();
				stmt.close();
				connection.close();

				//On retourne qu'une seule image
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		//Si aucun résultat
		return null;
	}

	public boolean checkAlreadyVoted(Integer idUser, String cleeImg){
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM historiques WHERE idUser = ? AND cleeImg = ?");
			stmt.setInt(1, idUser);
			stmt.setString(2, cleeImg);

			ResultSet results = null;
			try {
				results = stmt.executeQuery();

				if(results.next()){
					if(results.getInt(1) > 0){
						return true;
					}
					else{
						return false;
					}
				}

			} catch (SQLException e1) {
				System.out.println("<div class='erreur_message'>Erreur lors de l'exécution de la requête comptant le nombre de pseudo présent dans la bdd</div>");
				e1.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		//retourne une erreur
		return true;
	}

	public String setMyVote(Users user, String cleeImg, Integer ptsVote, String user_pseudo){
		String error = "";
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Integer idUser = UsersManager.getInstance().getTheIdUser(user_pseudo);
			if(ptsVote == 1 || ptsVote == -1){
				boolean check1 = checkAlreadyVoted(idUser,cleeImg);
				if(check1){
					error = "<div class='erreur_message'>Vous avez déjà voté pour cette image</div>";
				}
				else{

					PreparedStatement stmt = connection.prepareStatement("INSERT INTO `historiques`(`idUSer`,`cleeImg`, `ptsVote`) VALUES(?,?,?)");
					stmt.setInt(1, idUser);
					stmt.setString(2, cleeImg);
					stmt.setInt(3, ptsVote);
					stmt.executeUpdate();
					stmt.close();
					
					//On récupère les points de l'image
					Integer ptsImg = getPtsImage(cleeImg);
					//On met à jour les points de l'image dans la table images
					PreparedStatement stmt2 = connection.prepareStatement("UPDATE images SET ptsImg = ? WHERE cleeImg = ?");
					stmt2.setInt(1, ptsImg+(ptsVote));
					stmt2.setString(2, cleeImg);
					stmt2.executeUpdate();
					stmt2.close();
					
					connection.close();
					
					error = "<div class='erreur_message' style='color: green !important;'>Le vote a bien été pris en compte.</div>";
				}
			}
			//Si ptsVote ne correspond ni à 1 ou -1 c'est que l'utilisateur a tenté de modifié la valeur du GET ptsVote
			else{
				error = "<div class='erreur_message'>Erreur dans le vote</div>";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return error;
	}


}
