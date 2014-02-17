package hei.devweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hei.devweb.dao.ImagesDao;
import hei.devweb.model.Images;

public class ImagesDaoImpl implements ImagesDao {

	public ImagesDaoImpl() {
	}

	public List<Images> listerImages() {
		List<Images> liste = new ArrayList<Images>();
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("SELECT * FROM images ORDER BY ptsImg DESC");

			while (results.next()) {
				Images image = new Images(
						results.getInt("idImg"),
						results.getString("cleeImg"),
						results.getString("extImg"),
						//On se protège des injections grâce à la méthode htmlSpechialChars
						htmlSpecialChars(results.getString("titreImg")),
						htmlSpecialChars(results.getString("textImg")),
						results.getDate("dateImg"),
						results.getInt("ptsImg"),
						results.getInt("idUser"),
						results.getInt("idCategorie"));
				liste.add(image);
			}
			results.close();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}


		return liste;
	}

	public List<Images> listerAndSortImages(String sortChoice, Integer idCategorie) {
		List<Images> liste = new ArrayList<Images>();

		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			ResultSet results = null;
			if(idCategorie != 0){
				PreparedStatement stmt2 = null;
				if(sortChoice.equalsIgnoreCase("popularity")){
					stmt2 = connection.prepareStatement("SELECT * FROM images WHERE idCategorie = ?  ORDER BY ptsImg DESC");
					stmt2.setInt(1, idCategorie);
					results = stmt2.executeQuery();
				}
				else if(sortChoice.equalsIgnoreCase("newest")){
					stmt2 = connection.prepareStatement("SELECT * FROM images WHERE idCategorie = ?  ORDER BY dateImg DESC");
					stmt2.setInt(1, idCategorie);
					results = stmt2.executeQuery();
				}
				else if(sortChoice.equalsIgnoreCase("cat")){
					stmt2 = connection.prepareStatement("SELECT * FROM images WHERE idCategorie = ?  ORDER BY ptsImg DESC");
					stmt2.setInt(1, idCategorie);
					results = stmt2.executeQuery();
				}
				else{
					stmt2 = connection.prepareStatement("SELECT * FROM images WHERE idCategorie = ?  ORDER BY dateImg DESC");
					stmt2.setInt(1, idCategorie);
					results = stmt2.executeQuery();
				}

				while (results.next()) {
					Images image = new Images(
							results.getInt("idImg"),
							results.getString("cleeImg"),
							results.getString("extImg"),
							//On se protège des injections grâce à la méthode htmlSpechialChars
							htmlSpecialChars(results.getString("titreImg")),
							htmlSpecialChars(results.getString("textImg")),
							results.getDate("dateImg"),
							results.getInt("ptsImg"),
							results.getInt("idUser"),
							results.getInt("idCategorie"));
					liste.add(image);
				}
				results.close();
				stmt2.close();
				connection.close();
			}
			//On a pas de catégorie
			else{
				Statement stmt = connection.createStatement();

				if(sortChoice.equalsIgnoreCase("popularity")){
					results = stmt.executeQuery("SELECT * FROM images ORDER BY ptsImg DESC");
				}
				else if(sortChoice.equalsIgnoreCase("newest")){
					results = stmt.executeQuery("SELECT * FROM images ORDER BY dateImg DESC");
				}
				else if(sortChoice.equalsIgnoreCase("cat")){
					PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM images WHERE idCategorie = ?  ORDER BY ptsImg DESC");
					stmt2.setInt(1, idCategorie);
					results = stmt2.executeQuery();
				}
				else{
					results = stmt.executeQuery("SELECT * FROM images ORDER BY dateImg DESC");
				}

				while (results.next()) {
					Images image = new Images(
							results.getInt("idImg"),
							results.getString("cleeImg"),
							results.getString("extImg"),
							//On se protège des injections grâce à la méthode htmlSpechialChars
							htmlSpecialChars(results.getString("titreImg")),
							htmlSpecialChars(results.getString("textImg")),
							results.getDate("dateImg"),
							results.getInt("ptsImg"),
							results.getInt("idUser"),
							results.getInt("idCategorie"));
					liste.add(image);
				}
				results.close();
				stmt.close();
				stmt.close();
				connection.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}


		return liste;
	}

	public Images getImage(String cleeImg) {
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM images WHERE cleeImg = ?");
			stmt.setString(1, cleeImg);
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				Images image = new Images(
						results.getInt("idImg"),
						results.getString("cleeImg"),
						results.getString("extImg"),
						//On se protège des injections grâce à la méthode htmlSpechialChars
						htmlSpecialChars(results.getString("titreImg")),
						htmlSpecialChars(results.getString("textImg")),
						results.getDate("dateImg"),
						results.getInt("ptsImg"),
						results.getInt("idUser"),
						results.getInt("idCategorie"));

				results.close();
				stmt.close();
				
				connection.close();

				//On retourne qu'une seule image
				return image;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		//Si aucun résultat
		return null;
	}

	@Override
	public String ajouterImage(Images image, Integer idUser) {
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();

			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `images`(`cleeImg`,`extImg`,`titreImg`,`textImg`,`dateImg`,`ptsImg`,`idUser`,`idCategorie`) VALUES(?,?,?,?,NOW(),0,?,?)");
			stmt.setString(1, image.getCleeImg());
			stmt.setString(2, image.getExtImg());
			stmt.setString(3, image.getTitreImg());
			stmt.setString(4, image.getTextImg());
			stmt.setInt(5, idUser);
			stmt.setInt(6, image.getIdCategorie());
			stmt.executeUpdate();
			stmt.close();

			connection.close();
			return "<div class='erreur_message colorBlue'>L'image a correctement été ajoutée.<br/> Pour la visualiser rendez-vous <a style='color: #e85226;' href='image?id="+image.getCleeImg()+"'>ici</a></div>";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "<span style='color: red'>erreur</span>";
	}

	@Override
	public String modifierImage(String cleePrevImg, Images image, Integer idUser) {
		Integer check = 0;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//On commence par vérifier qu'on est bien le propriétaire de l'image
			PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM images WHERE cleeImg = ? AND idUser = ?");
			stmt.setString(1, cleePrevImg);
			stmt.setInt(2, idUser);

			ResultSet results = null;
			try {
				results = stmt.executeQuery();

				if(results.next()){
					check = results.getInt(1);
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			if(check > 0){
				//Si on ne modifie pas l'image
				if(cleePrevImg.equals(image.getCleeImg())){
					PreparedStatement stmt2 = connection.prepareStatement("UPDATE images SET titreImg = ?, textImg = ?, idCategorie = ? WHERE cleeImg = ?");
					stmt2.setString(1, image.getTitreImg());
					stmt2.setString(2, image.getTextImg());
					stmt2.setInt(3, image.getIdCategorie());
					stmt2.setString(4, image.getCleeImg());
					stmt2.executeUpdate();
					stmt2.close();
				}
				else{
					//On ajoute la nouvelle image et on réinitialise les points
					ajouterImage(image, idUser);
					
					PreparedStatement stmt2 = connection.prepareStatement("DELETE FROM historiques WHERE cleeImg = ?");
					stmt2.setString(1, cleePrevImg);
					stmt2.executeUpdate();

					PreparedStatement stmt3 = connection.prepareStatement("DELETE FROM commentaires WHERE cleeImg = ?");
					stmt3.setString(1, cleePrevImg);
					stmt3.executeUpdate();
					
					PreparedStatement stmt4 = connection.prepareStatement("DELETE FROM images WHERE cleeImg = ? AND idUser = ?");
					stmt4.setString(1, cleePrevImg);
					stmt4.setInt(2, idUser);
					stmt4.executeUpdate();
					
					stmt2.close();
					stmt3.close();
					stmt4.close();
				}
				return "<div class='erreur_message' style='color: green !important;'>L'image a correctement été modifiée.<br/> Pour la visualiser rendez-vous <a href='image?id="+image.getCleeImg()+"'>ici</a></div>";
			}
			else{
				return "<div class='erreur_message'>Erreur, vous n'êtes pas le propriétaire de cette image.</div>";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "<span style='color: red'>erreur</span>";
	}

	public List<Images> listerMesImages(Integer idUser) {
		List<Images> liste = new ArrayList<Images>();
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM images WHERE idUser = ? ORDER BY dateImg DESC");
			stmt.setInt(1, idUser);
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				Images image = new Images(
						results.getInt("idImg"),
						results.getString("cleeImg"),
						results.getString("extImg"),
						//On se protège des injections grâce à la méthode htmlSpechialChars
						htmlSpecialChars(results.getString("titreImg")),
						htmlSpecialChars(results.getString("textImg")),
						results.getDate("dateImg"),
						results.getInt("ptsImg"),
						results.getInt("idUser"),
						results.getInt("idCategorie"));
				liste.add(image);
			}
			results.close();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}


		return liste;
	}

	public String htmlSpecialChars(String stringToProtect) {
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<stringToProtect.length(); i++) {
			char c = stringToProtect.charAt(i);

			switch (c) {
			case '<' : 
				sb.append("&lt;");
				break;
			case '>' : 
				sb.append("&gt;");
				break;

			case '&' :
				sb.append("&amp;");
				break;
			case '"' :
				sb.append("&quot;");
				break;
			case '\'' :

				sb.append("&apos;");
				break;
			default:
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public String generateCleeImg(){
		String cleeImg;
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("SELECT COUNT(*) FROM images");

			while (results.next()) {
				Integer generatedNumber = (int)(Math.random()*(results.getInt(1)+ 1));

				List<Images> images = listerImages();

				cleeImg = images.get(generatedNumber).getCleeImg();

				results.close();
				stmt.close();
				connection.close();
				return cleeImg;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//erreur
		return "erreur";
	}
	
	@Override
	public void deleteImage(String cleeImg, Integer idUser) {
		Integer check = 0;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//On commence par vérifier qu'on est bien le propriétaire de l'image
			PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM images WHERE cleeImg = ? AND idUser = ?");
			stmt.setString(1, cleeImg);
			stmt.setInt(2, idUser);

			ResultSet results = null;
			try {
				results = stmt.executeQuery();

				if(results.next()){
					check = results.getInt(1);
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			if(check > 0){									
				PreparedStatement stmt2 = connection.prepareStatement("DELETE FROM historiques WHERE cleeImg = ?");
				stmt2.setString(1, cleeImg);
				stmt2.executeUpdate();

				PreparedStatement stmt3 = connection.prepareStatement("DELETE FROM commentaires WHERE cleeImg = ?");
				stmt3.setString(1, cleeImg);
				stmt3.executeUpdate();
					
				PreparedStatement stmt4 = connection.prepareStatement("DELETE FROM images WHERE cleeImg = ? AND idUser = ?");
				stmt4.setString(1, cleeImg);
				stmt4.setInt(2, idUser);
				stmt4.executeUpdate();
					
				stmt2.close();
				stmt3.close();
				stmt4.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Images> listerImagesSearched(String searchText) {
		List<Images> liste = new ArrayList<Images>();
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM images WHERE (titreImg LIKE ? OR textImg LIKE ?)");
			stmt.setString(1, "%"+searchText+"%");
			stmt.setString(2, "%"+searchText+"%");
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				Images image = new Images(
					results.getInt("idImg"),
					results.getString("cleeImg"),
					results.getString("extImg"),
					//On se protège des injections grâce à la méthode htmlSpechialChars
					htmlSpecialChars(results.getString("titreImg")),
					htmlSpecialChars(results.getString("textImg")),
					results.getDate("dateImg"),
					results.getInt("ptsImg"),
					results.getInt("idUser"),
					results.getInt("idCategorie"));
				liste.add(image);
			}
			results.close();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}
	
	public String getPrevCleeImg(String cleeImg){
		int actualImg = 0;
		int lastImg = 0;
		
		String cleePrevImg = null;
		
		Connection connection;
			try {
				connection = DataSourceProvider.getDataSource().getConnection();
		
				PreparedStatement stmtMyImage = connection.prepareStatement("SELECT idImg FROM images WHERE cleeImg = ?");
				stmtMyImage.setString(1, cleeImg);
				ResultSet results = stmtMyImage.executeQuery();
				while(results.next()){
					actualImg = results.getInt(1);
				}
				
				if(actualImg != 1){
					PreparedStatement stmtPrevImage = connection.prepareStatement("SELECT cleeImg FROM images WHERE idImg = ?");
					stmtPrevImage.setInt(1, (actualImg-1));
					ResultSet results2 = stmtPrevImage.executeQuery();
					while(results2.next()){
						cleePrevImg = results2.getString(1);
					}
				}
				else{
					Statement stmtLastClee = connection.createStatement();
					ResultSet results21 = stmtLastClee.executeQuery("SELECT * FROM images WHERE idImg = (SELECT MAX(idImg) FROM images)");
					while(results21.next()){
						lastImg = results21.getInt(1);
					}
					
					PreparedStatement stmtPrevImage = connection.prepareStatement("SELECT cleeImg FROM images WHERE idImg = ?");
					stmtPrevImage.setInt(1, lastImg);
					ResultSet results2 = stmtPrevImage.executeQuery();
					while(results2.next()){
						cleePrevImg = results2.getString(1);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		
		return cleePrevImg;
	}
	
	public String getNextCleeImg(String cleeImg){
		int actualImg = 0;
		int lastImg = 0;
		
		String cleeNextImg = null;
		
		Connection connection;
		try {
			connection = DataSourceProvider.getDataSource().getConnection();
		
			PreparedStatement stmtMyImage = connection.prepareStatement("SELECT idImg FROM images WHERE cleeImg = ?");
			stmtMyImage.setString(1, cleeImg);
			ResultSet results = stmtMyImage.executeQuery();
			while(results.next()){
				actualImg = results.getInt(1);
			}
			
			Statement stmtLastClee = connection.createStatement();
			ResultSet results2 = stmtLastClee.executeQuery("SELECT * FROM images WHERE idImg = (SELECT MAX(idImg) FROM images)");
			while(results2.next()){
				lastImg = results2.getInt(1);
			}
			
			if(actualImg != lastImg){
				PreparedStatement stmtNextImg = connection.prepareStatement("SELECT cleeImg FROM images WHERE idImg = ?");
				stmtNextImg.setInt(1, (actualImg+1));
				ResultSet results3 = stmtNextImg.executeQuery();
				while(results3.next()){
					cleeNextImg = results3.getString(1);
				}
			}
			else{
				Statement stmtFirstImg = connection.createStatement();
				ResultSet results3 = stmtFirstImg.executeQuery("SELECT cleeImg FROM images WHERE idImg = 1");
				while(results3.next()){
					cleeNextImg = results3.getString(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cleeNextImg;
	}
}
