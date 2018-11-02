package com.sample.vue.rankqueue.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sample.vue.user.model.UserEntity;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sample.vue.user.model.UserDetailTuple;


@Mapper
public interface RankQueueMapper {

	void deleteBySlotId(@Param("slotId") Integer slotId);

	List<Map<String, Object>> selectRankQueueList();
}
