package hei.devweb.dao.impl;

import hei.devweb.dao.AjaxNewsletterDao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AjaxNewsletterDaoTestCase {

private AjaxNewsletterDao ajaxNewsletterDao = new AjaxNewsletterDaoImpl();
	
	@Before
	public void purgeBDD() throws Exception {
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		
		stmt.executeUpdate("DELETE FROM newsletter");
		stmt.executeUpdate("INSERT INTO `newsletter`(`idNewsletter`, `emailNewsletter`, `dateNewsletterInsc`) VALUES (1,'MonAdresseMail@hotmail.fr', '2014-01-01')");
		stmt.close();
		connection.close();
	}
	
	@Test
	public void testAddMailInNewsletter() throws SQLException {
		String inscriptionNewsletter = ajaxNewsletterDao.addMailInNewsletter("MonAdresseMail2@hotmail.fr");
		Connection connection = DataSourceProvider.getDataSource()
				.getConnection();
		Statement stmt = connection.createStatement();
		
		ResultSet results = stmt.executeQuery("SELECT * FROM newsletter WHERE emailNewsletter = 'MonAdresseMail2@hotmail.fr'");
		Assert.assertTrue(results.next());
		Assert.assertNotNull(results);
		Assert.assertEquals("Vous vous &ecirc;tes correctement inscrit à la newsletter.", inscriptionNewsletter);
	}

}
