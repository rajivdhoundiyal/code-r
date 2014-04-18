package com.codeproof.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ReviewRole {

	public static final String REVIEW_ROLE_LEVEL = "reviewRoleLevel";
	
	@Id
	private String reviewRoleId;
	private String reviewDesc;
	private String reviewRoleType;
	
	public String getReviewRoleId() {
		return reviewRoleId;
	}
	public void setReviewRoleId(String reviewRoleId) {
		this.reviewRoleId = reviewRoleId;
	}
	public String getReviewDesc() {
		return reviewDesc;
	}
	public void setReviewDesc(String reviewDesc) {
		this.reviewDesc = reviewDesc;
	}
	public String getReviewRoleType() {
		return reviewRoleType;
	}
	public void setReviewRoleType(String reviewRoleType) {
		this.reviewRoleType = reviewRoleType;
	}

}
