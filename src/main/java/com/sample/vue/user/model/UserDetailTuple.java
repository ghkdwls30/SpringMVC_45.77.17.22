package com.sample.vue.user.model;

import com.sample.vue.group.model.GroupEntity;

import lombok.Data;

@Data
public class UserDetailTuple {	
	private UserEntity userEntity;
	
	// 남은 수량
	private int remainMobileSearchSlotCnt;
	private int remainAutoSlotCnt;
	private int remainRelSlotCnt;
}
