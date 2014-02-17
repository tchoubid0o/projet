package hei.devweb.metier;

import hei.devweb.dao.ImagesDao;
import hei.devweb.dao.impl.ImagesDaoImpl;
import hei.devweb.model.Images;

import java.util.List;

public class ImagesManager {

	private static ImagesManager instance;

	private ImagesDao imagesDao = new ImagesDaoImpl();

	private ImagesManager() {
	}

	public static ImagesManager getInstance() {
		if (instance == null) {
			instance = new ImagesManager();
		}
		return instance;
	}
	
	public List<Images> listerAndSortImages(String sortChoice, Integer idCategorie) {
		return imagesDao.listerAndSortImages(sortChoice, idCategorie);
	}
	
	public List<Images> listerImagesSearched(String searchText){
		return imagesDao.listerImagesSearched(searchText);
	}

	public List<Images> listerImages() {
		return imagesDao.listerImages();
	}
	
	public List<Images> listerMesImages(Integer idUser){
		return imagesDao.listerMesImages(idUser);
	}
	
	public Images getImage(String cleeImg) {
		if (cleeImg == null) {
			throw new IllegalArgumentException(
					"La clée de l'image doit être renseigné.");
		}
		return imagesDao.getImage(cleeImg);
	}
	
	public String ajouterImage(Images image, Integer idUser){
		return imagesDao.ajouterImage(image, idUser);
	}
	
	public String modifierImage(String cleePrevImg, Images image, Integer idUser){
		return imagesDao.modifierImage(cleePrevImg, image, idUser);
	}
	
	public String htmlSpecialChars(String stringToProtect){
		return imagesDao.htmlSpecialChars(stringToProtect);
	}
	
	public String generateCleeImg(){
		return imagesDao.generateCleeImg();
	}
	
	public void deleteImage(String cleeImg, Integer idUser){
		imagesDao.deleteImage(cleeImg, idUser);
	}
	
	public String getPrevCleeImg(String cleeImg){
		return imagesDao.getPrevCleeImg(cleeImg);
	}
	
	public String getNextCleeImg(String cleeImg){
		return imagesDao.getNextCleeImg(cleeImg);
	}
}
