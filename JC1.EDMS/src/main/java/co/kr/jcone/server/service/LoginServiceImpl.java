package co.kr.jcone.server.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.kr.jcone.server.dao.LoginDao;

@Service
public class LoginServiceImpl implements LoginService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private @Autowired LoginDao loginDAO;

	@Override
	public String login(Map<String, String> paramMap, HttpSession httpSession) {
		int result = loginDAO.loginChceck(paramMap);
		String resultMsg = null;
		String userId = paramMap.get("email");
		
		if (result > 0) {
			httpSession.setAttribute("userId", userId);
			resultMsg = "S";
		} else {
			resultMsg = "F";
		}
		
		logger.info("[{}] - LOGIN RESULT => {}", userId, resultMsg);
		
		return resultMsg;
	}

}
