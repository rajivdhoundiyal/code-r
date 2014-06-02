package com.codeproof.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.codeproof.util.StringConstants;

@Document
public class ReviewRole {

	public static final String REVIEW_ROLE_TYPE = Review.REVIEWERS + StringConstants.DOT + "reviewRoleType" + StringConstants.DOT + "reviewRoleType";
	public static final String REVIEW_ROLE_NAME = Review.REVIEWERS + StringConstants.DOT + "reviewerName";
	
	private String reviewerName;
	private ReviewRoleType reviewRoleType;

	public String getReviewerName() {
		return reviewerName;
	}
	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public ReviewRoleType getReviewRoleType() {
		return reviewRoleType;
	}
	public void setReviewRoleType(ReviewRoleType reviewRoleType) {
		this.reviewRoleType = reviewRoleType;
	}

}
