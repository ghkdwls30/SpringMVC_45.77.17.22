package com.sample.vue.slot.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sample.vue.common.controller.BaseRestController;
import com.sample.vue.slot.model.SlotEntity;
import com.sample.vue.slot.service.SlotService;


@RestController
@RequestMapping("/rest/slots")
public class SlotRestController extends BaseRestController {
	
	@Autowired
	SlotService slotService;
	
	// 슬롯 저장
	@RequestMapping(method = RequestMethod.PUT)
	public String saveSlot( HttpServletRequest req,
							  HttpServletResponse res, 
							  @RequestBody SlotEntity slotEntity) throws Exception {
	    
		//FIX : 관리자 체크 로직 추가
		slotService.saveSlot( slotEntity.getSlotEntityList());
		
		JSONObject response = new JSONObject();
		response.put("result", "SUCCESS");
		
		return response.toJSONString();
	}
	
	// 슬롯 수정
	@RequestMapping(value="/disable" ,method = RequestMethod.PUT)
	public String updateSlot( HttpServletRequest req,
							  HttpServletResponse res, 
							  @RequestBody SlotEntity slotEntity) throws Exception {
	    
		//FIX : 관리자 체크 로직 추가
		slotService.updateSlot( slotEntity.getSlotEntityList());
		
		JSONObject response = new JSONObject();
		response.put("result", "SUCCESS");
		
		return response.toJSONString();
	}
	

	// 슬롯 삭제
	@RequestMapping(method = RequestMethod.DELETE)
	public String deleteSlot( HttpServletRequest req,
							  HttpServletResponse res, 
							  @RequestBody SlotEntity slotEntity) throws Exception {
	    
		//FIX : 관리자 체크 로직 추가
		slotService.deleteSlot( slotEntity.getSlotEntityList());
		
		JSONObject response = new JSONObject();
		response.put("result", "SUCCESS");
		
		return response.toJSONString();
	}
	
	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) throws UnsupportedEncodingException {
		
		JSONObject response = new JSONObject();
		response.put("result", "FAIL");
		response.put("MSG", URLEncoder.encode(ex.getMessage(), "UTF-8"));

		return response.toJSONString();

	}
	
}
