package com.yoyu.req;

public class DataTableReq {

	//获取请求次数
    private String draw = "0";
    //数据起始位置
    private String start;
    //数据长度
    private String length;
	public String getDraw() {
		return draw;
	}
	public void setDraw(String draw) {
		this.draw = draw;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
    
    
}
