package hei.devweb.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import hei.devweb.dao.GlobalInformationsDao;

public class GlobalInformationsDaoImpl implements GlobalInformationsDao {
	public Integer countImagesDataBase(){
		Integer nbImages = 0;
		try {
			
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("SELECT COUNT(*) FROM images");
			
			while (results.next()) {
				nbImages = results.getInt(1);
			}
			
			results.close();
			stmt.close();
			connection.close();
			
			return nbImages;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//erreur
		return -1;
	}
	
	public Integer countUsersDataBase(){
		Integer nbUsers = 0;
		try {
			
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("SELECT COUNT(*) FROM users");
			
			while (results.next()) {
				nbUsers = results.getInt(1);
			}
			
			results.close();
			stmt.close();
			connection.close();
			
			return nbUsers;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//erreur
		return -1;
	}
	
	public Integer countVotesDataBase(){
		Integer nbVotes = 0;
		try {
			
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("SELECT COUNT(*) FROM historiques");
			
			while (results.next()) {
				nbVotes = results.getInt(1);
			}
			
			results.close();
			stmt.close();
			connection.close();
			
			return nbVotes;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//erreur
		return -1;
	}
	
	public ArrayList<String> returnAllCategorie(){
		ArrayList<String> maListe = new ArrayList<String>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
	
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("SELECT libelleCategorie FROM categories ORDER BY idCategorie ASC");

			while (results.next()) {
				maListe.add(results.getString(1));
			}
				
			results.close();
			stmt.close();
			connection.close();
				
			return maListe;
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//erreur
		return maListe;
	}
}
