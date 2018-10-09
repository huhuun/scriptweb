package com.yoyu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yoyu.pojo.ScriptCheck;
import com.yoyu.resp.ScriptApiResp;

@RestController
@RequestMapping("/api/script")
public class ScriptApiController {
	
	@ResponseBody
	@PostMapping("/check")
	//@GetMapping("/check")
	public ScriptApiResp checkScript(HttpServletRequest request) {
		//校验 参数
		String expireTime = "1546356763";
		ScriptCheck sc = new ScriptCheck();
		int nowSt = (int) ( System.currentTimeMillis() / 1000 );
		int expirest = Integer.parseInt(expireTime);
		int differ =(expirest - nowSt);
		if(differ < 0) {
			sc.setStatus("0");
			sc.setLeftDays("0");
		}else {
			sc.setStatus("1");
			sc.setLeftDays(String.valueOf(differ/(60*60*24)));
		}
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//sc.setExpireTime(sdf.format(new Date()));
		sc.setExpireTime(expireTime);
		return new ScriptApiResp(sc);
	}
}
