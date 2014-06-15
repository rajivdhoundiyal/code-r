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

}
