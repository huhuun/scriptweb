package com.yoyu.resp;

import com.yoyu.constant.CommonConstants;
import com.yoyu.pojo.Datatable;

public class BaseResp <T>{

	private String code;
	
	private String msg;
	
	private Datatable<T> dataTables;
	
//	private T item;
//	
//	private List<T> itemList;
//	
//	private PageInfo page;

	public BaseResp() {
		this.code = CommonConstants.RESP_CODE_SUCCESS;
		this.msg = CommonConstants.RESP_MSG_SUCCESS;
	}
	
	public BaseResp(String msg) {
		this.code = CommonConstants.RESP_CODE_SUCCESS;
		this.msg = msg;
	}
	public BaseResp(String msg, String code) {
		this.code = code;
		this.msg = msg;
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

	public Datatable<T> getDataTables() {
		return dataTables;
	}

	public void setDataTables(Datatable<T> dataTables) {
		this.dataTables = dataTables;
	}
	
	
}
