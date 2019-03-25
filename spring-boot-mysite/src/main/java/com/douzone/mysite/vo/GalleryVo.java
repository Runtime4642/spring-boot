package com.douzone.mysite.vo;

public class GalleryVo {
	long no;
	String comment;
	String imageURL;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", comment=" + comment + ", imageURL=" + imageURL + "]";
	}

}
