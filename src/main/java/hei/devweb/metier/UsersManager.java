package hei.devweb.metier;

import hei.devweb.dao.UsersDao;
import hei.devweb.dao.impl.UsersDaoImpl;
import hei.devweb.model.Users;

import java.net.UnknownHostException;
import java.util.List;

public class UsersManager {

	private static UsersManager instance;

	private UsersDao usersDao = new UsersDaoImpl();

	private UsersManager() {
	}

	public static UsersManager getInstance() {
		if (instance == null) {
			instance = new UsersManager();
		}
		return instance;
	}

	public List<Users> listerUsers() {
		return usersDao.listerUsers();
	}
	
	public Integer countUsersMailUsed(String user_mail){
		return usersDao.countUsersMailUsed(user_mail);
	}
	
	public Integer countUsersPseudoUsed(String user_pseudo){
		return usersDao.countUsersPseudoUsed(user_pseudo);
	}
	
	public void ajouterUser(Users user) throws UnknownHostException{
		usersDao.ajouterUser(user);
	}
	
	public String HashMyPassword(String user_password) throws Exception{
		return usersDao.HashMyPassword(user_password);
	}
	
	public String GetPasswordConnexion(String user_pseudo){
		return usersDao.GetPasswordConnexion(user_pseudo);
	}
	
	public Integer getTheIdUser(String user_pseudo){
		return usersDao.getTheIdUser(user_pseudo);
	}
	
	public String getUserPseudo(Integer idUser){
		return usersDao.getUserPseudo(idUser);
	}
}
