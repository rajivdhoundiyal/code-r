package com.codeproof.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/navigate")
public class NavigationController {

	@RequestMapping(value="{view}")
	public String navigateTo(@PathVariable String view) {
		return view;
	}
}
