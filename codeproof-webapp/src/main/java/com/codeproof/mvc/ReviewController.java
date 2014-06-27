package com.codeproof.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codeproof.common.model.dto.FileDetailsDTO;
import com.codeproof.common.model.dto.ReviewDTO;
import com.codeproof.common.model.dto.ReviewRoleDTO;
import com.codeproof.common.model.dto.ReviewRoleTypeDTO;
import com.codeproof.model.ReviewRole;
import com.codeproof.model.ReviewRoleType;
import com.codeproof.spec.ReviewBusinessService;

@Controller
@RequestMapping(value = "/review")
public class ReviewController {
	@Autowired
	ReviewBusinessService reviewBusinessService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getReview(@PathVariable String id) {
		System.out.println("Inside Get Review : " + id);
		return "index";
	}

	@RequestMapping(value = "/filter/{username}", method = RequestMethod.GET)
	public String getReviewByUserName(@PathVariable String username) {
		System.out.println("Inside Get Review : " + username);
		return "index";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateReview(@PathVariable String id) {
		System.out.println("Inside Upadate Review : " + id);
		return "index";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteReview(@PathVariable String id) {
		System.out.println("Inside Delete Review : " + id);
		return "index";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createReview(@RequestBody ReviewDTO review) {
		System.out.println("Inside Create Review : " + review);
		if (review.getReviewers() == null) {
			ReviewRoleDTO revRole = new ReviewRoleDTO();
			revRole.setReviewerName("rajiv");
			ReviewRoleTypeDTO revRoleType = new ReviewRoleTypeDTO();
			revRoleType.setReviewRoleType("Reviewee");
			revRole.setReviewRoleType(revRoleType);
			List<ReviewRoleDTO> reviewRoles = new ArrayList<ReviewRoleDTO>();
			reviewRoles.add(revRole);

			review.setReviewers(reviewRoles);
		}
		Set<FileDetailsDTO> files = review.getFiles();

		reviewBusinessService.save(review);
		return "index";
	}
}
