package com.codeproof.data.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.codeproof.data.spec.ReviewRoleDataService;
import com.codeproof.model.ReviewRoleType;

@Repository("reviewRoleDataService")
public class ReviewRoleDataServiceImpl extends AbstractDataService<ReviewRoleType> implements ReviewRoleDataService {

	@Override
	protected Class<ReviewRoleType> getReferenceClass() {
		return ReviewRoleType.class;
	}

	@Override
	public ReviewRoleType findByRoleType(String reviewRoleType) {
		Query filterQuery = new Query(Criteria.where(ReviewRoleType.REVIEW_ROLE_LEVEL).regex(reviewRoleType));
		return mongoTemplate.findOne(filterQuery, getReferenceClass());
	}

}
