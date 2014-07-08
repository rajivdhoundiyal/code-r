package com.codeproof.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codeproof.common.model.dto.ReviewCommentDTO;
import com.codeproof.spec.ReviewCommentBusinessService;
import com.codeproof.utilities.HttpStatusCode;

@Controller
@RequestMapping("/comment")
public class ReviewCommentController {

	@Autowired
	ReviewCommentBusinessService reviewCommentService;
	
	@RequestMapping(method= RequestMethod.GET)
	public @ResponseBody List<ReviewCommentDTO> getReviewComments(@RequestParam("reviewcodeid") String reviewCodeId,
			@RequestParam("filename") String fileName) {
		System.out.println(reviewCodeId + " : " + fileName);
		return reviewCommentService.getReviewComments(reviewCodeId, fileName);
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public @ResponseBody ReviewCommentDTO getReviewComment(@RequestParam("id") String id) {
		return reviewCommentService.find(id);
	}
	
	@RequestMapping(method= RequestMethod.POST)
	public @ResponseBody String save(@RequestBody ReviewCommentDTO reviewCommentDto) {
		reviewCommentService.save(reviewCommentDto);
		return HttpStatusCode.OK;
	}
	
	@RequestMapping(method= RequestMethod.PUT)
	public @ResponseBody String update(@RequestBody ReviewCommentDTO reviewCommentDto) {
		reviewCommentService.update(reviewCommentDto);
		return HttpStatusCode.OK;
	}
	
	@RequestMapping(method= RequestMethod.DELETE)
	public @ResponseBody String delete(@RequestBody ReviewCommentDTO reviewCommentDto) {
		reviewCommentService.remove(reviewCommentDto);
		return HttpStatusCode.OK;
	}
}
