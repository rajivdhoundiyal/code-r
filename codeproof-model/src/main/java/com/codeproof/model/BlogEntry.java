package com.codeproof.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BlogEntry {
	
	public static final String PROP_BLOG_TYPE = "blogType";
	public static final String PROP_USER_NAME = "userName";
	public static final String PROP_REVIEW_CODE_ID = "reviewCodeId";
	public static final String PROP_FILE_NAME = "fileName";
	
	@Id
	private String blogId;
	private String reviewCodeId;
	private String userName;
	private String blogDescription;
	private Date dateOfWriting;
	private String blogType;
	private String fileName;
	
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
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
