package com.codeproof.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.codeproof.spec.ReviewBusinessService;
import com.codeproof.utilities.ViewConstants;


@Controller
@RequestMapping(value="/welcome")
public class WelcomeController
{
	@Autowired
	ReviewBusinessService reviewBusinessService;

    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView welcome(Model model)
    {
    	ModelAndView modelAndView =  new ModelAndView();
    	modelAndView.setViewName(ViewConstants.INDEX.getViewName());
        return modelAndView;
    }
}