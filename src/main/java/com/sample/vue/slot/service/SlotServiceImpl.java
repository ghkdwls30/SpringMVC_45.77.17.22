package com.sample.vue.slot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sample.vue.common.model.PageCriteria;
import com.sample.vue.common.utils.AuthUtil;
import com.sample.vue.rankqueue.model.RankQueueEntity;
import com.sample.vue.rankqueue.repository.RankQueueMapper;
import com.sample.vue.rankqueue.repository.RankQueueRepository;
import com.sample.vue.slot.model.SlotEntity;
import com.sample.vue.slot.repository.SlotMapper;
import com.sample.vue.slot.repository.SlotRepository;
import com.sample.vue.user.model.UserDetailTuple;
import com.sample.vue.user.model.UserEntity;
import com.sample.vue.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SlotServiceImpl implements SlotService {

	@Autowired
	SlotMapper slotMapper;
	
	@Autowired
	SlotRepository slotRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RankQueueMapper rankQueueMapper;
	
	@Autowired
	RankQueueRepository rankQueueRepository;
	
	@Override
	public void registSlot(String account, String type, int slotCnt) {
		
		for (int i = 0; i < slotCnt; i++) {
			SlotEntity entity = new SlotEntity();
			entity.setAccount(account);
			entity.setType(type);
			
			slotRepository.insert(entity);
		}
	}

	@Override
	public PageInfo<SlotEntity> selectSlotListPage(Map<String, Object> param, PageCriteria pageCriteria) {
		PageHelper.startPage(pageCriteria.getPage(), pageCriteria.getSize());
		List<SlotEntity> list = slotMapper.selectSlotList( param);		
		return new PageInfo<SlotEntity>(list);
	}

	@Override
	public void updateSlot(SlotEntity slotEntity) {
			slotRepository.update(slotEntity);
	}

	@Override
	public void deleteSlot(List<SlotEntity> slotEntityList) {
		for (SlotEntity slotEntity : slotEntityList) {
			slotRepository.delete(slotEntity);
			rankQueueMapper.deleteBySlotId( slotEntity.getSlotId());
		}		
	}

	@Override
	public void saveSlot(List<SlotEntity> slotEntityList) throws Exception {
		
		Map<String, UserDetailTuple> userMap = new HashMap<String, UserDetailTuple>();
		
		for (SlotEntity slotEntity : slotEntityList) {
			
			if( slotEntity.getAccount() == null || slotEntity.getAccount().trim().length() == 0) {
				slotEntity.setAccount(AuthUtil.getCurrentUserAccount());
			}
			
			UserDetailTuple userDetailTuple = null;			
			String currentUserKey = slotEntity.getAccount();
			if( userMap.containsKey( currentUserKey)) {
				 userDetailTuple = userMap.get( currentUserKey);	
			}else {
				 userDetailTuple = userService.selectDetailUser(currentUserKey);
				 userMap.put(currentUserKey, userDetailTuple);
			}
			
			if( slotEntity.getSlotId() == -1) {
				
				if( slotEntity.getType().equals(SlotEntity.TYPE_AUTO)) {
					
					if(  userDetailTuple.getRemainAutoSlotCnt() == 0) {
						throw new Exception("자동완성 슬롯 수량이 부족하여 저장 할 수 없는 슬롯이 포함되어 있습니다.");
					}	
					userDetailTuple.setRemainAutoSlotCnt( userDetailTuple.getRemainAutoSlotCnt() - 1);
			    }else if( slotEntity.getType().equals(SlotEntity.TYPE_MOBILE_SEARCH)) {
			    	
			    	if(  userDetailTuple.getRemainMobileSearchSlotCnt() == 0) {
						throw new Exception("모바일 통합 검색 슬롯 수량이 부족하여 저장 할 수 없는 슬롯이 포함되어 있습니다.");
					}	
			    	
			    	userDetailTuple.setRemainMobileSearchSlotCnt(userDetailTuple.getRemainMobileSearchSlotCnt()  - 1); 
			    }else if( slotEntity.getType().equals(SlotEntity.TYPE_RELATION)) {
			    	
			    	if(  userDetailTuple.getRemainRelSlotCnt() == 0) {
						throw new Exception("연관검색어 슬롯 수량이 부족하여 저장 할 수 없는 슬롯이 포함되어 있습니다.");
					}	
			    	
			    	userDetailTuple.setRemainRelSlotCnt( userDetailTuple.getRemainRelSlotCnt() - 1);
			    }				    			
				
				slotRepository.insert(slotEntity);
//				insertRankQueue(slotEntity);
				
			}else {
				
				SlotEntity oldSlotEntity = new SlotEntity();
				oldSlotEntity.setSlotId(slotEntity.getSlotId());
				oldSlotEntity = slotRepository.selectOne(oldSlotEntity);
				
				if( slotEntity.getStatus().equals(SlotEntity.STATUS_ACCEPT)) {
					
					if( !oldSlotEntity.getStatus().equals(slotEntity.getStatus())) {
						
						if( slotEntity.getType().equals(SlotEntity.TYPE_AUTO)) {
							
							if(  userDetailTuple.getRemainAutoSlotCnt() == 0) {
								throw new Exception("자동완성 슬롯 수량이 부족하여 저장 할 수 없는 슬롯이 포함되어 있습니다.");
							}	
							userDetailTuple.setRemainAutoSlotCnt( userDetailTuple.getRemainAutoSlotCnt() - 1);
					    }else if( slotEntity.getType().equals(SlotEntity.TYPE_MOBILE_SEARCH)) {
					    	
					    	if(  userDetailTuple.getRemainMobileSearchSlotCnt() == 0) {
								throw new Exception("모바일 통합 검색 슬롯 수량이 부족하여 저장 할 수 없는 슬롯이 포함되어 있습니다.");
							}	
					    	
					    	userDetailTuple.setRemainMobileSearchSlotCnt(userDetailTuple.getRemainMobileSearchSlotCnt()  - 1); 
					    }else if( slotEntity.getType().equals(SlotEntity.TYPE_RELATION)) {
					    	
					    	if(  userDetailTuple.getRemainRelSlotCnt() == 0) {
								throw new Exception("연관검색어 슬롯 수량이 부족하여 저장 할 수 없는 슬롯이 포함되어 있습니다.");
							}	
					    	
					    	userDetailTuple.setRemainRelSlotCnt( userDetailTuple.getRemainRelSlotCnt() - 1);
					    }		
					}
					
					slotRepository.update(slotEntity);
//					insertRankQueue(slotEntity);
					 
				}else {
					slotRepository.update(slotEntity);
//					insertRankQueue(slotEntity);
				}
				
			}
		}
		
	}

	@Override
	public int selectSlotListCnt(Map<String, Object> param) {
		return slotMapper.selectSlotListCnt(param);
	}

	@Override
	public void disableSlot(Map<String, Object> param) {
		
		String account = (String)param.get("account");
		
		// 중지 처리해야할 각 타입별 갯수
		Map<String, Long> map = slotMapper.disableSlotCntMap(param);
		
		int disableMobileSearchSlotCnt = ((Long)map.get("disableMobileSearchSlotCnt")).intValue();
		int disableAutoSlotCnt = ((Long)map.get("disableAutoSlotCnt")).intValue();
		int disableRelSlotCnt = ((Long)map.get("disableRelSlotCnt")).intValue();
		
		// 가장 등록일이 오래된 컬럼을 중지시킴
		if( disableMobileSearchSlotCnt > 0) {
			slotMapper.disableSlot( SlotEntity.TYPE_MOBILE_SEARCH, disableMobileSearchSlotCnt, account);
		}
		
		if( disableRelSlotCnt > 0) {
			slotMapper.disableSlot( SlotEntity.TYPE_RELATION, disableRelSlotCnt, account);
		}

		if( disableAutoSlotCnt > 0) {
			slotMapper.disableSlot( SlotEntity.TYPE_AUTO, disableAutoSlotCnt, account);
		}
		
	}

	@Override
	public List<Map<String, String>> selectSlotExcelList(Map<String, Object> param) {
		return slotMapper.selectSlotExcelList(param);
	}

	@Override
	public List<SlotEntity> selectSlotList(Map<String, Object> param) {
		return slotMapper.selectSlotList(param);
	}

	@Override
	public void updateSlot(List<SlotEntity> slotEntityList) {
		for (SlotEntity slotEntity : slotEntityList) {
			slotRepository.update(slotEntity);
			
//			insertRankQueue(slotEntity);
		}
		
	}

	private void insertRankQueue(SlotEntity slotEntity) {
		RankQueueEntity rankQueueEntity = new RankQueueEntity();
		rankQueueEntity.setSlotId(slotEntity.getSlotId());
		rankQueueRepository.insert(rankQueueEntity);
	}
	
	

}
	