package com.sample.vue.board.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardEntity {
	
	public static int KEY_NOTI_BOARD = 1;
	
	
	private int seq;
	private String content;
	private String modifiedAt;
	
	
	
}
