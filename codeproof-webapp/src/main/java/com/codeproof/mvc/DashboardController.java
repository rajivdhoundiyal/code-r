package com.codeproof.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codeproof.model.Review;
import com.codeproof.model.Review.ReviewStatus;
import com.codeproof.spec.ReviewBusinessService;

@Controller
@RequestMapping(value="/dashboard")
public class DashboardController {
	
	@Autowired
	ReviewBusinessService reviewBusinessService;
	
	@RequestMapping(value="/reviews/{username}")
	public @ResponseBody List<Review> getReviewByUser(@PathVariable("username") String userName) {
		
		Review review = new Review();
		review.setReviewId("00101");
		review.setReviewCode("Aooo10");
		review.setReviewName("My First Review");
		review.setReviewDescription("My First Review for testing review creation");
		review.setReviewStatus(ReviewStatus.COMPLETED.getStatus());
		Map<String, String> reviewers = new HashMap<String, String>();
		reviewers.put("Rajiv", "Reviewer");
		review.setReviewers(reviewers);
		review.setReviewers(reviewers);
		
		
		Review review2 = new Review();
		review2.setReviewId("0010");
		review2.setReviewCode("Aooo1");
		review2.setReviewName("My IInd Review");
		review2.setReviewDescription("My IInd Review for enabling review creation");
		Map<String, String> reviewers2 = new HashMap<String, String>();
		reviewers2.put("Rajiv", "Reviewer");
		review.setReviewers(reviewers2);
		review2.setReviewStatus(ReviewStatus.IN_PROGRESS.getStatus());
		
		Review review3 = new Review();
		review3.setReviewId("0010");
		review3.setReviewCode("Aooo1");
		review3.setReviewName("My IIInd Review");
		review3.setReviewDescription("My IIInd Review for testing review");
		Map<String, String> reviewers3 = new HashMap<String, String>();
		reviewers3.put("Rajiv", "Reviewer");
		review.setReviewers(reviewers3);
		review3.setReviewStatus(ReviewStatus.NOT_PICKED.getStatus());
		
		Review review4 = new Review();
		review4.setReviewId("0010");
		review4.setReviewCode("Aooo1");
		review4.setReviewName("My IVnd Review");
		review4.setReviewDescription("My IVnd Review for functionality abc");
		Map<String, String> reviewers4 = new HashMap<String, String>();
		reviewers4.put("Rajiv", "Reviewer");
		review.setReviewers(reviewers4);
		review4.setReviewStatus(ReviewStatus.IN_PROGRESS.getStatus());
		
		List<Review> reviews = new ArrayList<Review>();
		reviews.add(review);
		reviews.add(review2);
		reviews.add(review3);
		reviews.add(review4);
		
		/*return reviewBusinessService.findByReviewer("User3");*/
		return reviews;
	}

}
