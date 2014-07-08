package com.codeproof.data.spec;

import java.util.List;

import com.codeproof.model.ReviewComment;

public interface ReviewCommentDataService {
	ReviewComment find(String id);
	List<ReviewComment> getReviewComments(String reviewCodeId, String fileName);
	void save(ReviewComment reviewComment);
	void update(ReviewComment reviewComment);
	void remove(ReviewComment reviewComment);
}
