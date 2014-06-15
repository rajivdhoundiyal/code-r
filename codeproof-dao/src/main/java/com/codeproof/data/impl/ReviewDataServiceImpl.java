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
import com.codeproof.model.ReviewRoleType;

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
		ReviewRoleType reviewRole = reviewRoleDataService.findByRoleType(reviewRoleType);
		Query filterQuery = Query.query(Criteria.where(ReviewRole.REVIEW_ROLE_TYPE).regex(
				reviewRole.getReviewRoleType()));
		return mongoTemplate.find(filterQuery, getReferenceClass());
	}

	@Override
	public List<Review> findReviewByReviewer(String userName) {
		Query filterQuery = Query.query(Criteria.where(ReviewRole.REVIEW_ROLE_NAME).regex(userName));
		filterQuery.fields().exclude(Review.PROP_FILES);
		return mongoTemplate.find(filterQuery, getReferenceClass());
	}
}
