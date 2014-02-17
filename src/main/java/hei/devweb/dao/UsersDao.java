package hei.devweb.dao;

import hei.devweb.model.Users;

import java.net.UnknownHostException;
import java.util.List;

public interface UsersDao {

	public List<Users> listerUsers();
	
	public Integer countUsersMailUsed(String user_mail);
	
	public Integer countUsersPseudoUsed(String user_pseudo);

	public void ajouterUser(Users user) throws UnknownHostException;
	
	public String HashMyPassword(String user_password) throws Exception;
	
	public String GetPasswordConnexion(String user_pseudo);

	public Integer getTheIdUser(String user_pseudo);

	public String getUserPseudo(Integer idUser);
}
