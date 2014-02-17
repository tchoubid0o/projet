package hei.devweb.model;

public class Votes {
	private Integer idUser;
	private Integer idImg;
	private Integer ptsVote;
	
	public Votes(Integer idUser, Integer idImg, Integer ptsVote) {
		super();
		this.idUser = idUser;
		this.idImg = idImg;
		this.ptsVote = ptsVote;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdImg() {
		return idImg;
	}

	public void setIdImg(Integer idImg) {
		this.idImg = idImg;
	}

	public Integer getPtsVote() {
		return ptsVote;
	}

	public void setPtsVote(Integer ptsVote) {
		this.ptsVote = ptsVote;
	}
	
}
