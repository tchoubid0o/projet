package hei.devweb.dao.impl;

import hei.devweb.dao.CommentairesDao;
import hei.devweb.model.Commentaires;

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

public class CommentairesDaoTestCase {

private CommentairesDao commentairesDao = new CommentairesDaoImpl();
	
	@Before
	public void purgeBDD() throws Exception {
		Connection connection = DataSourceProvider.getDataSource()
				.getConnection();
		Statement stmt = connection.createStatement();
		
		String ip = InetAddress.getLocalHost().getHostAddress();
		
		stmt.executeUpdate("DELETE FROM images");
		stmt.executeUpdate("DELETE FROM users");
		stmt.executeUpdate("DELETE FROM categories");
		stmt.executeUpdate("DELETE FROM commentaires");
		stmt.executeUpdate("INSERT INTO `users`(`idUser`, `user_pseudo`, `user_mail`, `user_password`, `user_ip`, `user_registered`) VALUES (1,'pseudo','MonAdresseMail@hotmail.fr', '9697542531b409622caae066d292bd37', '" + ip + "', '2014-01-01')");
		stmt.executeUpdate("INSERT INTO `images`(`idImg`, `cleeImg`, `extImg`, `titreImg`, `textImg`, `dateImg`, `ptsImg`, `idUser`, `idCategorie`) VALUES (1,'11111','.jpg', 'titre image', 'mon texte recherche', '2014-01-01', 0, 1, 1)");
		stmt.executeUpdate("INSERT INTO `images`(`idImg`, `cleeImg`, `extImg`, `titreImg`, `textImg`, `dateImg`, `ptsImg`, `idUser`, `idCategorie`) VALUES (2,'22222','.jpg', 'titre image2', 'mon texte2', '2014-01-01', 0, 1, 2)");
		stmt.executeUpdate("INSERT INTO `commentaires`(`commentId`, `commentDate`, `commentContent`, `idUser`, `cleeImg`) VALUES(1, '2014-01-01', 'mon premier commentaire', 1, '11111')");
		stmt.close();
		connection.close();
	}
	
	@Test
	public void testListerCommentaires() {
		List<Commentaires> liste = commentairesDao.listerCommentaires("11111");
		Assert.assertEquals(1, liste.size());
		
		Assert.assertEquals(1, (int)liste.get(0).getCommentId());
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		Assert.assertEquals(cal.getTime(), liste.get(0).getCommentDate());
		Assert.assertEquals("mon premier commentaire", liste.get(0).getCommentContent());
		Assert.assertEquals(1, (int)liste.get(0).getIdUser());
		Assert.assertEquals("11111", liste.get(0).getCleeImg());
	}
	
	@Test
	public void testAjoutCommentaire() throws SQLException {	
		commentairesDao.ajoutCommentaire("2eme commentaire", 1, "22222");
		
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		
		ResultSet results = stmt.executeQuery("SELECT * FROM commentaires WHERE cleeImg = '22222'");
		Assert.assertTrue(results.next());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(new Date());
		
		Assert.assertEquals(date, dateFormat.format(results.getDate("commentDate")));
		Assert.assertEquals("2eme commentaire", results.getString("commentContent"));
		Assert.assertEquals(1, results.getInt("idUser"));
		Assert.assertEquals("22222", results.getString("cleeImg"));
	}
}
