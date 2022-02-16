package com.carefirst.member.eligibilty.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mem")
public class MemberCoverageController {
	
	 @GetMapping("/test")
	    public String viewBooks(Model model) {
	        model.addAttribute("test", "test");
	        return "test";
	    }

}
