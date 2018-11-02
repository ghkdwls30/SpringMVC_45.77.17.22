package com.sample.vue.user.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.vue.common.controller.BaseRestController;
import com.sample.vue.common.model.PageCriteria;
import com.sample.vue.common.utils.DateUtil;
import com.sample.vue.slot.service.SlotService;
import com.sample.vue.user.model.UserEntity;
import com.sample.vue.user.model.UserDetailTuple;
import com.sample.vue.user.service.UserService;


@RestController
@RequestMapping("/rest/users")
public class UserRestController extends BaseRestController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	SlotService slotService;
	
	// 사용자 등록
	@RequestMapping(method = RequestMethod.POST)
	public String insertUser( HttpServletRequest req,
							  HttpServletResponse res,
							  @RequestBody Map<String,Object> param) throws Exception {
	    
		//FIX : 관리자 체크 로직 추가
		
		userService.insertUser(param);
		
		JSONObject response = new JSONObject();
		response.put("result", "SUCCESS");
		
		return response.toJSONString();
	}
	
	// 사용자 수정
	@RequestMapping(method = RequestMethod.PUT)
	public String updateUser( HttpServletRequest req,
							  HttpServletResponse res, 
							  @RequestBody Map<String,Object> param) throws Exception {
	    
		//FIX : 관리자 체크 로직 추가
		
		userService.updateUser(param);
		
		
		
		JSONObject response = new JSONObject();
		response.put("result", "SUCCESS");
		
		return response.toJSONString();
	}
	
	// 사용자 삭제
	@RequestMapping(method = RequestMethod.DELETE)
	public String deleteUser( HttpServletRequest req,
							  HttpServletResponse res, 
							  @RequestBody Map<String,Object> param) throws Exception {
		
		List<String> accounts = (List<String>)param.get("accounts");
		userService.deleteUser( accounts);
		
		JSONObject response = new JSONObject();
		response.put("result", "SUCCESS");
		
		return response.toJSONString();
		
	}

	// 사용자 조회
	@RequestMapping(value="/detail/{id}",method = RequestMethod.GET)
	public UserDetailTuple selectDetailUser( HttpServletRequest req,
							  HttpServletResponse res, 
							  @PathVariable String id,
							  @RequestParam Map<String,Object> param) throws Exception {
		UserDetailTuple tuple = userService.selectDetailUser(param);
		return tuple;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Object selectUserList( HttpServletRequest req,
											HttpServletResponse res, 								
											@ModelAttribute PageCriteria pageCriteria,
											@RequestParam Map<String,Object> param) throws Exception {
		
		Object entitys = null;
		
		if( pageCriteria.getPage() != null && pageCriteria.getSize() != null) {
			// 페이징 리스트
			entitys = userService.selectUserListPage(param, pageCriteria);
		}else {
			// 전체 리스트
//			entitys = userService.selectUserList();
		}
		
		return entitys;
	}

	/**
	 * 튜플 리스트
	 * @param req
	 * @param res
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public Object selectUserDetailListPage( HttpServletRequest req,
											HttpServletResponse res, 								
											@ModelAttribute PageCriteria pageCriteria,
											@RequestParam Map<String,Object> param) throws Exception {
		
		Object entitys = null;
		
		if( pageCriteria.getPage() != null && pageCriteria.getSize() != null) {
			// 페이징 리스트
			entitys = userService.selectUserDetailListPage(param, pageCriteria);
		}else {
			// 전체 리스트
			entitys = userService.selectUserDetailList(param);
		}
		 	
		return entitys;
	}
	
	
	
	
//	/**
//	 * 단일 엔티티 조회
//	 * @param req
//	 * @param res
//	 * @param id
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value="/{id}",method = RequestMethod.GET)
//	public UserEntity selectUser( HttpServletRequest req,
//							  HttpServletResponse res, 
//							  @PathVariable String id,
//							  @RequestParam Map<String,Object> param) throws Exception {
//		
//
//		// 컨트롤 -> 서비스로 넘어가는 파리미터는 무조건 Map형태여야함.
//		// 이유 : 파라미터로 넣게 되면 추수 서비스에 파라미터가 추가/삭제 되는 경우 메소드의 형태가 변경되기 때문에
//		UserEntity entity = userService.selectUser(param);
//		
//		return entity;
//	}
//	
	
}
