package com.sample.vue.slot.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sample.vue.user.model.UserEntity;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sample.vue.slot.model.SlotEntity;
import com.sample.vue.user.model.UserDetailTuple;


@Mapper
public interface SlotMapper {

	List<SlotEntity> selectSlotList(Map<String, Object> param);

	int selectSlotListCnt(Map<String, Object> param);

	Map<String, Long> disableSlotCntMap(Map<String, Object> param);

	void disableSlot(@Param("type") String type, @Param("cnt") int cnt, @Param("account")String account);

	List<Map<String, String>> selectSlotExcelList(Map<String, Object> param);
}
