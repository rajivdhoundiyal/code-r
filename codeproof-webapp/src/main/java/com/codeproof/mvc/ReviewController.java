package com.codeproof.mvc;

 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codeproof.model.dto.ReviewDTO;
import com.codeproof.spec.ReviewBusinessService;

@Controller
@RequestMapping(value="/review")
public class ReviewController
{
	@Autowired
	ReviewBusinessService reviewBusinessService;
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public String getReview(@PathVariable String id)
    {
    	System.out.println("Inside Get Review : " + id);
        return "index";
    }
    
    @RequestMapping(value="/filter/{username}", method=RequestMethod.GET)
    public String getReviewByUserName(@PathVariable String userName)
    {
    	System.out.println("Inside Get Review : " + userName);
        return "index";
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String updateReview(@PathVariable String id)
    {
    	System.out.println("Inside Upadate Review : " + id);
        return "index";
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteReview(@PathVariable String id)
    {
    	System.out.println("Inside Delete Review : " + id);
        return "index";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String createReview(@RequestBody ReviewDTO review)
    {
    	System.out.println("Inside Create Review : " + review);
    	reviewBusinessService.save(review);
        return "index";
    }
}
