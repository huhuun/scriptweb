package com.yoyu.resp;

import java.util.List;

import com.yoyu.pojo.Datatable;
import com.yoyu.pojo.PageInfo;

public class BaseResp <T>{

	private String code;
	
	private String msg;
	
	private Datatable<T> dataTables;
	
//	private T item;
//	
//	private List<T> itemList;
//	
//	private PageInfo page;

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
