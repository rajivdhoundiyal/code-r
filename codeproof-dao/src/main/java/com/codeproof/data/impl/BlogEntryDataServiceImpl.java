package com.codeproof.data.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.codeproof.data.impl.AbstractDataService;
import com.codeproof.data.spec.BlogEntryDataService;
import com.codeproof.model.BlogEntry;

@Repository("blogEntryDataService")
public class BlogEntryDataServiceImpl extends AbstractDataService<BlogEntry> implements BlogEntryDataService {

	@Override
	public List<BlogEntry> getBlogEntriesByReviewCode(String reviewCode) {
		Query query = Query.query(Criteria.where(BlogEntry.PROP_REVIEW_CODE_ID).regex(reviewCode).andOperator(Criteria.where(BlogEntry.PROP_FILE_NAME).gte(null)));
		return mongoTemplate.find(query, getReferenceClass());
	}

	@Override
	public List<BlogEntry> getBlogEntriesByReviewCodeAndFileName(String reviewCode, String fileName) {
		Query query = Query.query(Criteria.where(BlogEntry.PROP_REVIEW_CODE_ID).regex(reviewCode)
				.andOperator(Criteria.where(BlogEntry.PROP_FILE_NAME).regex(fileName)));
		return mongoTemplate.find(query, getReferenceClass());
	}

	@Override
	protected Class<BlogEntry> getReferenceClass() {
		return BlogEntry.class;
	}

}
