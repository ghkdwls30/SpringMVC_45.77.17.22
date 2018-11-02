package com.sample.vue.user.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sample.vue.user.model.UserEntity;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sample.vue.user.model.UserDetailTuple;


@Mapper
public interface UserMapper {
	UserEntity selectUser(Map<String, Object> param);
	UserDetailTuple selectDetailUser(Map<String, Object> param);
	List<UserEntity> selectUserList(Map<String, Object> param);
	List<UserDetailTuple> selectUserDetailList(Map<String, Object> param);
	UserEntity selectUserByAccountAndPassword(Map<String, Object> param);
	int selectUserListCnt(Map<String, Object> param);
	UserDetailTuple selectDetailUser(String account);
	List<UserEntity> selectAllUserList();
}
