package hei.devweb.dao;

import hei.devweb.model.Users;

public interface VotesDao {
	public Integer getPtsImage(String cleeImg);
	
	public Users getUserVoted(String user_pseudo);

	public boolean checkAlreadyVoted(Integer idUser, String cleeImg);

	public String setMyVote(Users user, String cleeImg, Integer ptsVote, String user_pseudo);
}
