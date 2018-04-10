package co.kr.jcone.server.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.kr.jcone.server.service.LoginService;

@Controller
//@RequestMapping("login")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired private LoginService loginService;

	@RequestMapping(value = "loginView")
	public ModelAndView loginView(Locale locale, Model model) {
		
		return new ModelAndView("login");
	}

	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public @ResponseBody String login(@RequestParam Map<String, String> paramMap, HttpServletRequest request) {
		String returnMsg = loginService.login(paramMap, request.getSession());
		return returnMsg;
	}
	
	@RequestMapping(value = "logout", method = { RequestMethod.POST })
	public @ResponseBody String logout(@RequestParam Map<String, String> paramMap, HttpServletRequest request) {
		String returnMsg = loginService.logout(paramMap, request.getSession());
		return returnMsg;
	}
}
