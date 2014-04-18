package com.codeproof.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Review {
	
	public static final String REVIEWERS = "reviewers";
	
	@Id
	private String reviewId;
	private Map<String, String> reviewers;
	private String reviewCode;
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

}
