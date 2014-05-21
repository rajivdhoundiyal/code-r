package com.codeproof.mvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codeproof.data.spec.ReviewDataService;
import com.codeproof.model.Review;

@Controller
@RequestMapping(value="/dashboard")
public class DashboardController {
	
	@Autowired
	ReviewDataService reviewDataService;
	
	@RequestMapping(value="/get/reviews/{username}")
	public @ResponseBody List<Review> getReviewByUser(@PathVariable("username") String userName) {
		
		Review review = new Review();
		review.setReviewId("00101");
		review.setReviewCode("Aooo10");
		review.setReviewDescription("My First Review");
		
		
		Review review2 = new Review();
		review2.setReviewId("0010");
		review2.setReviewCode("Aooo1");
		review2.setReviewDescription("My IInd Review");
		
		Review review3 = new Review();
		review3.setReviewId("0010");
		review3.setReviewCode("Aooo1");
		review3.setReviewDescription("My IIInd Review");
		
		Review review4 = new Review();
		review4.setReviewId("0010");
		review4.setReviewCode("Aooo1");
		review4.setReviewDescription("My IVnd Review");
		
		List<Review> reviews = new ArrayList<Review>();
		reviews.add(review);
		reviews.add(review2);
		reviews.add(review3);
		reviews.add(review4);
		
		/*return reviewDataService.findByReviewer("User3");*/
		return reviews;
	}

}
