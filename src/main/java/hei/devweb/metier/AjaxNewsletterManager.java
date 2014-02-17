package hei.devweb.metier;

import hei.devweb.dao.AjaxNewsletterDao;
import hei.devweb.dao.impl.AjaxNewsletterDaoImpl;

public class AjaxNewsletterManager {

	private static AjaxNewsletterManager instance;

	private AjaxNewsletterDao ajaxNewsletterDao = new AjaxNewsletterDaoImpl();

	private AjaxNewsletterManager() {
	}

	public static AjaxNewsletterManager getInstance() {
		if (instance == null) {
			instance = new AjaxNewsletterManager();
		}
		return instance;
	}
	
	public String addMailInNewsletter(String email){
		return ajaxNewsletterDao.addMailInNewsletter(email);
	}
}
