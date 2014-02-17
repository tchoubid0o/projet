package hei.devweb.dao.impl;

import hei.devweb.dao.UsersDao;
import hei.devweb.model.Users;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl implements UsersDao {

	public UsersDaoImpl() {
		
	}

	public List<Users> listerUsers() {
		List<Users> liste = new ArrayList<Users>();
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("SELECT * FROM users");

			while (results.next()) {
				Users user = new Users(
						results.getInt("idUser"),
						results.getString("user_pseudo"),
						results.getString("user_mail"),
						results.getString("user_password"),
						results.getString("user_ip"),
						results.getDate("user_registered"));
				liste.add(user);
			}
			results.close();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}


		return liste;
	}

	public Integer countUsersMailUsed(String user_mail) {
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE user_mail = ?");
			stmt.setString(1, user_mail);

			ResultSet results = null;
			try {
				results = stmt.executeQuery();

				if(results.next()){
					return results.getInt(1);
				}

			} catch (SQLException e1) {
				System.out.println("Erreur lors de l'exécution de la requête comptant le nombre de mail présent dans la bdd");
				e1.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//retourne une erreur
		return -1;
	}

	public Integer countUsersPseudoUsed(String user_pseudo) {
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE user_pseudo = ?");
			stmt.setString(1, user_pseudo);

			ResultSet results = null;
			try {
				results = stmt.executeQuery();

				if(results.next()){
					return results.getInt(1);
				}

			} catch (SQLException e1) {
				System.out.println("Erreur lors de l'exécution de la requête comptant le nombre de pseudo présent dans la bdd");
				e1.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		//retourne une erreur
		return -1;
	}

	@Override
	public void ajouterUser(Users user) throws UnknownHostException {
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();

			
			String ip = InetAddress.getLocalHost().getHostAddress();

			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `users`(`user_pseudo`,`user_mail`,`user_password`, `user_ip`, `user_registered`) VALUES(?,?,?,?, NOW())");
			stmt.setString(1, user.getUser_pseudo());
			stmt.setString(2, user.getUser_mail());
			stmt.setString(3, user.getUser_password());
			stmt.setString(4, ip);
			stmt.executeUpdate();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String HashMyPassword(String user_password) throws Exception{
		// On crypte le password en MD5 avec un salt le tout 3 fois
		user_password = "projet"+user_password+"JEE";
		for(int a = 0; a < 3; a++){
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(user_password.getBytes());

			byte byteData[] = md.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			user_password = sb.toString();
		}

		return user_password;
		//Renvoi un varchar(32)
	}
	
	public String GetPasswordConnexion(String user_pseudo){
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();

			PreparedStatement stmt = connection.prepareStatement("SELECT user_password FROM users WHERE user_pseudo = ?");
			stmt.setString(1, user_pseudo);

			ResultSet results = null;
			try {
				results = stmt.executeQuery();

				if(results.next()){
					return results.getString(1);
				}

			} catch (SQLException e1) {
				System.out.println("Erreur lors de l'exécution de la requête permettant de récupérer le password hasher pour la connexion");
				e1.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "erreur";
	}
	
	public Integer getTheIdUser(String user_pseudo){
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();

			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE user_pseudo = ?");
			stmt.setString(1, user_pseudo);
			ResultSet results = null;
			try {
				results = stmt.executeQuery();
				if(results.next()){
					//Retourne juste l'idUser
					return results.getInt(1);
				}

			} catch (SQLException e1) {
				System.out.println("Erreur lors de l'exécution de la requête permettant de récupérer l'idUser");
				e1.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public String getUserPseudo(Integer idUser){
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();

			PreparedStatement stmt = connection.prepareStatement("SELECT user_pseudo FROM users WHERE idUser = ?");
			stmt.setInt(1, idUser);
			ResultSet results = null;
			try {
				results = stmt.executeQuery();
				if(results.next()){
					//Retourne juste l'idUser
					return results.getString(1);
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "erreur";
	}
}
