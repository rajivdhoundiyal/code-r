package com.codeproof.common.model.dto;

import java.util.Date;

public class BlogEntryDTO {
	
	private String reviewCodeId;
	private String userName;
	private String blogDescription;
	private Date dateOfWriting;
	private String blogType;
	private String fileName;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBlogDescription() {
		return blogDescription;
	}
	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}
	public Date getDateOfWriting() {
		return dateOfWriting;
	}
	public void setDateOfWriting(Date dateOfWriting) {
		this.dateOfWriting = dateOfWriting;
	}
	public String getBlogType() {
		return blogType;
	}
	public void setBlogType(String blogType) {
		this.blogType = blogType;
	}
	public String getReviewCodeId() {
		return reviewCodeId;
	}
	public void setReviewCodeId(String reviewCodeId) {
		this.reviewCodeId = reviewCodeId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
