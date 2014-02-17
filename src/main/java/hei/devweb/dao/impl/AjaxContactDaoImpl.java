package hei.devweb.dao.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import hei.devweb.dao.AjaxContactDao;


public class AjaxContactDaoImpl implements AjaxContactDao {
	
	public AjaxContactDaoImpl() {
	}
	
	public String addContactMessage(String email,String subject,String message) throws UnknownHostException{
		
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();

			String ip_adress = InetAddress.getLocalHost().getHostAddress();
			
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO `contact`(`email`, `sujet`, `message`, `dateContact`, `ipContact`) VALUES(?,?,?,NOW(),?)");
			stmt.setString(1, email);
			stmt.setString(2, subject);
			stmt.setString(3, message);
			stmt.setString(4, ip_adress);
			
			stmt.executeUpdate();
			connection.close();
			stmt.close();
			return "Votre formulaire de contact a bien &eacute;t&eacute; transmis.";
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return "erreur";
	}
}
