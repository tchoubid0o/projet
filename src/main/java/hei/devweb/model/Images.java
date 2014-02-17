package hei.devweb.model;

import java.util.Date;

public class Images {
	private Integer idImg;
	private String cleeImg;
	private String extImg;
	private String titreImg;
	private String textImg;
	private Date dateImg;
	private Integer ptsImg;
	private Integer idUser;
	private Integer idCategorie;
	
	public Images(Integer idImg, String cleeImg, String extImg,
			String titreImg, String textImg, Date dateImg, Integer ptsImg,
			Integer idUser, Integer idCategorie) {
		super();
		this.idImg = idImg;
		this.cleeImg = cleeImg;
		this.extImg = extImg;
		this.titreImg = titreImg;
		this.textImg = textImg;
		this.dateImg = dateImg;
		this.ptsImg = ptsImg;
		this.idUser = idUser;
		this.idCategorie = idCategorie;
	}

	public Integer getIdImg() {
		return idImg;
	}

	public void setIdImg(Integer idImg) {
		this.idImg = idImg;
	}
	
	public String getCleeImg() {
		return cleeImg;
	}

	public void setCleeImg(String cleeImg) {
		this.cleeImg = cleeImg;
	}

	public String getExtImg() {
		return extImg;
	}

	public void setExtImg(String extImg) {
		this.extImg = extImg;
	}

	public String getTitreImg() {
		return titreImg;
	}

	public void setTitreImg(String titreImg) {
		this.titreImg = titreImg;
	}

	public String getTextImg() {
		return textImg;
	}

	public void setTextImg(String textImg) {
		this.textImg = textImg;
	}

	public Date getDateImg() {
		return dateImg;
	}

	public void setDateImg(Date dateImg) {
		this.dateImg = dateImg;
	}

	public Integer getPtsImg() {
		return ptsImg;
	}

	public void setPtsImg(Integer ptsImg) {
		this.ptsImg = ptsImg;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	
	public Integer getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(Integer idCategorie) {
		this.idCategorie = idCategorie;
	}
}
