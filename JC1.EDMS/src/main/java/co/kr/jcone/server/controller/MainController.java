package co.kr.jcone.server.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.kr.jcone.server.bean.DocumentBean;
import co.kr.jcone.server.service.DocumentService;
import co.kr.jcone.server.service.MainService;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private DocumentService documentService;
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "/main")
	public ModelAndView main(HttpServletRequest request, HttpSession session) {
		
		return mainService.mainPageView(request, session);
	}

	@RequestMapping(value = "/insertDocument")
	public ModelAndView insertDocument(HttpServletRequest request, HttpSession session) {

		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("content/insertDocument");
		// todo
		// 문서 등록  
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/uploadDocument")
	public String uploadDocument(HttpServletRequest request, @ModelAttribute DocumentBean bean, HttpSession session) {
		
		return documentService.uploadDocument(request, bean, session);
	}
	
	@RequestMapping(value = "/listDocument")
	public ModelAndView listDocument(HttpServletRequest request, @ModelAttribute DocumentBean bean, HttpSession session) {

		
		return mainService.getListDocument(request, bean, session);
		
	}
	

	@RequestMapping(value = "/viewDetail")
	public ModelAndView viewDetail(HttpServletRequest request, @ModelAttribute DocumentBean bean, HttpSession session) {
		
		return documentService.viewDetail(request, bean, session);
		
	}

	@RequestMapping(value = "/setting")
	public ModelAndView setting(HttpServletRequest request, HttpSession session) {

		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("content/setting");
		// todo
		// 메인 페이지
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="download")
	public String download(HttpServletRequest request, HttpServletResponse response, HttpSession session, @ModelAttribute DocumentBean bean) {
		
		return mainService.download(request, response, session, bean);
	}

}
