package com.yoyu.req;

import com.yoyu.pojo.PageInfo;

public class BaseReq <T>{

	private T item;
	
	private PageInfo page;
	
	public BaseReq() { }
	
	public BaseReq(T item) {
		this.item = item;

	}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}

	public PageInfo getPage() {
		return page;
	}

	public void setPage(PageInfo page) {
		this.page = page;
	}
	
	
}
