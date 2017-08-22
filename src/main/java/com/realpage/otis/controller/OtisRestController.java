package com.realpage.otis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OtisRestController {

	@RequestMapping(value="/roles",method = RequestMethod.GET)
	public String[] getRoles(){
		
		String[] roles = {"QA","Supervisor","Data Entry", "User","SeniorQA"};
		return roles;
	}
	
}
