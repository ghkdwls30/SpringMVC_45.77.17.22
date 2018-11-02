package com.sample.vue.slot.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.sample.vue.common.model.PageCriteria;
import com.sample.vue.slot.model.SlotEntity;
import com.sample.vue.user.model.UserDetailTuple;
import com.sample.vue.user.model.UserEntity;

public interface SlotService  {

	void registSlot(String account, String type, int cnt);
	PageInfo<SlotEntity> selectSlotListPage(Map<String, Object> param, PageCriteria pageCriteria);
	void updateSlot(SlotEntity slotEntity);
	void deleteSlot(List<SlotEntity> slotEntityList);
	void saveSlot(List<SlotEntity> slotEntityList) throws Exception;
	int selectSlotListCnt(Map<String, Object> param);
	void disableSlot(Map<String, Object> param);
	List<Map<String, String>> selectSlotExcelList(Map<String, Object> param);
	List<SlotEntity> selectSlotList(Map<String, Object> param);
	void updateSlot(List<SlotEntity> slotEntityList);
	
}
