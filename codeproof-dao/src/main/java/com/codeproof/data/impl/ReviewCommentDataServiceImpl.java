package com.codeproof.data.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.codeproof.data.spec.ReviewCommentDataService;
import com.codeproof.model.ReviewComment;

@Repository("reviewCommentDataService")
public class ReviewCommentDataServiceImpl extends AbstractDataService<ReviewComment> implements ReviewCommentDataService {

	@Override
	public List<ReviewComment> getReviewComments(String reviewCodeId, String fileName) {
		Query query = Query.query(Criteria.where(ReviewComment.PROP_REVIEW_CODE_ID).regex(reviewCodeId)
				.and(ReviewComment.PROP_FILE_NAME).regex(fileName));
		return mongoTemplate.find(query, ReviewComment.class);
	}

	@Override
	protected Class<ReviewComment> getReferenceClass() {
		return ReviewComment.class;
	}

}
