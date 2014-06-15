package com.codeproof.common.model.dto;


public class ReviewRoleDTO {

	private String reviewerName;
	private ReviewRoleTypeDTO reviewRoleType;

	public String getReviewerName() {
		return reviewerName;
	}
	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public ReviewRoleTypeDTO getReviewRoleType() {
		return reviewRoleType;
	}
	public void setReviewRoleType(ReviewRoleTypeDTO reviewRoleType) {
		this.reviewRoleType = reviewRoleType;
	}

}
