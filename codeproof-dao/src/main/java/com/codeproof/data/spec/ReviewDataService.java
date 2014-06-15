package com.codeproof.data.spec;

import java.util.List;

import com.codeproof.model.Review;

public interface ReviewDataService {

	Review find(String id);

	void save(Review review);

	void update(Review review);

	List<Review> findByReviewRole(String reviewRoleType);

	List<Review> findReviewByReviewer(String userName);
}
