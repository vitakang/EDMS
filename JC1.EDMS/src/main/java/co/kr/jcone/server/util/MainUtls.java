package co.kr.jcone.server.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainUtls {
	
	private static final Logger logger = LoggerFactory.getLogger(MainUtls.class);
	
	public static String changeTextUTF8(String text) throws Exception{
		return new String(text.getBytes("8859_1"),"UTF-8");
	}
	
	/**
	 * 		type
	 * 				1 : String -> int
	 * 				2 : int -> String
	 * */
	public static String changeSecurityGradeCode(String SecurityString, String type) {

		String resultCode = "";
		
		logger.debug("************** SecurityString : " + SecurityString);
		
		if(SecurityString != null) {
			
			if("1".equals(type)) {
				if("전체공개".equals(SecurityString)) {
					resultCode = "0";
					return resultCode;
				}else if("부분공개".equals(SecurityString)) {
					resultCode = "1";
					return resultCode;
				}else if("팀공개".equals(SecurityString)) {
					resultCode = "2";
					return resultCode;
				}else if("비공개".equals(SecurityString)) {
					resultCode = "3";
					return resultCode;
				}
			}else if("2".equals(type)) {
				if("0".equals(SecurityString)) {
					resultCode = "전체공개";
					return resultCode;
				}else if("1".equals(SecurityString)) {
					resultCode = "부분공개";
					return resultCode;
				}else if("2".equals(SecurityString)) {
					resultCode = "팀공개";
					return resultCode;
				}else if("3".equals(SecurityString)) {
					resultCode = "비공개";
					return resultCode;
				}
			}
			
		}
		
		return "FAIL";
	}
	
	public static int getStartpage(int pageCnt, int nowPage) {
		
		int startPage = 0;
		
		if(pageCnt <= 10) {
			startPage = 1;
		} else {
			startPage = ((int) (Math.floor(nowPage / 10) * 10) + 1);
		}
		
		return startPage;
	}
	
	// pageCnt	총페이지 39
	// nowPage	지금 페이지 27
	
	public static int getEndpage(int pageCnt, int nowPage) {
		
		int endPage = 0;
		
		// 마지막페이지의 페이징 첫번쨰 ( ex_ 39페이지 까지 있다면  31반환 )
		int middleEndPage = ((int) (Math.floor(pageCnt / 10) * 10) + 1); 
		// 현재페이지의 마지막
		int middlePageLast = ((int) (Math.floor(nowPage / 10) * 10) + 10);  
		
		if(pageCnt <= 10) {
			endPage = pageCnt;
		} else if(middleEndPage > nowPage) {
			endPage = middlePageLast;
		} else {
			endPage = pageCnt;
		}
		return endPage;
	}
	
}
