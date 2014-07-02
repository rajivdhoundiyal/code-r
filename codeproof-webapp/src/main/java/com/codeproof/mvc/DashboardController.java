package com.codeproof.mvc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codeproof.common.model.dto.FileDetailsDTO;
import com.codeproof.common.model.dto.ReviewDTO;
import com.codeproof.model.Review;
import com.codeproof.spec.FileBusinessService;
import com.codeproof.spec.ReviewBusinessService;

@Controller
@RequestMapping(value="/dashboard")
public class DashboardController {
	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
	@Autowired
	ReviewBusinessService reviewBusinessService;
	
	@Autowired
	FileBusinessService fileBusinessService;
	
	@RequestMapping(value="/reviews/{username}")
	public @ResponseBody List<ReviewDTO> getReviewByUser(@PathVariable("username") String userName) {
		List<ReviewDTO> reviews = reviewBusinessService.findReviewByReviewer("rajiv");
		Review review = new Review();
		/*review.setReviewId("00101");
		review.setReviewCode("Aooo10");
		review.setReviewName("My First Review");
		review.setReviewDescription("My First Review for testing review creation");
		review.setReviewStatus(ReviewStatus.COMPLETED.getStatus());
		List<ReviewRole> reviewers = new ArrayList<ReviewRole>();
		review.setReviewers(reviewers);
		review.setReviewers(reviewers);
		
		
		Review review2 = new Review();
		review2.setReviewId("0010");
		review2.setReviewCode("Aooo1");
		review2.setReviewName("My IInd Review");
		review2.setReviewDescription("My IInd Review for enabling review creation");
		List<ReviewRole> reviewers2 = new ArrayList<ReviewRole>();
		review.setReviewers(reviewers2);
		review2.setReviewStatus(ReviewStatus.IN_PROGRESS.getStatus());
		
		Review review3 = new Review();
		review3.setReviewId("0010");
		review3.setReviewCode("Aooo1");
		review3.setReviewName("My IIInd Review");
		review3.setReviewDescription("My IIInd Review for testing review");
		List<ReviewRole> reviewers3 = new ArrayList<ReviewRole>();
		ReviewRole revRole3 = new ReviewRole();
		User user = new User();
		user.setFirstName("First-3");
		user.setLastName("Last-3");
		ReviewRoleType roleType3 = new ReviewRoleType();
		roleType3.setReviewRoleType("Reviewer-3");
		revRole3.setReviewer(user);
		revRole3.setReviewRoleType(roleType3);
		reviewers3.add(revRole3);
		review.setReviewers(reviewers3);
		review3.setReviewStatus(ReviewStatus.NOT_PICKED.getStatus());
		
		Review review4 = new Review();
		review4.setReviewId("0010");
		review4.setReviewCode("Aooo1");
		review4.setReviewName("My IVnd Review");
		review4.setReviewDescription("My IVnd Review for functionality abc");
		List<ReviewRole> reviewers4 = new ArrayList<ReviewRole>();
		ReviewRole revRole4 = new ReviewRole();
		User user4 = new User();
		user4.setFirstName("First-4");
		user4.setLastName("Last-4");
		ReviewRoleType roleType4 = new ReviewRoleType();
		roleType4.setReviewRoleType("Reviewer-4");
		revRole4.setReviewer(user4);
		revRole4.setReviewRoleType(roleType4);
		reviewers4.add(revRole4);
		reviewers4.add(revRole3);
		review.setReviewers(reviewers4);
		review4.setReviewStatus(ReviewStatus.IN_PROGRESS.getStatus());*/
		
		/*List<Review> reviews = new ArrayList<Review>();
		reviews.add(review);*/
		/*reviews.add(review2);
		reviews.add(review3);
		reviews.add(review4);*/
		
		/*return reviewBusinessService.findByReviewer("User3");*/
		return reviews;
	}
	
	@RequestMapping(value = "/reviews/{username}/{reviewcode}")
	public @ResponseBody
	List<ReviewDTO> getFileDetailsByReviewCode(@PathVariable("username") String userName,
			@PathVariable("reviewcode") String reviewCode) {
		logger.info("Inside getReviewByUser ("+userName +" , " + reviewCode + ")");
		List<ReviewDTO> fileDetails = fileBusinessService.getFileDetailsByReviewCode(reviewCode);
		return fileDetails;
	}

}

