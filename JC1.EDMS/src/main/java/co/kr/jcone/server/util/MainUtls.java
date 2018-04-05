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
			
			if("1".equals(SecurityString)) {
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
			}else if("2".equals(SecurityString)) {
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
	
}
