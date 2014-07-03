package com.codeproof.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.codeproof.common.model.dto.UserDTO;
import com.codeproof.spec.ReviewBusinessService;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	
	@Autowired
	ReviewBusinessService reviewBusinessService;
	
	@RequestMapping(value="/success", method=RequestMethod.GET)
	public ModelAndView success() {
 
		ModelAndView model = new ModelAndView();
		  /*if (userName != null) {
			model.addObject("error", "Invalid username and password!");
		  }
	 
		  if (pasword != null) {
			model.addObject("msg", "You've been logged out successfully.");
		  }*/
		  model.setViewName("success");
 
	  return model;
 
	}
	
	@RequestMapping(value="/validate", method=RequestMethod.POST)
	public @ResponseBody UserDTO login(@RequestBody UserDTO user) {
 
		UserDTO returnUser = new UserDTO();
		returnUser.setUserName(user.getUserName());
		System.out.println("Validating : " + user.getUserName());
		//returnUser.setUserName("Rajiv");
		//System.out.println("Validate.............. Username : " + user.getUserName() + " password : " + user.getPassword());
	  ModelAndView model = new ModelAndView();
	  model.addObject("user", user);
	  /*if (userName != null) {
		model.addObject("error", "Invalid username and password!");
	  }
 
	  if (pasword != null) {
		model.addObject("msg", "You've been logged out successfully.");
	  }*/
	  model.setViewName("success");
 
	  return returnUser;
 
	}
	
	

}
