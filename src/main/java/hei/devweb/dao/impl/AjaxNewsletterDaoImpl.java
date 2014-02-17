package hei.devweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hei.devweb.dao.AjaxNewsletterDao;


public class AjaxNewsletterDaoImpl implements AjaxNewsletterDao {
	
	public AjaxNewsletterDaoImpl() {
	}
	
	public String addMailInNewsletter(String email){
		String returnResult;
		
		try {
			//On va commencer par vérifier que le mail n'est pas déjà présent dans la BDD
			Connection connection = DataSourceProvider.getDataSource().getConnection();

			PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM newsletter WHERE emailNewsletter = ?");
			stmt.setString(1, email);
			
			
			ResultSet results = stmt.executeQuery();

			if(results.next()){
				if(results.getInt(1) > 0){
					returnResult = "<div style='color: red;text-align: center; margin-top: 15px;'>Vous êtes déjà inscrit à la newsletter.</div>";
					return returnResult;
				}
				else{
					try {
						PreparedStatement stmt2 = connection.prepareStatement("INSERT INTO `newsletter`(`emailNewsletter`, dateNewsletterInsc) VALUES(?, NOW())");
						stmt2.setString(1, email);
						stmt2.executeUpdate();
						
						returnResult = "Vous vous &ecirc;tes correctement inscrit à la newsletter.";
						
						stmt2.close();
						return returnResult;
					} catch (SQLException e) {
							e.printStackTrace();
					}
				}
			}
			connection.close();
			stmt.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return "erreur";
	}
}
