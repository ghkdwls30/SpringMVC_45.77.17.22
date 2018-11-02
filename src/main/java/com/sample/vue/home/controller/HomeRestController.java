package com.sample.vue.home.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sample.vue.common.utils.AuthUtil;
import com.sample.vue.common.utils.ExcelCellRef;
import com.sample.vue.common.utils.ExcelReadOption;
import com.sample.vue.slot.model.SlotEntity;
import com.sample.vue.slot.service.SlotService;
import com.sample.vue.user.model.UserEntity;
import com.sample.vue.user.service.UserService;


@RestController
@RequestMapping("/rest/home")
public class HomeRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeRestController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	SlotService slotService;
	
	
	@RequestMapping(value = "/uploadExcel")
	public String uploadExcel(  HttpServletRequest req 
							  ,HttpServletResponse res
							  ,@RequestParam("excelFile") MultipartFile file
			) throws Exception {

			// 설정
			ExcelReadOption excelReadOption = new ExcelReadOption();
			excelReadOption.setStartRow(4);
		
	        //Need to read excel file as follows  but how do I call the filepart here? 
	        XSSFWorkbook wb  = new XSSFWorkbook(file.getInputStream());
	        XSSFSheet sheet = wb.getSheetAt(0);
            
            /**
             * sheet에서 유효한(데이터가 있는) 행의 개수를 가져온다.
             */
            int numOfRows = sheet.getPhysicalNumberOfRows();
            int numOfCells = 4;
            int cellIndex = 0;
            
            // 사용자의 전체 슬롯번호
            Map<String, Object> param = new HashMap<String, Object>();
            
            if( !AuthUtil.isAdmin()) {
            	param.put("account", AuthUtil.getCurrentUserAccount());	
            }
            List<SlotEntity> list = slotService.selectSlotList(param);
            Map<String, String> oldSlotMap =  new HashMap<String, String>();
            for (SlotEntity entity : list) {
            	oldSlotMap.put(String.valueOf( entity.getSlotId()), null); 
			}
            
            List<UserEntity> userEntityList = userService.selectAllUserList();
            Map<String, String> allUserMap =  new HashMap<String, String>();
            for (UserEntity entity : userEntityList) {
            	allUserMap.put(entity.getAccount(), null);
            }
            
            Row row = null;
            Cell cell = null;
            
            String cellName = "";
            String cellValue = "";
            
            SlotEntity slotEntity = null;
            
            List<SlotEntity> slotEntityList = new ArrayList<SlotEntity>();
            
            for(int rowIndex = excelReadOption.getStartRow() - 1; rowIndex < numOfRows; rowIndex++) {
            	
                row = sheet.getRow(rowIndex);
                if(row != null) {
                	
                    slotEntity = new SlotEntity();
                    
                    // 1번째 컬럼 : 슬롯아이디
                    cell = row.getCell(cellIndex++);
                    cellValue = ExcelCellRef.getValue(cell);
                    
                    if( cellValue == null || cellValue.equals("")) continue;
                    
                    if( !oldSlotMap.containsKey(cellValue) && !cellValue.equals("-1")) {
                    	throw new Exception(String.format("%d행 %d열의 슬롯 아이디가 존재하지 않습니다.", rowIndex + 1, cellIndex));
                    }
                    
                    slotEntity.setSlotId( Integer.parseInt( cellValue));
                    
                    // 2번째 컬럼 : 상태
                    cell = row.getCell(cellIndex++);
                    cellValue = ExcelCellRef.getValue(cell);
                    
                    if( !cellValue.equals("D") && !cellValue.equals("A")) {
                    	throw new Exception(String.format("%d행 %d열의 상태 값을 확인해 주세요.", rowIndex + 1, cellIndex));
                    }
                    
                    slotEntity.setStatus(cellValue);
                    
                    
                    // 3번째 컬럼 : 분류
                    cell = row.getCell(cellIndex++);
                    cellValue = ExcelCellRef.getValue(cell);
                    
                    if( !cellValue.equals("R") && !cellValue.equals("A") && !cellValue.equals("MS")) {
                    	throw new Exception(String.format("%d행 %d열의 분류 값을 확인해 주세요.", rowIndex + 1, cellIndex));
                    }
                    
                    slotEntity.setType(cellValue);
                    
                    // 4번째 컬럼 : 검색어
                    cell = row.getCell(cellIndex++);
                    cellValue = ExcelCellRef.getValue(cell);
                    
                    if( cellValue.length() > 50) {
                    	throw new Exception(String.format("%d행 %d열의 검색어의 길이가 너무 깁니다.", rowIndex + 1, cellIndex));
                    }
                    
                    slotEntity.setSearchKw(cellValue);
                    
                    // 5번째 컬럼 : 검색 노출어
                    cell = row.getCell(cellIndex++);
                    cellValue = ExcelCellRef.getValue(cell);
                    
                    if( cellValue.length() > 50) {
                    	throw new Exception(String.format("%d행 %d열의 검색어의 길이가 너무 깁니다.", rowIndex + 1, cellIndex));
                    }
                    
                    slotEntity.setExposeKw(cellValue);
                    
                    
                    if( AuthUtil.isAdmin()) {
                    	
                    	// 6번재 컬럼 : 계정	
                	   cell = row.getCell(cellIndex++);
                       cellValue = ExcelCellRef.getValue(cell);
                       
                       if( !allUserMap.containsKey(cellValue)) {
                       		throw new Exception(String.format("%d행 %d열의 계정이 존재하지 않습니다.", rowIndex + 1, cellIndex));
                       }
                       
                       slotEntity.setAccount(cellValue);
                    	
                    	// 7번재 컬럼 : 현재 순위	
                    	cell = row.getCell(cellIndex++);
                        cellValue = ExcelCellRef.getValue(cell);
                        
                        try {
                        	Integer.parseInt(cellValue);
						} catch (NumberFormatException  e) {
							if( cellValue.length() != 0) {
								throw new Exception(String.format("%d행 %d열의 현재랭킹이 숫자가 아닙니다.", rowIndex + 1, cellIndex));
							}
						}
                        
                        slotEntity.setRanking( cellValue.length() == 0 ? null : Integer.parseInt(cellValue));
                        
                        // 8번재 컬럼 : 전체 순위	
                    	cell = row.getCell(cellIndex++);
                        cellValue = ExcelCellRef.getValue(cell);
                        
                        try {
                        	Integer.parseInt(cellValue);
						} catch (NumberFormatException  e) {
							if( cellValue.length() != 0) {
								throw new Exception(String.format("%d행 %d열의 전체랭킹이 숫자가 아닙니다.", rowIndex + 1, cellIndex));
							}
						}
                        
                        slotEntity.setRankTot( cellValue.length() == 0 ? null : Integer.parseInt(cellValue));
                    }
                    
                    slotEntityList.add(slotEntity);
                }
                
                cellIndex = 0;
                
            }
            
            slotService.saveSlot(slotEntityList);
            
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
