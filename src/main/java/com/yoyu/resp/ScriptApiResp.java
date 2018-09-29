package com.yoyu.resp;

public class ScriptApiResp {

	
	private static String DEFAULT_CODE = "0";
	private static String DEFAULT_MSG = "success";
	private static String DEFAULT_DATA = "";
	
	private String code;
	
	private String msg;
	
	private Object data;

	public ScriptApiResp() {
		this.code = DEFAULT_CODE;
		this.msg = DEFAULT_MSG;
		this.data = DEFAULT_DATA;
	}
	
	public ScriptApiResp(String code, String msg) {
		
		this.code = code;
		this.msg = msg;
	}
	
	public ScriptApiResp(String code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	
	
}
