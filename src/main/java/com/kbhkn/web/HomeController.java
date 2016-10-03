package com.kbhkn.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping(value="/admin/index")
	public String home(){
		return "index";
	}
	
	@GetMapping(value="/login")
	public String login(){
		return "login";
	}
}
