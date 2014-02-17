package hei.devweb.dao.impl;

import hei.devweb.dao.VotesDao;
import hei.devweb.model.Users;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VotesDaoTestCase {

	private VotesDao votesDao = new VotesDaoImpl();

	@Before
	public void purgeBDD() throws Exception {
		Connection connection = DataSourceProvider.getDataSource()
				.getConnection();
		Statement stmt = connection.createStatement();
		
		String ip = InetAddress.getLocalHost().getHostAddress();
		
		stmt.executeUpdate("DELETE FROM historiques");
		stmt.executeUpdate("DELETE FROM images");
		stmt.executeUpdate("DELETE FROM users");
		stmt.executeUpdate("INSERT INTO `users`(`idUser`, `user_pseudo`, `user_mail`, `user_password`, `user_ip`, `user_registered`) VALUES (1,'pseudo','MonAdresseMail@hotmail.fr', '9697542531b409622caae066d292bd37', '" + ip + "', '2014-01-01')");
		stmt.executeUpdate("INSERT INTO `images`(`idImg`, `cleeImg`, `extImg`, `titreImg`, `textImg`, `dateImg`, `ptsImg`, `idUser`, `idCategorie`) VALUES (1,'11111','.jpg', 'titre image', 'mon texte', '2014-01-01', 0, 1, 1)");
		stmt.close();
		connection.close();
	}
	
	@Test
	public void testGetPtsImage() {
		Integer ptsImg = votesDao.getPtsImage("11111");
		Assert.assertEquals(0, (int) ptsImg);
	}
	
	@Test
	public void testGetUserVoted() throws UnknownHostException {
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		Users user1 = votesDao.getUserVoted("pseudo");
		Assert.assertEquals(1, (int) user1.getIdUser());
		Assert.assertEquals("pseudo", user1.getUser_pseudo());
		Assert.assertEquals("MonAdresseMail@hotmail.fr", user1.getUser_mail());
		Assert.assertEquals("9697542531b409622caae066d292bd37",user1.getUser_password());
		Assert.assertEquals(InetAddress.getLocalHost().getHostAddress(), user1.getUser_ip());
		Assert.assertEquals(cal.getTime(), user1.getUser_registered());
	}
	
	@Test
	public void testCheckAlreadyVoted() {
		//L'utilisateur 1 n'a pas voté pour l'image 11111, on doit donc avoir un résultat false
		boolean vote = votesDao.checkAlreadyVoted(1, "11111");
		Assert.assertFalse(vote);
	}
	
	@Test
	public void testSetMyVote(){
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		Users user2 = new Users(null, "pseudo2", "MonAdresseMail2@hotmail.fr", "MonPasswordCrypte", "ip2", cal.getTime());
		
		String setVote = votesDao.setMyVote(user2, "11111", 1, "pseudo");
		
		//La fonction retourne un message de succés si le vote a été pris en compte
		Assert.assertEquals("<div class='erreur_message' style='color: green !important;'>Le vote a bien été pris en compte.</div>", setVote);
	}
}
