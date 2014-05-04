package com.codeproof.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeproof.data.spec.ReviewDataService;
import com.codeproof.model.Review;
import com.codeproof.spec.ReviewBusinessService;

@Service("reviewBusinessService")
public class ReviewBusinessServiceImpl extends AbstractBusinessService implements ReviewBusinessService {

	@Autowired
	ReviewDataService reviewDataService;
	
	@Override
	public Review find(String id) {
		return reviewDataService.find(id);
	}

	@Override
	public void save(Review review) {
		reviewDataService.save(review);
	}

	@Override
	public void update(Review review) {
		reviewDataService.update(review);
	}

	@Override
	public List<Review> findByReviewRole(String reviewRoleType) {
		return reviewDataService.findByReviewRole(reviewRoleType);
	}

}
