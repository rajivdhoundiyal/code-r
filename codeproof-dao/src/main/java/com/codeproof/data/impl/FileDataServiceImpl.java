package com.codeproof.data.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation.GroupOperationBuilder;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Field;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.codeproof.data.spec.FileDataService;
import com.codeproof.model.File;
import com.codeproof.model.FileDetails;
import com.codeproof.model.Review;
import com.codeproof.util.StringConstants;

@Repository("fileDataService")
public class FileDataServiceImpl extends AbstractDataService<File> implements
		FileDataService {

	Logger logger = LoggerFactory.getLogger(FileDataServiceImpl.class);

	@Override
	protected Class<File> getReferenceClass() {
		return File.class;
	}

	@Override
	public List<Review> getFileDetailsByReviewCode(String reviewCode) {
		logger.debug("Inside Get File Details (Review Code) : " + reviewCode);
		System.out.println(reviewCode);
		Query query = Query.query(Criteria.where(Review.PROP_REVIEW_CODE)
				.regex(reviewCode));
		query.fields().exclude(Review.PROP_REVIEW_STATUS);
		query.fields().exclude(Review.PROP_REVIEW_DESCRIPTION);
		query.fields().exclude(
				Review.PROP_FILES + StringConstants.DOT
						+ FileDetails.FILE_CONTENTS);
		return mongoTemplate.find(query, Review.class);
	}

	@Override
	public List<Review> getFileContentByReviewCodeAndFileName(
			String reviewCode, String fileName) {
		logger.debug("Inside Get File Content (Review Code) : " + reviewCode
				+ " (File Path) : " + fileName);
		/*Query query = Query.query(Criteria
				.where(Review.PROP_REVIEW_CODE)
				.regex(reviewCode)
				.andOperator(
						Criteria.where(
								Review.PROP_FILES + StringConstants.DOT
										+ FileDetails.FILE_NAME).regex(
								fileName)));*/
		AggregationOperation unwind = Aggregation.unwind(Review.PROP_FILES);
		AggregationOperation matchReviewCode = Aggregation.match(Criteria.where(Review.PROP_REVIEW_CODE).regex(reviewCode));
		AggregationOperation matchFileName = Aggregation.match(Criteria.where(Review.PROP_FILES + StringConstants.DOT
				+ FileDetails.FILE_NAME).regex(fileName));
		GroupOperation group = Aggregation.group("reviewId", Review.PROP_FILES).push(Review.PROP_FILES).as(Review.PROP_FILES);
		
		Aggregation aggregation = Aggregation.newAggregation(unwind, matchFileName, matchReviewCode, group);
		
		return mongoTemplate.aggregate(aggregation, Review.class, Review.class).getMappedResults();
		/*Query query = Query.query(Criteria
				.where(Review.PROP_REVIEW_CODE)
				.regex(reviewCode)
				.and(Review.PROP_FILES + StringConstants.DOT
										+ FileDetails.FILE_NAME).regex(
								fileName));*/
		//return mongoTemplate.find(query, Review.class);
	}
}
