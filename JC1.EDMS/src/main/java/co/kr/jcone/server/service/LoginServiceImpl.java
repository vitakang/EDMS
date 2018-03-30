package co.kr.jcone.server.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.kr.jcone.server.dao.LoginDao;

@Service
public class LoginServiceImpl implements LoginService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private LoginDao loginDAO;

	@Override
	public String login(Map<String, String> paramMap) {
		int result = loginDAO.loginChceck(paramMap);
		List<Map<String, String>> list = loginDAO.getList();
		return result > 0 ? "S" : "F";
	}

}
