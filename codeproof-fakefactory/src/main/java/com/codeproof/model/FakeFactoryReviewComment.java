package com.codeproof.model;

public class FakeFactoryReviewComment {
	
	public static ReviewComment createReviewComment() {
		
		ReviewComment reviewComment = new ReviewComment();
		reviewComment.setCommentStatus(ReviewComment.CommentStatus.OPEN.getCommentStatus());
		reviewComment.setCommentType(ReviewComment.CommentType.ERROR.getCommentType());
		reviewComment.setLineNumber(10L);
		reviewComment.setReviewComment("Test comment...");
		return reviewComment;
		
	}

	public static ReviewComment createReviewComment(ReviewComment.CommentStatus commentStatus, ReviewComment.CommentType commentType, Long lineNumber, String comment) {
		
		ReviewComment reviewComment = new ReviewComment();
		reviewComment.setCommentStatus(commentStatus.OPEN.getCommentStatus());
		reviewComment.setCommentType(commentType.ERROR.getCommentType());
		reviewComment.setLineNumber(lineNumber);
		reviewComment.setReviewComment(comment);
		
		return reviewComment;
		
	}
}
