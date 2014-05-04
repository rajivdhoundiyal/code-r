package com.codeproof.mvc;

 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codeproof.spec.ReviewBusinessService;

@Controller
@RequestMapping(value="/review")
public class ReviewController
{
	@Autowired
	ReviewBusinessService reviewBusinessService;

    @RequestMapping(method=RequestMethod.GET)
    public String displaySortedMembers(Model model)
    {
    	System.out.println("Is that so....");
        //model.addAttribute("newMember", new Member());
        //model.addAttribute("members", memberDao.findAllOrderedByName());
        return "index";
    }
    
    /*@RequestMapping(method=RequestMethod.GET)
    public List<String> getReview(String userName, String filter) {
    	return new ArrayList<String>();
    }*/

    /*@RequestMapping(method=RequestMethod.POST)
    public String registerNewMember(@ModelAttribute("newMember") Member newMember, BindingResult result, Model model)
    {
        if (!result.hasErrors()) {
            memberDao.register(newMember);
            return "redirect:/";
        }
        else {
            model.addAttribute("members", memberDao.findAllOrderedByName());
            return "index";
        }
    }*/
}
