package com.yoyu.pojo;

import java.util.List;

public class Datatable<T> {

	//获取请求次数
    private String draw;
    //总记录数
    private String recordsTotal;
    //过滤后记录数
    private String recordsFiltered;
    
    private List<T> Data;

	public String getDraw() {
		return draw;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}

	public String getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(String recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public String getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(String recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<T> getData() {
		return Data;
	}

	public void setData(List<T> data) {
		Data = data;
	}
    
}
