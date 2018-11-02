package com.sample.vue.home.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.sample.vue.board.model.BoardEntity;
import com.sample.vue.board.service.BoardService;
import com.sample.vue.board.service.BoardServiceImpl;
import com.sample.vue.common.model.PageCriteria;
import com.sample.vue.common.utils.AuthUtil;
import com.sample.vue.common.utils.ExcelCellRef;
import com.sample.vue.common.utils.ExcelReadOption;
import com.sample.vue.common.utils.ExcelUtil;
import com.sample.vue.slot.model.SlotEntity;
import com.sample.vue.slot.service.SlotService;
import com.sample.vue.user.service.UserService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	SlotService slotService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest req
							 , HttpServletResponse res
							 ,@ModelAttribute PageCriteria pageCriteria
							 ,@RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView m = new ModelAndView();
		m.setViewName("home.homeContent.base");
	
		if( pageCriteria.getPage() == null || pageCriteria.getSize() == null) {
			pageCriteria.setPage(1);
			pageCriteria.setSize(20);
		}
		
		if( !AuthUtil.isAdmin()) {
			param.put("account", AuthUtil.getCurrentUserAccount());
		}
		param.put("isAdmin", AuthUtil.isAdmin());
		
		PageInfo<SlotEntity> slotEntityPageList = slotService.selectSlotListPage(param, pageCriteria);
		
		BoardEntity boardEntity = new BoardEntity();
		boardEntity.setSeq( BoardEntity.KEY_NOTI_BOARD);
		m.addObject("boardEntity", boardService.selectBoard(boardEntity));
		m.addObject("slotEntityList", slotEntityPageList.getList());
		m.addObject("userEntityList", userService.selectAllUserList());
		m.addObject("pageInfo", slotEntityPageList);
		m.addObject("isAdmin", AuthUtil.isAdmin());
		
		if( !AuthUtil.isAdmin()) {
			m.addObject("userDetailTuple", userService.selectDetailUser( AuthUtil.getCurrentUserAccount()));	
		}
		
		if( AuthUtil.isAdmin()) {
			// 전체사용자 리스트 카운트
			m.addObject("allUserCnt",userService.selectUserListCnt(param));
			
			// 전체 슬롯 수
			m.addObject("allSlotCnt",slotService.selectSlotListCnt(param));
		}
		
		return m;
	}
	
	@RequestMapping(value = "/excelList")
	public void excelList(HttpServletRequest request 
							  ,HttpServletResponse response
							  ,@RequestParam Map<String, Object> param
			) throws InvalidFormatException {
		
		if( !AuthUtil.isAdmin()) {
			param.put("account", AuthUtil.getCurrentUserAccount());
		}
		List<Map<String, String>> slotEntityList = slotService.selectSlotExcelList(param);
		
        Map<String , Object> beans = new HashMap<String , Object>();
        beans.put("list", slotEntityList);
        
        //FIX: 한글 NAME 안되는 현상 해결하기
		ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.download(request, response, beans, "SLOT_LIST",  "EL_0001.xlsx");
	}
	
	
	@RequestMapping(value = "/excelForm")
	public void excelForm(HttpServletRequest request 
							  ,HttpServletResponse response
							  ,@RequestParam Map<String, Object> param
			) throws InvalidFormatException {
		
		if( !AuthUtil.isAdmin()) {
			param.put("account", AuthUtil.getCurrentUserAccount());
		}
		List<SlotEntity> slotEntityList = slotService.selectSlotList(param);
		
        Map<String , Object> beans = new HashMap<String , Object>();
        beans.put("list", slotEntityList);
        
        //FIX: 한글 NAME 안되는 현상 해결하기
		ExcelUtil excelUtil = new ExcelUtil();
		
		if( AuthUtil.isAdmin()) {
			excelUtil.download(request, response, beans, "SLOT_UPLOAD_FORM_ADMIN",  "EF_0001_ADMIN.xlsx");
		}else {
			excelUtil.download(request, response, beans, "SLOT_UPLOAD_FORM",  "EF_0001.xlsx");	
		}
        
	}
	

}
	
	
	
