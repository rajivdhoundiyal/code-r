package com.codeproof.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Review {
	
	public static final String REVIEWERS = "reviewers";
	public static final String REVIEWER = "reviewer";
	
	@Id
	private String reviewId;
	private Map<String, String> reviewers;
	private List<String> reviewer;
	private String reviewCode;
	private String reviewDescription;
	private Map<String, File> files;
	
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
	public Map<String, File> getFiles() {
		return files;
	}
	public void setFiles(Map<String, File> files) {
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
}
