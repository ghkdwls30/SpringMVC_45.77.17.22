package com.sample.vue.user.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserEntity {
	private String account;
	private String password;
	private String name;
	private String startedAt;
	private String endedAt;
	private String type;
	private String modifiedAt;
	private String createdAt;
	private String company;
	
	// 전체 수량
	private int mobileSearchSlotCnt;
	private int autoSlotCnt;
	private int relSlotCnt;
	
	
	public boolean isAdmin() {
		return type.equals("01") ? true : false;
	}
	
	
}
