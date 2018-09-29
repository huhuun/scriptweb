package com.yoyu.resp;

import com.yoyu.pojo.Datatable;

public class DatatableResp<T>{

	private String code;
	
	private String msg;
	
	private Datatable<T> dataTables;

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
