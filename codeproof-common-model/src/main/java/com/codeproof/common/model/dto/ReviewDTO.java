package com.codeproof.common.model.dto;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReviewDTO {
	
	private String reviewId;
	private List<ReviewRoleDTO> reviewers;
	private String reviewee;
	private String reviewCode;
	private String reviewName;
	private String reviewDescription;
	private Set<FileDetailsDTO> files;
	private String reviewStatus;
	
	public String getReviewId() {
		return reviewId;
	}
	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}
	public String getReviewCode() {
		return reviewCode;
	}
	public void setReviewCode(String reviewCode) {
		this.reviewCode = reviewCode;
	}
	public String getReviewDescription() {
		return reviewDescription;
	}
	public void setReviewDescription(String reviewDescription) {
		this.reviewDescription = reviewDescription;
	}
	public String getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	public String getReviewName() {
		return reviewName;
	}
	public void setReviewName(String reviewName) {
		this.reviewName = reviewName;
	}
	public Set<FileDetailsDTO> getFiles() {
		return files;
	}
	public void setFiles(Set<FileDetailsDTO> files) {
		this.files = files;
	}
	public String getReviewee() {
		return reviewee;
	}
	public void setReviewee(String reviewee) {
		this.reviewee = reviewee;
	}
	public List<ReviewRoleDTO> getReviewers() {
		return reviewers;
	}
	public void setReviewers(List<ReviewRoleDTO> reviewers) {
		this.reviewers = reviewers;
	}
}
