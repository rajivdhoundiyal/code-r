package com.codeproof.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeproof.data.spec.ReviewDataService;
import com.codeproof.model.Review;
import com.codeproof.model.dto.ReviewDTO;
import com.codeproof.spec.ReviewBusinessService;

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
		reviewDataService.save(dozerConverter.convertTo(reviewDTO, Review.class));
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
	public List<ReviewDTO> findByReviewer(final String userName) {
		return dozerConverter.convertFrom(reviewDataService.findByReviewer(userName), ReviewDTO.class);
	}

}
