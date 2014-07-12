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

import com.codeproof.common.model.dto.BlogEntryDTO;
import com.codeproof.common.model.dto.BlogEntryDTO;
import com.codeproof.spec.BlogEntryBusinessService;
import com.codeproof.utilities.HttpStatusCode;

@Controller
@RequestMapping(value = "/blog")
public class BlogEntryController {

	@Autowired
	BlogEntryBusinessService blogEntryBusinessService;


	@RequestMapping(value="/review/{reviewcodeid}", method = RequestMethod.GET)
	public @ResponseBody
	List<BlogEntryDTO> getBlogEntriesByReviewCode(@PathVariable("reviewcodeid") String reviewCodeId) {
		System.out.println(reviewCodeId);
		return blogEntryBusinessService.getBlogEntriesByReviewCode(reviewCodeId);
	}
	
	@RequestMapping(value="/file", method = RequestMethod.GET)
	public @ResponseBody
	List<BlogEntryDTO> getBlogEntriesByReviewCodeAndFileName(@RequestParam("reviewcodeid") String reviewCodeId,
			@RequestParam("filename") String fileName) {
		System.out.println(reviewCodeId + " : " + fileName);
		return blogEntryBusinessService.getBlogEntriesByReviewCodeAndFileName(reviewCodeId, fileName);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	BlogEntryDTO getBlogEntry(@RequestParam("id") String id) {
		return blogEntryBusinessService.find(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	String save(@RequestBody BlogEntryDTO blogEntryDto) {
		blogEntryBusinessService.save(blogEntryDto);
		return HttpStatusCode.OK;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody
	String update(@RequestBody BlogEntryDTO blogEntryDto) {
		blogEntryBusinessService.update(blogEntryDto);
		return HttpStatusCode.OK;
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody
	String delete(@RequestBody BlogEntryDTO blogEntryDto) {
		// blogEntryBusinessService.remove(blogEntryDto);
		return HttpStatusCode.OK;
	}
}
