package co.kr.jcone.server.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.kr.jcone.server.controller.MainController;

public class MainUtls {
	
	private static final Logger logger = LoggerFactory.getLogger(MainUtls.class);
	
	public static String changeTextUTF8(String text) throws Exception{
		return new String(text.getBytes("8859_1"),"UTF-8");
	}
	
	public static String changeSecurityGradeCode(String SecurityString) {

		String resultCode = "";
		
		logger.debug("************** SecurityString : " + SecurityString);
		
		if(SecurityString != null) {
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
		}
		
		return "FAIL";
	}

}
