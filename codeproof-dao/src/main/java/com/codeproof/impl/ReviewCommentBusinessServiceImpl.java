package com.codeproof.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeproof.common.model.dto.ReviewCommentDTO;
import com.codeproof.data.spec.ReviewCommentDataService;
import com.codeproof.model.ReviewComment;
import com.codeproof.spec.ReviewCommentBusinessService;

@Service("reviewCommentBusinessService")
public class ReviewCommentBusinessServiceImpl extends AbstractBusinessService<ReviewCommentDTO, ReviewComment>
		implements
			ReviewCommentBusinessService {

	@Autowired
	ReviewCommentDataService reviewCommentDataService;

	@Override
	public ReviewCommentDTO find(String id) {
		return dozerConverter.convertFrom(reviewCommentDataService.find(id), ReviewCommentDTO.class);
	}

	@Override
	public List<ReviewCommentDTO> getReviewComments(String reviewCodeId, String fileName) {
		return dozerConverter.convertFrom(reviewCommentDataService.getReviewComments(reviewCodeId, fileName),
				ReviewCommentDTO.class);
	}

	@Override
	public void save(ReviewCommentDTO reviewCommentDto) {
		reviewCommentDataService.save(dozerConverter.convertTo(reviewCommentDto, ReviewComment.class));
	}

	@Override
	public void update(ReviewCommentDTO reviewCommentDto) {
		reviewCommentDataService.update(dozerConverter.convertTo(reviewCommentDto, ReviewComment.class));
	}

	@Override
	public void remove(ReviewCommentDTO reviewCommentDto) {
		reviewCommentDataService.remove(dozerConverter.convertTo(reviewCommentDto, ReviewComment.class));
	}

}
