package com.codeproof.spec;

import java.util.List;

import com.codeproof.common.model.dto.ReviewDTO;

public interface ReviewBusinessService {
	
	ReviewDTO find(String id);

	void save(ReviewDTO reviewDTO);

	void update(ReviewDTO reviewDTO);
	
	List<ReviewDTO> findByReviewRole(String reviewRoleType);
	
	List<ReviewDTO> findReviewByReviewer(String userName);
	
}
