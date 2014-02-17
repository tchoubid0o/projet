package hei.devweb.metier;

import java.net.UnknownHostException;

import hei.devweb.dao.AjaxContactDao;
import hei.devweb.dao.impl.AjaxContactDaoImpl;

public class AjaxContactManager {

	private static AjaxContactManager instance;

	private AjaxContactDao ajaxContactDao = new AjaxContactDaoImpl();

	private AjaxContactManager() {
	}

	public static AjaxContactManager getInstance() {
		if (instance == null) {
			instance = new AjaxContactManager();
		}
		return instance;
	}
	
	public String addContactMessage(String email,String subject,String message) throws UnknownHostException{
		return ajaxContactDao.addContactMessage(email,subject,message);
	}
}
