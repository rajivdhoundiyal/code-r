package com.codeproof.mvc;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codeproof.model.dto.FileDetailsDTO;
import com.codeproof.spec.FileBusinessService;
import com.codeproof.spec.ReviewBusinessService;

@Controller
@RequestMapping(value="/file")
public class FileController {
	
	@Autowired
	private ReviewBusinessService reviewBusinessService;
	
	@Autowired
	FileBusinessService fileBusinessService;
	
	@RequestMapping(value = "/details", method=RequestMethod.POST)
    public String saveFileDetails(@RequestBody Set<FileDetailsDTO> fileDetails)
    {
    	System.out.println("Inside Create Review : " + fileDetails);
    	//reviewBusinessService.save(review);
    	fileBusinessService.save(file);
        return "index";
    }
}
