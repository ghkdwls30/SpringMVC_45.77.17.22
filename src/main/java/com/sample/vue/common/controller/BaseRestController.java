package com.sample.vue.common.controller;

import java.sql.Timestamp;
import java.util.Map;

public class BaseRestController {
	
	Map<String, Object> param = null;
	
	protected void registParam(Map<String, Object> param) {
		this.param = param;
	}
	
	protected void clearParam(Map<String, Object> param) {
		this.param = null;
	}
	

	protected void setParam(String key, Object value) {
		this.param.put(key, value);
	}
	
	protected String getParamToString(String key) {
		return (String)this.param.get(key);
	}
	
}
