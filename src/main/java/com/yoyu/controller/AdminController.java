package com.yoyu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/login.html")
	public String login(HttpServletRequest request,ModelAndView model) {
		return "admin/login";
	}
	
	@PostMapping("/login.html")
	public ModelAndView loginon(HttpServletRequest request,ModelAndView model) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username == null || password == null) {
			model.addObject("errMsg", "用户名或密码错误！");
			model.setViewName("admin/login");
		}else if(username.equals("yoyu") && password.equals("123456")){
			model.setViewName("admin/index");
		}
		return model;
	}
	
	
	@RequestMapping("/index.html")
	public String index() {
		return "admin/index";
	}
	

	@RequestMapping("/script.html")
	public String getScript() {
	
		return "/admin/script";
	}
	
	
	
}
