package com.codeproof.spec;

import java.util.List;

import com.codeproof.common.model.dto.ReviewCommentDTO;

public interface ReviewCommentBusinessService {
	ReviewCommentDTO find(String id);
	List<ReviewCommentDTO> getReviewComments(String reviewCodeId, String fileName);
	void save(ReviewCommentDTO reviewCommentDto);
	void update(ReviewCommentDTO reviewCommentDto);
	void remove(ReviewCommentDTO reviewCommentDto);
}
