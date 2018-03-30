package co.kr.jcone.server.dao;

import java.util.List;
import java.util.Map;

public interface LoginDao {
	
	abstract public int loginChceck(Map<String, String> paramMap);
	abstract public List<Map<String, String>> getList(); 
}
