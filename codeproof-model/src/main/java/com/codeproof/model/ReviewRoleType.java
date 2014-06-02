package com.codeproof.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
//@Entity
//@Table(name="review_role_type")
public class ReviewRoleType {

	public static final String REVIEW_ROLE_LEVEL = "reviewRoleType";
	
	@Id
	//@javax.persistence.Id
	//@GeneratedValue(strategy=GenerationType.SEQUENCE)
	//@Column(name="review_role_id")
	private String reviewRoleId;
	
	//@Column(name="review_description")
	private String reviewDescription;
	
	//@Column(name="review_role_type")
	private String reviewRoleType;
	
	public String getReviewRoleId() {
		return reviewRoleId;
	}
	public void setReviewRoleId(String reviewRoleId) {
		this.reviewRoleId = reviewRoleId;
	}
	public String getReviewDescription() {
		return reviewDescription;
	}
	public void setReviewDescription(String reviewDesc) {
		this.reviewDescription = reviewDesc;
	}
	public String getReviewRoleType() {
		return reviewRoleType;
	}
	public void setReviewRoleType(String reviewRoleType) {
		this.reviewRoleType = reviewRoleType;
	}

}
