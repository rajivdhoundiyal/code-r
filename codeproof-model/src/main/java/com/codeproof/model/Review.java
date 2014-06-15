package com.codeproof.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
//@Entity
//@Table(name="review")
public class Review {
	
	public static final String REVIEWERS = "reviewers";
	public static final String REVIEWER = "reviewer";
	public static final String REVIEWEE = "reviewee";
	
	public static final String PROP_FILES = "files";
	public static final String PROP_REVIEW_CODE = "reviewCode";
	public static final String PROP_REVIEW_NAME = "reviewName";
	public static final String PROP_REVIEW_DESCRIPTION = "reviewDescription";
	public static final String PROP_REVIEW_STATUS = "reviewStatus";
	
	public enum ReviewStatus {
		COMPLETED("Completed"),
		IN_PROGRESS("In-Progress"),
		NOT_PICKED("Not Picked");
		
		private String status;
		
		private ReviewStatus(String status) {
			this.status = status;
		}
		
		public String getStatus() {
			return status;
		}
		
		@Override
		public String toString() {
			return status;
		}
	}
	
	@Id
	//@javax.persistence.Id
	//@Column(name="review_id")
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private String reviewId;
	
	//@ManyToMany(targetEntity=ReviewRoleType.class, cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	//@JoinColumn(name = "review_id", referencedColumnName="review_role_id")
	private List<ReviewRole> reviewers;
	
	//@Column(name="review_code")
	private String reviewCode;
	
	//@Column(name="review_name")
	private String reviewName;
	
	//@Column(name="review_description")
	private String reviewDescription;
	
	private List<FileDetails> files;
	
	//@Column(name="review_status")
	private String reviewStatus;
	
	public String getReviewId() {
		return reviewId;
	}
	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}
	public List<ReviewRole> getReviewers() {
		return reviewers;
	}
	public void setReviewers(List<ReviewRole> reviewers) {
		this.reviewers = reviewers;
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
	public List<FileDetails> getFiles() {
		return files;
	}
	public void setFiles(List<FileDetails> files) {
		this.files = files;
	}
}
