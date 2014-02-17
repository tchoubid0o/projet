package hei.devweb.model;

import java.util.Date;

public class Users {
	private Integer idUser;
	private String user_pseudo;
	private String user_mail;
	private String user_password;
	private String user_ip;
	private Date user_registered;
	
	public Users(Integer idUser, String user_pseudo, String user_mail,
			String user_password, String user_ip, Date user_registered) {
		super();
		this.idUser = idUser;
		this.user_pseudo = user_pseudo;
		this.user_mail = user_mail;
		this.user_password = user_password;
		this.user_ip = user_ip;
		this.user_registered = user_registered;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getUser_pseudo() {
		return user_pseudo;
	}

	public void setUser_pseudo(String user_pseudo) {
		this.user_pseudo = user_pseudo;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_ip() {
		return user_ip;
	}

	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}

	public Date getUser_registered() {
		return user_registered;
	}

	public void setUser_registered(Date user_registered) {
		this.user_registered = user_registered;
	}
}
