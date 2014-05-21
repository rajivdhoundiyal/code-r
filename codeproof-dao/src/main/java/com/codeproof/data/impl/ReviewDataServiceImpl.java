package com.codeproof.data.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.codeproof.data.spec.ReviewDataService;
import com.codeproof.data.spec.ReviewRoleDataService;
import com.codeproof.data.spec.UserDataService;
import com.codeproof.model.Review;
import com.codeproof.model.ReviewRole;
import com.codeproof.model.User;

@Repository("reviewDataService")
public class ReviewDataServiceImpl extends AbstractDataService<Review> implements ReviewDataService {
	
	@Autowired
	ReviewRoleDataService reviewRoleDataService;

	@Autowired
	UserDataService userDataService;
	
	@Override
	protected Class<Review> getReferenceClass() {
		return Review.class;
	}

	@Override
	public List<Review> findByReviewRole(String reviewRoleType) {
		ReviewRole reviewRole = reviewRoleDataService.findByRoleType(reviewRoleType);
		Query filterQuery = new Query(Criteria.where(Review.REVIEWERS).regex(reviewRole.getReviewRoleId()));
		return mongoTemplate.find(filterQuery, getReferenceClass());
	}

	@Override
	public List<Review> findByReviewer(String userName) {
		Query filterQuery = new Query(Criteria.where(Review.REVIEWER).regex(userName));
		return mongoTemplate.find(filterQuery, getReferenceClass());
	}

}
