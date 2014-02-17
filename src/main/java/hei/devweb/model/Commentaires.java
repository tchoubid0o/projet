package hei.devweb.model;

import java.util.Date;

public class Commentaires {
	private Integer commentId;
	private Date commentDate;
	private String commentContent;
	private Integer idUser;
	private String cleeImg;
	
	public Commentaires(Integer commentId, Date commentDate,
			String commentContent, Integer idUser, String cleeImg) {
		super();
		this.commentId = commentId;
		this.commentDate = commentDate;
		this.commentContent = commentContent;
		this.idUser = idUser;
		this.cleeImg = cleeImg;
	}
	
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getCleeImg() {
		return cleeImg;
	}
	public void setCleeImg(String cleeImg) {
		this.cleeImg = cleeImg;
	}
	
	
}
