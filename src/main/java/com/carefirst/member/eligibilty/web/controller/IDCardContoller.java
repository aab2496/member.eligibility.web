package com.carefirst.member.eligibilty.web.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IDCardContoller {

	@Autowired
	IdCardAPIService service;

	@GetMapping("/idcard")
	public String viewIdCards(Model model) {
		System.out.println(" Inside contoller>>>>>>>>>>>>");
		model.addAttribute("test", "test");
		return "idcard";
	}

	@RequestMapping(path = "/idcard/image/front")
	private ResponseEntity<Object> idCard() {
		return service.getIDCardDocument("ID_FRONT-171-917218512-230777001", "");

	}
	
	@RequestMapping(path = "/idcard/image/back")
	private ResponseEntity<Object> idCardBack() {
		return service.getIDCardDocument("ID_BACK-171-917218512-230777001", "");

	}

	@RequestMapping(path = "/image1")
	private void idCard1(HttpServletResponse response) throws IOException {
		response.setContentType("image/png");
		byte[] data = service.getIDCardDocument2("ID_FRONT-171-917218512-230777001", "");

		InputStream inputStream = new ByteArrayInputStream(data);
		IOUtils.copy(inputStream, response.getOutputStream());

	}
}
