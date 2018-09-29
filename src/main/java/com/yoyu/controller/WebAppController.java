package com.yoyu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WebAppController {
	
	@RequestMapping("/index")
	public String index() {
		System.out.println("cehsi==================>");
		return "webapp/index";
	}
}
