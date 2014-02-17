package hei.devweb.dao.impl;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import hei.devweb.dao.GlobalInformationsDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GlobalInformationsDaoTestCase {

	private GlobalInformationsDao globalInformationsDao = new GlobalInformationsDaoImpl();
	
	@Before
	public void purgeBDD() throws Exception {
		Connection connection = DataSourceProvider.getDataSource()
				.getConnection();
		Statement stmt = connection.createStatement();
		
		String ip = InetAddress.getLocalHost().getHostAddress();
		
		stmt.executeUpdate("DELETE FROM images");
		stmt.executeUpdate("DELETE FROM users");
		stmt.executeUpdate("DELETE FROM historiques");
		stmt.executeUpdate("DELETE FROM categories");
		stmt.executeUpdate("INSERT INTO `users`(`idUser`, `user_pseudo`, `user_mail`, `user_password`, `user_ip`, `user_registered`) VALUES (1,'pseudo','MonAdresseMail@hotmail.fr', '9697542531b409622caae066d292bd37', '" + ip + "', '2014-01-01')");
		stmt.executeUpdate("INSERT INTO `images`(`idImg`, `cleeImg`, `extImg`, `titreImg`, `textImg`, `dateImg`, `ptsImg`, `idUser`, `idCategorie`) VALUES (1,'11111','.jpg', 'titre image', 'mon texte recherche', '2014-01-01', 0, 1, 1)");
		stmt.executeUpdate("INSERT INTO `images`(`idImg`, `cleeImg`, `extImg`, `titreImg`, `textImg`, `dateImg`, `ptsImg`, `idUser`, `idCategorie`) VALUES (2,'22222','.jpg', 'titre image2', 'mon texte2', '2014-01-01', 0, 1, 2)");
		stmt.executeUpdate("INSERT INTO `categories`(`idCategorie`, `libelleCategorie`) VALUES (1, 'maCategorieTest')");
		stmt.close();
		connection.close();
	}
	
	@Test
	public void testCountImagesDataBase() {
		Integer nbImg = globalInformationsDao.countImagesDataBase();
		Assert.assertEquals(2, (int)nbImg);
	}
	
	@Test
	public void testCountUsersDataBase() {
		Integer nbUsers = globalInformationsDao.countUsersDataBase();
		Assert.assertEquals(1, (int)nbUsers);
	}
	
	@Test
	public void testCountVotesDataBase() {
		Integer nbVotes = globalInformationsDao.countVotesDataBase();
		Assert.assertEquals(0, (int)nbVotes);
	}
	
	@Test
	public void testReturnAllCategorie() {
		ArrayList<String> categories = globalInformationsDao.returnAllCategorie();
		Assert.assertEquals(1, categories.size());
		Assert.assertEquals("maCategorieTest", categories.get(0));
	}
}
