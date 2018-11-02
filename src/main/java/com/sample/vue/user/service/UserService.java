package com.sample.vue.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.sample.vue.common.model.PageCriteria;
import com.sample.vue.user.model.UserDetailTuple;
import com.sample.vue.user.model.UserEntity;

public interface UserService  {
	void insertUser(Map<String, Object> param) throws Exception;
	void updateUser(Map<String, Object> param) throws Exception;
	UserDetailTuple selectDetailUser(Map<String, Object> param) throws Exception;
	List<UserEntity> selectUserList(Map<String, Object> param) throws Exception;
	PageInfo<UserEntity> selectUserListPage(Map<String, Object> param, PageCriteria pageCriteria);
	PageInfo<UserDetailTuple> selectUserDetailListPage(Map<String, Object> param, PageCriteria pageCriteria);
	List<UserDetailTuple> selectUserDetailList(Map<String, Object> param);
	UserEntity selectUser(String account) throws Exception;
	void deleteUser(List<String> accounts) throws Exception;
	UserEntity selectUserByAccountAndPassword(Map<String, Object> param) throws Exception;
	int selectUserListCnt(Map<String, Object> param);
	UserDetailTuple selectDetailUser(String currentUserAccount);
	List<UserEntity> selectAllUserList()  throws Exception;
}
