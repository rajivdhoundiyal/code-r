package com.codeproof.model.dto;

import java.util.List;
import java.util.Map;

public class ReviewDTO {
	
	private String reviewId;
	private Map<String, String> reviewers;
	private List<String> reviewer;
	private String reviewCode;
	private String reviewName;
	private String reviewDescription;
	private Map<String, FileDTO> files;
	private String reviewStatus;
	
	public String getReviewId() {
		return reviewId;
	}
	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}
	public Map<String, String> getReviewers() {
		return reviewers;
	}
	public void setReviewers(Map<String, String> reviewers) {
		this.reviewers = reviewers;
	}
	public String getReviewCode() {
		return reviewCode;
	}
	public void setReviewCode(String reviewCode) {
		this.reviewCode = reviewCode;
	}
	public Map<String, FileDTO> getFiles() {
		return files;
	}
	public void setFiles(Map<String, FileDTO> files) {
		this.files = files;
	}
	public String getReviewDescription() {
		return reviewDescription;
	}
	public void setReviewDescription(String reviewDescription) {
		this.reviewDescription = reviewDescription;
	}
	public List<String> getReviewer() {
		return reviewer;
	}
	public void setReviewer(List<String> reviewer) {
		this.reviewer = reviewer;
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
}
