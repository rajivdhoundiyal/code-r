package com.codeproof.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codeproof.model.dto.FileContentDTO;
import com.codeproof.model.dto.FileDetailsDTO;

@Controller
@RequestMapping(value="/file")
public class FileController {

	@RequestMapping(value = "/content", method=RequestMethod.POST)
    public String saveFileContent(@RequestBody FileContentDTO fileContent)
    {
    	System.out.println("Inside Create Review : " + fileContent);
    	//reviewBusinessService.save(review);
        return "index";
    }
	
	@RequestMapping(value = "/details", method=RequestMethod.POST)
    public String saveFileDetails(@RequestBody FileDetailsDTO fileDetails)
    {
    	System.out.println("Inside Create Review : " + fileDetails);
    	//reviewBusinessService.save(review);
        return "index";
    }
}
