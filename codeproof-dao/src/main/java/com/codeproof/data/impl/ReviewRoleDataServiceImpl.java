package com.codeproof.data.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.codeproof.data.spec.ReviewRoleDataService;
import com.codeproof.model.ReviewRole;

@Repository("reviewRoleDataService")
public class ReviewRoleDataServiceImpl extends AbstractDataService<ReviewRole> implements ReviewRoleDataService {

	@Override
	protected Class<ReviewRole> getReferenceClass() {
		return ReviewRole.class;
	}

	@Override
	public ReviewRole findByRoleType(String reviewRoleType) {
		Query filterQuery = new Query(Criteria.where(ReviewRole.REVIEW_ROLE_LEVEL).regex(reviewRoleType));
		return mongoTemplate.findOne(filterQuery, getReferenceClass());
	}

}
