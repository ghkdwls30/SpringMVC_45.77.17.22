package com.sample.vue.common.scheduler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.sample.vue.rankqueue.repository.RankQueueMapper;
import com.sample.vue.slot.model.SlotEntity;
import com.sample.vue.slot.repository.SlotRepository;


@Component
public class RankingTask {
	

	@Autowired
	RankQueueMapper rankQueueMapper;
	
	@Autowired
	SlotRepository slotRepository;
	
    public void TestScheduler() throws IOException, XPathExpressionException, SAXException, ParserConfigurationException{
    	
    	List<Map<String, Object>> rankQueueList = getDataSource();
    	
    	for (Map<String, Object> rankQueue : rankQueueList) {
    		
    		int slotId = (Integer)rankQueue.get("slotId");
    		String searchKw = (String)rankQueue.get("searchKw");
    		String exposeKw = (String)rankQueue.get("exposeKw");
    		
    		
    		List<String> rankingList = searchRankingListFromNaver(searchKw);

    		int ranking = 0;
    		int rankTot = rankingList.size();
    		boolean isRankingSetting = false;
    		for (int i = 0; i < rankTot; i++) {
    			
    			ranking++;
    			
    			if( rankingList.get(i).equals( exposeKw)) {
    				isRankingSetting = true;
    				break;
    			}
			}
    		
    		if( !isRankingSetting) {
    			ranking = 0;
    		}
    		
    		SlotEntity entity = new SlotEntity();
    		entity.setSlotId(slotId);
    		entity.setRankTot(rankTot);
    		entity.setRanking(ranking);
    		
    		slotRepository.update(entity);
    		
    		
    		rankQueueMapper.deleteBySlotId(slotId);
		}
    	
    }



	private List<String> searchRankingListFromNaver(String searchKw) throws IOException {
		URL url = new URL("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=" + URLEncoder.encode(searchKw, "UTF-8"));
        
        // 문자열로 URL 표현
        System.out.println("URL :" + url.toExternalForm());
        
        // HTTP Connection 구하기 
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        
        // 요청 방식 설정 ( GET or POST or .. 별도로 설정하지않으면 GET 방식 )
        conn.setRequestMethod("GET"); 
        
        // 연결 타임아웃 설정 
        conn.setConnectTimeout(3000); // 3초 
        // 읽기 타임아웃 설정 
        conn.setReadTimeout(3000); // 3초
        
        
        // request header set 
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36"); 


        // 요청 방식 구하기
        System.out.println("getRequestMethod():" + conn.getRequestMethod());
        // 응답 콘텐츠 유형 구하기
        System.out.println("getContentType():" + conn.getContentType());
        // 응답 코드 구하기
        System.out.println("getResponseCode():"    + conn.getResponseCode());
        // 응답 메시지 구하기
        System.out.println("getResponseMessage():" + conn.getResponseMessage());
        
        
        // 응답 헤더의 정보를 모두 출력
        for (Map.Entry<String, List<String>> header : conn.getHeaderFields().entrySet()) {
            for (String value : header.getValue()) {
                System.out.println(header.getKey() + " : " + value);
            }
        }

        
        List<String> list = null;
        
        // 응답 내용(BODY) 구하기        
        try (InputStream in = conn.getInputStream();
                ByteArrayOutputStream out = new ByteArrayOutputStream()) {
        	byte[] buf = new byte[1024 * 8];
            int length = 0;
            while ((length = in.read(buf)) != -1) {
                out.write(buf, 0, length);
            }
            
            list = parsing( new String(out.toByteArray(), "UTF-8"));
        }
        
      // 접속 해제
      conn.disconnect();
      
      return list;
	}



	private List<Map<String, Object>> getDataSource() {
		return rankQueueMapper.selectRankQueueList();
	}

	private List<String> parsing(String text) {

		List<String> list = new ArrayList<String>();
		String resultString = null;
		Pattern regex = Pattern.compile("data-idx=\".+\" data-area=\"\\*.*<\\/a>");
		Matcher regexMatcher = regex.matcher(text);
		
		while (regexMatcher.find()) { 
			resultString = regexMatcher.group();
		}
		
		resultString = resultString.substring(0, resultString.indexOf("closed _related_keyword_closed"));
		
		regex = Pattern.compile("([ㄱ-ㅎㅏ-ㅣ가-힣]+\\s*)+");
		regexMatcher = regex.matcher(resultString);
		
		while (regexMatcher.find()) { 
			list.add( regexMatcher.group());
		}
		
		return list;
	}

} 

