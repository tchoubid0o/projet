package hei.devweb.dao;

import hei.devweb.model.Images;

import java.util.List;

public interface ImagesDao {

	public List<Images> listerImages();
	
	public List<Images> listerAndSortImages(String sortChoice, Integer idCategorie);
	
	public List<Images> listerMesImages(Integer idUser);
	
	public List<Images> listerImagesSearched(String searchText);
	
	public Images getImage(String cleeImg);

	public String ajouterImage(Images image, Integer idUser);
	
	public String modifierImage(String cleePrevImg, Images image, Integer idUser);

	public String htmlSpecialChars(String stringToProtect);
	
	public String generateCleeImg();

	public void deleteImage(String cleeImg, Integer idUser);

	public String getPrevCleeImg(String cleeImg);

	public String getNextCleeImg(String cleeImg);
	
	//public void marquerTacheCommeFinie(Integer idTache);
	
	//public void ajouterTache(News tache);
}
