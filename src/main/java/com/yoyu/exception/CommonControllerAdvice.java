package com.yoyu.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yoyu.resp.ScriptApiResp;

@ControllerAdvice
public class CommonControllerAdvice {

	@ResponseBody
    @ExceptionHandler(value = ScriptException.class)
    public ScriptApiResp apiErrorHandler(ScriptException ex) {
		ScriptApiResp resp = new ScriptApiResp();
		resp.setCode(ex.getCode());
		resp.setMsg(ex.getMsg());
        return resp;
    }
	
	@ResponseBody
    @ExceptionHandler(value = BizException.class)
    public ScriptApiResp bizErrorHandler(BizException ex) {
		ScriptApiResp resp = new ScriptApiResp();
		resp.setCode(ex.getCode());
		resp.setMsg(ex.getMsg());
        return resp;
    }
	
	/*@ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ScriptApiResp errorHandler(Exception ex) {
		ScriptApiResp resp = new ScriptApiResp();
		resp.setCode("9999");
		resp.setMsg("system_error");
        return resp;
    }*/
	
}
