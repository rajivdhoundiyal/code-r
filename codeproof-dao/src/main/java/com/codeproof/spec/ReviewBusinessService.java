package com.codeproof.spec;

import java.util.List;

import com.codeproof.model.Review;

public interface ReviewBusinessService {
	
	Review find(String id);

	void save(Review review);

	void update(Review review);
	
	List<Review> findByReviewRole(String reviewRoleType);
	
	List<Review> findByReviewer(String userName);
	
}
