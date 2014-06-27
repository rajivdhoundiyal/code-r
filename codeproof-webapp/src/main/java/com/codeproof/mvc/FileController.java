package com.codeproof.mvc;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codeproof.common.model.dto.FileDetailsDTO;
import com.codeproof.common.model.dto.ReviewDTO;
import com.codeproof.spec.FileBusinessService;
import com.codeproof.spec.ReviewBusinessService;

@Controller
@RequestMapping(value = "/file")
public class FileController {

	@Autowired
	private ReviewBusinessService reviewBusinessService;
	
	@Autowired
	private FileBusinessService fileBusinessService;

	@RequestMapping(value = "/details", method = RequestMethod.POST)
	public String saveFileDetails(@RequestBody Set<FileDetailsDTO> fileDetails) {
		System.out.println("Inside Create Review : " + fileDetails);
		// reviewBusinessService.save(review);
		// fileBusinessService.save(file);
		return "index";
	}

	@RequestMapping(value = "/{username}/{reviewcode}", method = RequestMethod.POST)
	public List<ReviewDTO> getFileContents(@PathVariable("username") String userName,
			@PathVariable("reviewcode") String reviewCode, @RequestBody FileDetailsDTO filePath) {
		System.out.println("Inside file content Username : " + userName + " Review Code : " + reviewCode
				+ " File Path : " + filePath.getFullPath());
		return fileBusinessService.getFileContentByReviewCodeAndFileName(reviewCode, filePath.getFullPath());
	}
}
