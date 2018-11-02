package com.sample.vue.board.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sample.vue.board.service.BoardService;
import com.sample.vue.common.utils.AuthUtil;
import com.sample.vue.common.utils.ExcelCellRef;
import com.sample.vue.common.utils.ExcelReadOption;
import com.sample.vue.slot.model.SlotEntity;
import com.sample.vue.slot.service.SlotService;
import com.sample.vue.user.model.UserEntity;
import com.sample.vue.user.service.UserService;


@RestController
@RequestMapping("/rest/board")
public class BoarddRestController {
	
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value="/{seq}",method = RequestMethod.PUT)
	public String saveBoard(  HttpServletRequest req 
							  ,HttpServletResponse res,
							  @PathVariable int seq,
							  @RequestBody Map<String,Object> param
			) throws Exception {
		
			boardService.saveBaord( param);
			
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
