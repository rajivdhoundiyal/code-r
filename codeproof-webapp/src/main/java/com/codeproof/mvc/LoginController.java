package com.codeproof.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.codeproof.model.User;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView login() {
 
		System.out.println("Login..........");
	  ModelAndView model = new ModelAndView();
	  /*if (error != null) {
		model.addObject("error", "Invalid username and password!");
	  }
 
	  if (logout != null) {
		model.addObject("msg", "You've been logged out successfully.");
	  }*/
	  model.setViewName("login");
 
	  return model;
 
	}
	
	@RequestMapping(value="/validate", method=RequestMethod.POST)
	public @ResponseBody User login(@RequestBody User user) {
 
		User returnUser = new User();
		returnUser.setUserName("Rajiv");
		System.out.println("Validate.............. Username : " + user.getUserName() + " password : " + user.getPassword());
	  ModelAndView model = new ModelAndView();
	  /*if (userName != null) {
		model.addObject("error", "Invalid username and password!");
	  }
 
	  if (pasword != null) {
		model.addObject("msg", "You've been logged out successfully.");
	  }*/
	  model.setViewName("login");
 
	  return returnUser;
 
	}

}
