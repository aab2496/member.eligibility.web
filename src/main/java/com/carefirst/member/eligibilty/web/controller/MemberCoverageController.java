package com.carefirst.member.eligibilty.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberCoverageController {

	@GetMapping("/")
	public String viewMemberCoverage(Model model) {
		System.out.println(" Inside MemberCoverageController viewMemberCoverage>>>>>>>>>>>>");
		model.addAttribute("test", "test");
		return "coverage";
	}

}
