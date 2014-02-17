package hei.devweb.metier;

import hei.devweb.dao.VotesDao;
import hei.devweb.dao.impl.VotesDaoImpl;
import hei.devweb.model.Users;

public class VotesManager {
	
	private static VotesManager instance;

	private VotesDao votesDao = new VotesDaoImpl();

	private VotesManager() {
	}

	public static VotesManager getInstance() {
		if (instance == null) {
			instance = new VotesManager();
		}
		return instance;
	}
	
	public Integer getPtsImage(String cleeImg){
		return votesDao.getPtsImage(cleeImg);
	}
	
	public Users getUserVoted(String user_pseudo){
		return votesDao.getUserVoted(user_pseudo);
	}
	
	public boolean checkAlreadyVoted(Integer idUser, String cleeImg){
		return votesDao.checkAlreadyVoted(idUser, cleeImg);
	}
	
	public String setMyVote(Users user, String cleeImg, Integer ptsVote, String user_pseudo){
		return votesDao.setMyVote(user, cleeImg, ptsVote, user_pseudo);
	}
}
