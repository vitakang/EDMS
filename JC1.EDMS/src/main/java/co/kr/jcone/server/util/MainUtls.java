package co.kr.jcone.server.util;

public class MainUtls {
	
	public static String changeTextUTF8(String text) throws Exception{
		return new String(text.getBytes("8859_1"),"UTF-8");
	}

}
