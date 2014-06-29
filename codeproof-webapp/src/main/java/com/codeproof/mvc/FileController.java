package com.codeproof.mvc;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codeproof.common.model.dto.FileContentDTO;
import com.codeproof.common.model.dto.FileDetailsDTO;
import com.codeproof.common.model.dto.ReviewDTO;
import com.codeproof.model.FileContent;
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
	public @ResponseBody FileContentDTO getFileContents(@PathVariable("username") String userName,
			@PathVariable("reviewcode") String reviewCode, @RequestBody FileDetailsDTO name) {
		List<ReviewDTO> review = fileBusinessService.getFileContentByReviewCodeAndFileName(reviewCode, name.getName());
		ReviewDTO reviewDto = fileBusinessService.getFileContentByReviewCodeAndFileName(reviewCode, name.getName()).get(0);
		FileDetailsDTO fileDetailsDTO = reviewDto.getFiles().iterator().next();
		FileContentDTO fileContent = fileDetailsDTO.getFileContents().iterator().next();
		fileContent.setContentValue(new String(fileContent.getContent()));
		fileContent.setContent(null);
		return fileContent;
	}
}
