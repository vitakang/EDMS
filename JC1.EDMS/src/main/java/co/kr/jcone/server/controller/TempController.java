package co.kr.jcone.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kr.jcone.server.bean.DocumentBean;
import co.kr.jcone.server.service.TempService;

@Controller
public class TempController {
	
	@Autowired
	TempService tempService;
	
	@ResponseBody
	@RequestMapping(value = "/teamFolderDelete")
	public String teamFolderDelete(HttpServletRequest request, HttpSession session, @ModelAttribute DocumentBean bean) {
		
		return tempService.teamFolderDelete(request, session, bean);
	}
}
