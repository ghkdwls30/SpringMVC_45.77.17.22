package com.sample.vue.board.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sample.vue.board.model.BoardEntity;
import com.sample.vue.board.repository.BoardRepository;
import com.sample.vue.common.model.PageCriteria;
import com.sample.vue.slot.model.SlotEntity;
import com.sample.vue.slot.repository.SlotRepository;
import com.sample.vue.slot.service.SlotService;
import com.sample.vue.user.model.UserDetailTuple;
import com.sample.vue.user.model.UserEntity;
import com.sample.vue.user.repository.UserMapper;
import com.sample.vue.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository boardRepository;

	@Override
	public void saveBaord(Map<String, Object> param) throws Exception {
		BoardEntity entity = new BoardEntity();
		BeanUtils.populate(entity,param);
		boardRepository.update( entity);
	}

	@Override
	public BoardEntity selectBoard( BoardEntity boardEntity) {
		return boardRepository.selectOne(boardEntity);
	}

}
	