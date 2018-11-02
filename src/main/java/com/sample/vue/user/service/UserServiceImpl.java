package com.sample.vue.user.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SlotService slotService;

	// 사용자 등록
	@Override
	public void insertUser( Map<String, Object> param) throws Exception {
		UserEntity entity = new UserEntity();
		BeanUtils.populate(entity,param);
		userRepository.insert( entity);

//		int mobileSearchSlotCnt = (int)param.get("mobileSearchSlotCnt");
//		int autoSlotCnt = (int)param.get("autoSlotCnt");
//		int relSlotCnt = (int)param.get("relSlotCnt");
//		
//		slotService.registSlot(entity.getAccount(), SlotEntity.TYPE_MOBILE_SEARCH, mobileSearchSlotCnt);
//		slotService.registSlot(entity.getAccount(), SlotEntity.TYPE_AUTO, autoSlotCnt);
//		slotService.registSlot(entity.getAccount(), SlotEntity.TYPE_RELATION, relSlotCnt);
	}
	
	// 전체 사용자 조회
	@Override
	public List<UserEntity> selectUserList(Map<String, Object> param) throws Exception {
		return userMapper.selectUserList( param);
	}
	
	/**
	 * 단일쿼리 : 유저 조회
	 */
	@Override
	public UserEntity selectUser( String account) throws Exception {
		UserEntity entity = new UserEntity();
		entity.setAccount(account);
		return userRepository.selectOne(entity);
	}
	
	/**
	 * 단일쿼리 : 유저 갱신
	 */
	@Override
	public void updateUser(Map<String, Object> param) throws Exception { 
		log.debug("updateUser");
		//FIX: 복사하지 않을 프로퍼티 설정하는 방법!
		UserEntity entity = new UserEntity();
		BeanUtils.populate(entity,param);
		userRepository.update( entity);
		
		// 슬롯 중지처리
		slotService.disableSlot(param);
	}
	
	/**
	 * 단일쿼리 : 유저 삭제
	 */
	@Override
	public void deleteUser(List<String> accounts) throws Exception {
		
		for (String account : accounts) {
			UserEntity entity = new UserEntity();
			entity.setAccount(account);
			userRepository.delete( entity);
		}
		
	}
	

	/**
	 * 복합쿼리 : 단일 튜플 조회 예제 
	 */
	@Override
	public UserDetailTuple selectDetailUser(Map<String, Object> param) {
		return userMapper.selectDetailUser( param);
	}
	


	/**
	 * 복합쿼리 : 엔티티 리스트 페이징 조회 예제 
	 */
//	@Override
//	public PageInfo<UserEntity> selectUserListPage(Map<String, Object> param, PageCriteria pageCriteria) {
//		PageHelper.startPage(pageCriteria.getPage(), pageCriteria.getSize());
//		List<UserEntity> list = userMapper.selectUserList( param);		
//		return new PageInfo<UserEntity>(list);
//	}

	/**
	 * 복합쿼리 : 튜플 리스트 페이징 조회 예제 
	 */
	@Override
	public PageInfo<UserDetailTuple> selectUserDetailListPage(Map<String, Object> param, PageCriteria pageCriteria) {
		PageHelper.startPage(pageCriteria.getPage(), pageCriteria.getSize());
		List<UserDetailTuple> list = userMapper.selectUserDetailList( param);		
		return new PageInfo<UserDetailTuple>(list);
	}

	/**
	 * 복합쿼리 : 튶플 리스트 조회 예제 
	 */
	@Override
	public List<UserDetailTuple> selectUserDetailList(Map<String, Object> param) {
	
		return userMapper.selectUserDetailList(param);
	}

	@Override
	public PageInfo<UserEntity> selectUserListPage(Map<String, Object> param, PageCriteria pageCriteria) {
		PageHelper.startPage(pageCriteria.getPage(), pageCriteria.getSize());
		List<UserEntity> list = userMapper.selectUserList( param);		
		return new PageInfo<UserEntity>(list);
	}

	@Override
	public UserEntity selectUserByAccountAndPassword(Map<String, Object> param) throws Exception {
		return userMapper.selectUserByAccountAndPassword( param);
	}

	@Override
	public int selectUserListCnt(Map<String, Object> param) {
		return userMapper.selectUserListCnt( param);
	}

	@Override
	public UserDetailTuple selectDetailUser(String account) {
		return userMapper.selectDetailUser( account);
	}

	@Override
	public List<UserEntity> selectAllUserList() throws Exception {
		return userMapper.selectAllUserList();
	}


}
	