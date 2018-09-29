package com.yoyu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yoyu.resp.ScriptApiResp;

@RestController
@RequestMapping("/script")
public class ScriptApiController {
	
	@ResponseBody
	//@PostMapping("/check")
	@GetMapping("/check")
	public ScriptApiResp checkScript() {
		
		
		return new ScriptApiResp();
	}
	

}
