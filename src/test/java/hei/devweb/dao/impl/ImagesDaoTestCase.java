package hei.devweb.dao.impl;

import hei.devweb.dao.ImagesDao;
import hei.devweb.model.Images;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ImagesDaoTestCase {

	private ImagesDao imagesDao = new ImagesDaoImpl();
	
	@Before
	public void purgeBDD() throws Exception {
		Connection connection = DataSourceProvider.getDataSource()
				.getConnection();
		Statement stmt = connection.createStatement();
		
		String ip = InetAddress.getLocalHost().getHostAddress();
		
		stmt.executeUpdate("DELETE FROM images");
		stmt.executeUpdate("DELETE FROM users");
		stmt.executeUpdate("INSERT INTO `users`(`idUser`, `user_pseudo`, `user_mail`, `user_password`, `user_ip`, `user_registered`) VALUES (1,'pseudo','MonAdresseMail@hotmail.fr', '9697542531b409622caae066d292bd37', '" + ip + "', '2014-01-01')");
		stmt.executeUpdate("INSERT INTO `images`(`idImg`, `cleeImg`, `extImg`, `titreImg`, `textImg`, `dateImg`, `ptsImg`, `idUser`, `idCategorie`) VALUES (1,'11111','.jpg', 'titre image', 'mon texte recherche', '2014-01-01', 0, 1, 1)");
		stmt.executeUpdate("INSERT INTO `images`(`idImg`, `cleeImg`, `extImg`, `titreImg`, `textImg`, `dateImg`, `ptsImg`, `idUser`, `idCategorie`) VALUES (2,'22222','.jpg', 'titre image2', 'mon texte2', '2014-01-01', 0, 1, 2)");
		stmt.close();
		connection.close();
	}
	
	@Test
	public void testListerImages(){
		List<Images> liste = imagesDao.listerImages();
		Assert.assertEquals(2, liste.size());
		
		Assert.assertEquals(1, (int)liste.get(0).getIdImg());
		Assert.assertEquals("11111", liste.get(0).getCleeImg());
		Assert.assertEquals(".jpg", liste.get(0).getExtImg());
		Assert.assertEquals("titre image", liste.get(0).getTitreImg());
		Assert.assertEquals("mon texte recherche", liste.get(0).getTextImg());
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		Assert.assertEquals(cal.getTime(), liste.get(0).getDateImg());
		Assert.assertEquals(0, (int)liste.get(0).getPtsImg());
		Assert.assertEquals(1, (int)liste.get(0).getIdUser());
		Assert.assertEquals(1, (int)liste.get(0).getIdCategorie());
		
		Assert.assertEquals(2, (int)liste.get(1).getIdImg());
		Assert.assertEquals("22222", liste.get(1).getCleeImg());
		Assert.assertEquals(".jpg", liste.get(1).getExtImg());
		Assert.assertEquals("titre image2", liste.get(1).getTitreImg());
		Assert.assertEquals("mon texte2", liste.get(1).getTextImg());
		Assert.assertEquals(cal.getTime(), liste.get(1).getDateImg());
		Assert.assertEquals(0, (int)liste.get(1).getPtsImg());
		Assert.assertEquals(1, (int)liste.get(1).getIdUser());
		Assert.assertEquals(2, (int)liste.get(1).getIdCategorie());
	}

	@Test
	public void testListerAndSortImages(){
		//On sélectionne toutes les images avec la catégorie 1 (et par popularité)
		List<Images> liste = imagesDao.listerAndSortImages("popularity", 1);
		Assert.assertEquals(1, liste.size());
		
		Assert.assertEquals(1, (int)liste.get(0).getIdImg());
		Assert.assertEquals("11111", liste.get(0).getCleeImg());
		Assert.assertEquals(".jpg", liste.get(0).getExtImg());
		Assert.assertEquals("titre image", liste.get(0).getTitreImg());
		Assert.assertEquals("mon texte recherche", liste.get(0).getTextImg());
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		Assert.assertEquals(cal.getTime(), liste.get(0).getDateImg());
		Assert.assertEquals(0, (int)liste.get(0).getPtsImg());
		Assert.assertEquals(1, (int)liste.get(0).getIdUser());
		Assert.assertEquals(1, (int)liste.get(0).getIdCategorie());
	}
	
	@Test
	public void testGetImage(){
		Images image = imagesDao.getImage("11111");
		
		Assert.assertEquals(1, (int)image.getIdImg());
		Assert.assertEquals("11111", image.getCleeImg());
		Assert.assertEquals(".jpg", image.getExtImg());
		Assert.assertEquals("titre image", image.getTitreImg());
		Assert.assertEquals("mon texte recherche", image.getTextImg());
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		Assert.assertEquals(cal.getTime(), image.getDateImg());
		Assert.assertEquals(0, (int)image.getPtsImg());
		Assert.assertEquals(1, (int)image.getIdUser());
		Assert.assertEquals(1, (int)image.getIdCategorie());
	}
	
	@Test
	public void testAjouterImage() throws SQLException{
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		Images image = new Images(null, "33333", ".jpg", "titre3", "texte3", cal.getTime(), 0, 1, 2);
		imagesDao.ajouterImage(image, 1);
		
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		
		ResultSet results = stmt.executeQuery("SELECT * FROM images WHERE cleeImg = '33333' ");
		Assert.assertTrue(results.next());

		Assert.assertEquals("33333", results.getString("cleeImg"));
		Assert.assertEquals(".jpg", results.getString("extImg"));
		Assert.assertEquals("titre3", results.getString("titreImg"));
		Assert.assertEquals("texte3", results.getString("textImg"));
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(new Date());
		
		Assert.assertEquals(date, dateFormat.format(results.getDate("dateImg")));
		
		Assert.assertEquals(0, results.getInt("ptsImg"));
		Assert.assertEquals(1, results.getInt("idUser"));
		Assert.assertEquals(2, results.getInt("idCategorie"));
	}
	
	@Test
	public void testModifierImage() throws SQLException{
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		Images imageAfter = new Images(1, "11111", ".jpg", "titreModifié", "texteModifié", cal.getTime(), 0, 1, 3);

		imagesDao.modifierImage("11111", imageAfter, 1);
		
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		
		ResultSet results = stmt.executeQuery("SELECT * FROM images WHERE cleeImg = '11111' ");
		Assert.assertTrue(results.next());
		
		Assert.assertEquals(1, results.getInt("idImg"));
		Assert.assertEquals("11111", results.getString("cleeImg"));
		Assert.assertEquals(".jpg", results.getString("extImg"));
		Assert.assertEquals("titreModifié", results.getString("titreImg"));
		Assert.assertEquals("texteModifié", results.getString("textImg"));
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Assert.assertEquals("2014-01-01", dateFormat.format(results.getDate("dateImg")));
		
		Assert.assertEquals(0, results.getInt("ptsImg"));
		Assert.assertEquals(1, results.getInt("idUser"));
		Assert.assertEquals(3, results.getInt("idCategorie"));
	}
	
	@Test
	public void testListerMesImages(){
		List<Images> liste = imagesDao.listerMesImages(1);
		Assert.assertEquals(2, liste.size());
		
		Assert.assertEquals(1, (int)liste.get(0).getIdImg());
		Assert.assertEquals("11111", liste.get(0).getCleeImg());
		Assert.assertEquals(".jpg", liste.get(0).getExtImg());
		Assert.assertEquals("titre image", liste.get(0).getTitreImg());
		Assert.assertEquals("mon texte recherche", liste.get(0).getTextImg());
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		Assert.assertEquals(cal.getTime(), liste.get(0).getDateImg());
		Assert.assertEquals(0, (int)liste.get(0).getPtsImg());
		Assert.assertEquals(1, (int)liste.get(0).getIdUser());
		Assert.assertEquals(1, (int)liste.get(0).getIdCategorie());
		
		Assert.assertEquals(2, (int)liste.get(1).getIdImg());
		Assert.assertEquals("22222", liste.get(1).getCleeImg());
		Assert.assertEquals(".jpg", liste.get(1).getExtImg());
		Assert.assertEquals("titre image2", liste.get(1).getTitreImg());
		Assert.assertEquals("mon texte2", liste.get(1).getTextImg());
		Assert.assertEquals(cal.getTime(), liste.get(1).getDateImg());
		Assert.assertEquals(0, (int)liste.get(1).getPtsImg());
		Assert.assertEquals(1, (int)liste.get(1).getIdUser());
		Assert.assertEquals(2, (int)liste.get(1).getIdCategorie());
	}
	
	@Test
	public void testHtmlSpecialChars() {
		String specialChars = imagesDao.htmlSpecialChars("< > & \" \'");
		Assert.assertEquals("&lt; &gt; &amp; &quot; &apos;", specialChars);
	}
	
	@Test
	public void testGenerateCleeImg() {
		String cleeImg = imagesDao.generateCleeImg();
		Assert.assertNotEquals("erreur", cleeImg);
		Assert.assertNotNull(cleeImg);
	}
	
	@Test
	public void testDeleteImage() throws SQLException {
		imagesDao.deleteImage("11111", 1);

		Connection connection = DataSourceProvider.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		
		ResultSet results = stmt.executeQuery("SELECT * FROM images WHERE cleeImg = '11111' ");
		Assert.assertFalse(results.next());
	}
	
	@Test
	public void testListerImagesSearched(){
		List<Images> liste = imagesDao.listerImagesSearched("recherche");
		Assert.assertEquals(1, liste.size());
		
		Assert.assertEquals(1, (int)liste.get(0).getIdImg());
		Assert.assertEquals("11111", liste.get(0).getCleeImg());
		Assert.assertEquals(".jpg", liste.get(0).getExtImg());
		Assert.assertEquals("titre image", liste.get(0).getTitreImg());
		Assert.assertEquals("mon texte recherche", liste.get(0).getTextImg());
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		Assert.assertEquals(cal.getTime(), liste.get(0).getDateImg());
		Assert.assertEquals(0, (int)liste.get(0).getPtsImg());
		Assert.assertEquals(1, (int)liste.get(0).getIdUser());
		Assert.assertEquals(1, (int)liste.get(0).getIdCategorie());
	}
	
	@Test
	public void testGetPrevCleeImg() {
		String prevCleeImg = imagesDao.getPrevCleeImg("22222");
		Assert.assertEquals("11111", prevCleeImg);
	}
	
	@Test
	public void testGetNextCleeImg() {
		String prevCleeImg = imagesDao.getPrevCleeImg("11111");
		Assert.assertEquals("22222", prevCleeImg);
	}	
}
