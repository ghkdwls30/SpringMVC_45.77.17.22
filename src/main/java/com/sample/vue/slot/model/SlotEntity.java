package com.sample.vue.slot.model;

import java.util.List;

import com.sample.vue.user.model.UserEntity;

import lombok.Data;

@Data
public class SlotEntity {
	
	
	public static final String TYPE_MOBILE_SEARCH = "MS";
	public static final String TYPE_AUTO = "A";
	public static final String TYPE_RELATION = "R";
	
	public static final String STATUS_ACCEPT = "A";
	public static final String STATUS_DENY = "D";
	
	private Integer slotId;
	private String account;
	private String searchKw;
	private String exposeKw;
	private String type;
	private String modifiedAt;
	private String createdAt;
	private Integer ranking;
	private Integer rankTot;
	private String status;
	
	
	private List<SlotEntity> slotEntityList ;
}
