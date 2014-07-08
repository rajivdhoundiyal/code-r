package com.codeproof.common.model.dto;


public class ReviewCommentDTO {

	public enum CommentType {
		ERROR("Error"), COMMENT("Comment");

		private String commentType;

		private CommentType(String commentType) {
			this.commentType = commentType;
		}

		public String getCommentType() {
			return commentType;
		}
	}
	
	public enum ErrorType {
		TEXT("Text"), LOGICAL("Logical"), DESIGN("Design"), TESTING("Testing");

		private String errorType;

		private ErrorType(String errorType) {
			this.errorType = errorType;
		}

		public String getErrorType() {
			return errorType;
		}
	}

	public enum CommentStatus {
		FIXED("Fixed"), OPEN("Open");

		private String commentStatus;

		private CommentStatus(String commentStatus) {
			this.commentStatus = commentStatus;
		}

		public String getCommentStatus() {
			return commentStatus;
		}
	}

	private String reviewCommentId;
	private String commentType;
	private String errorType;
	private String reviewCodeId;
	private String fileName;
	private String reviewComment;
	private String commentStatus;
	private Long lineNumber;

	public String getReviewCommentId() {
		return reviewCommentId;
	}
	public void setReviewCommentId(String reviewCommentId) {
		this.reviewCommentId = reviewCommentId;
	}
	public String getCommentType() {
		return commentType;
	}
	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}
	public String getReviewComment() {
		return reviewComment;
	}
	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}
	public String getCommentStatus() {
		return commentStatus;
	}
	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}
	public Long getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(Long lineNumber) {
		this.lineNumber = lineNumber;
	}
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
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
