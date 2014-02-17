package hei.devweb.dao.impl;

import hei.devweb.dao.UsersDao;
import hei.devweb.model.Users;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
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

public class UsersDaoTestCase {
	private UsersDao userDao = new UsersDaoImpl();

	@Before
	public void purgeBDD() throws Exception {
		Connection connection = DataSourceProvider.getDataSource()
				.getConnection();
		Statement stmt = connection.createStatement();
		
		String ip = InetAddress.getLocalHost().getHostAddress();
		
		stmt.executeUpdate("DELETE FROM users");
		stmt.executeUpdate("INSERT INTO `users`(`idUser`, `user_pseudo`, `user_mail`, `user_password`, `user_ip`, `user_registered`) VALUES (1,'pseudo','MonAdresseMail@hotmail.fr', '9697542531b409622caae066d292bd37', '" + ip + "', '2014-01-01')");
		stmt.executeUpdate("INSERT INTO `users`(`idUser`, `user_pseudo`, `user_mail`, `user_password`, `user_ip`, `user_registered`) VALUES (2,'pseudo2','MonAdresseMail2@hotmail.fr', '9697542531b409622caae066d292bd37', '" + ip + "', '2014-01-01')");
		stmt.close();
		connection.close();
	}
	
	@Test
	public void testListerUsers() throws UnknownHostException {
		List<Users> liste = userDao.listerUsers();
		Assert.assertEquals(2, liste.size());
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		Assert.assertEquals("pseudo", liste.get(0).getUser_pseudo());
		Assert.assertEquals("MonAdresseMail@hotmail.fr", liste.get(0).getUser_mail());
		Assert.assertEquals("9697542531b409622caae066d292bd37",liste.get(0).getUser_password());
		Assert.assertEquals(InetAddress.getLocalHost().getHostAddress(), liste.get(0).getUser_ip());
		Assert.assertEquals(cal.getTime(), liste.get(0).getUser_registered());
		
		Assert.assertEquals(2, liste.size());
		Assert.assertEquals("pseudo2", liste.get(1).getUser_pseudo());
		Assert.assertEquals("MonAdresseMail2@hotmail.fr", liste.get(1).getUser_mail());
		Assert.assertEquals("9697542531b409622caae066d292bd37",liste.get(1).getUser_password());
		Assert.assertEquals(InetAddress.getLocalHost().getHostAddress(), liste.get(1).getUser_ip());
		Assert.assertEquals(cal.getTime(), liste.get(1).getUser_registered());
	}
	
	@Test
	public void testCountUsersMailUsed() {
		//Le Mail MonAdresseMail@hotmail.fr est déjà présent dans la BDD ( la fonction retourne 1 )
		Integer countUsersMailUsed = userDao.countUsersMailUsed("MonAdresseMail@hotmail.fr");
		Assert.assertEquals(1, (int) countUsersMailUsed);
	}
	
	@Test
	public void testCountUsersPseudoUsed() {
		//Le pseudo "pseudo" est présent 1 fois dans la BDD
		Integer countUsersPseudoUsed = userDao.countUsersPseudoUsed("pseudo");
		Assert.assertEquals(1, (int) countUsersPseudoUsed);
	}
	
	@Test
	public void testAjouterUser() throws Exception {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		Users user = new Users(null, "pseudo3", "MonAdresseMail3@hotmail.fr", "MonPasswordCrypte", "ip3", cal.getTime());
		userDao.ajouterUser(user);

		Connection connection = DataSourceProvider.getDataSource()
				.getConnection();
		Statement stmt = connection.createStatement();
		
		String ip = InetAddress.getLocalHost().getHostAddress();
		
		ResultSet results = stmt.executeQuery("SELECT * FROM users WHERE user_pseudo ='pseudo3'");
		Assert.assertTrue(results.next());
		Assert.assertNotNull(results.getInt("idUser"));
		Assert.assertEquals("pseudo3", results.getString("user_pseudo"));
		Assert.assertEquals("MonAdresseMail3@hotmail.fr", results.getString("user_mail"));
		Assert.assertEquals("MonPasswordCrypte", results.getString("user_password"));
		Assert.assertEquals(ip, results.getString("user_ip"));
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(new Date());
		
		Assert.assertEquals(date, dateFormat.format(results.getDate("user_registered")));

		stmt.close();
		connection.close();
	}
	
	@Test
	public void testHashMyPassword() throws Exception {
		//Le pseudo "pseudo" est présent 1 fois dans la BDD
		String hash = userDao.HashMyPassword("password");
		Assert.assertEquals("9697542531b409622caae066d292bd37", hash);
	}
	
	@Test
	public void testGetPasswordConnexion(){
		String passwordUser1 = userDao.GetPasswordConnexion("pseudo");
		Assert.assertEquals("9697542531b409622caae066d292bd37", passwordUser1);
	}
	
	@Test
	public void testGetTheIdUser(){
		//L'utilisateur pseudo a l'id numéro 1 dans la BDD.
		Integer idUser1 = userDao.getTheIdUser("pseudo");
		Assert.assertEquals(1, (int) idUser1);
	}
	
	@Test
	public void testGetUserPseudo(){
		//L'utilisateur numéro 1 correspond à pseudo dans la BDD.
		String pseudoUser1 = userDao.getUserPseudo(1);
		Assert.assertEquals("pseudo", pseudoUser1);
	}
	/*
	@Test
	public void testMarquerTacheCommeFinie() throws Exception {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		tacheDao.marquerTacheCommeFinie(1);

		Connection connection = DataSourceProvider.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		ResultSet results = stmt.executeQuery("SELECT * FROM tache WHERE id = 1");
		Assert.assertTrue(results.next());
		Assert.assertEquals(true, results.getBoolean("finie"));
		stmt.close();
		connection.close();
	}
	*/
}
