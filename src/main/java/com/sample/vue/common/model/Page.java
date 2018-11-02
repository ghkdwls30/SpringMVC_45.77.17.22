package com.sample.vue.common.model;

import java.util.List;

import com.github.pagehelper.PageInfo;

public class Page<T> {
	
	private List<T> list;

	public Page(List<T> list) {
		this.list = list;
	}
	
	public List<T> getList() {
		return list;
	}
	
	public PageInfo<T> getPageInfo() {
		return new PageInfo<T>(list);
	}
}
