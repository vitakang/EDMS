package co.kr.jcone.server.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

public interface LoginService {

	abstract public String login(Map<String, String> paramMap, HttpSession httpSession);
}
