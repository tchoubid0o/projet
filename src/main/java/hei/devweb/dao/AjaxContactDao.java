package hei.devweb.dao;

import java.net.UnknownHostException;

public interface AjaxContactDao {
	public String addContactMessage(String email,String subject,String message) throws UnknownHostException;
}
