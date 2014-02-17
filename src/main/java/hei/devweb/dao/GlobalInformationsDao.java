package hei.devweb.dao;

import java.util.ArrayList;

public interface GlobalInformationsDao {

	public Integer countImagesDataBase();
	
	public Integer countVotesDataBase();
	
	public Integer countUsersDataBase();

	public ArrayList<String> returnAllCategorie();
}
