package com.codeproof.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeproof.common.model.dto.ReviewDTO;
import com.codeproof.data.spec.ReviewDataService;
import com.codeproof.model.Review;
import com.codeproof.model.Review.ReviewStatus;
import com.codeproof.spec.ReviewBusinessService;
import com.codeproof.util.UniqueIdGenerator;

@Service("reviewBusinessService")
public class ReviewBusinessServiceImpl extends AbstractBusinessService<ReviewDTO, Review> implements ReviewBusinessService {

	@Autowired
	ReviewDataService reviewDataService;
	
	@Override
	public ReviewDTO find(final String id) {
		return dozerConverter.convertFrom(reviewDataService.find(id), ReviewDTO.class);
	}

	@Override
	public void save(final ReviewDTO reviewDTO) {
		Review review = dozerConverter.convertTo(reviewDTO, Review.class);
		String reviewCode = UniqueIdGenerator.randomStringOfLength(6);
		review.setReviewCode(reviewCode);
		review.setReviewStatus(ReviewStatus.NOT_PICKED.getStatus());
		reviewDataService.save(review);
	}

	@Override
	public void update(final ReviewDTO reviewDTO) {
		reviewDataService.update(dozerConverter.convertTo(reviewDTO, Review.class));
	}

	@Override
	public List<ReviewDTO> findByReviewRole(final String reviewRoleType) {
		return dozerConverter.convertFrom(reviewDataService.findByReviewRole(reviewRoleType), ReviewDTO.class);
	}

	@Override
	public List<ReviewDTO> findReviewByReviewer(final String userName) {
		List<Review> review = reviewDataService.findReviewByReviewer(userName);
		return dozerConverter.convertFrom(review, ReviewDTO.class);
	}
	
}
