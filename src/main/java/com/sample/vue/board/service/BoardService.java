package com.sample.vue.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.sample.vue.board.model.BoardEntity;
import com.sample.vue.common.model.PageCriteria;
import com.sample.vue.user.model.UserDetailTuple;
import com.sample.vue.user.model.UserEntity;

public interface BoardService  {

	void saveBaord(Map<String, Object> param) throws Exception;

	BoardEntity selectBoard(BoardEntity boardEntity);
}
