package co.kr.jcone.server.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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
	public ModelAndView main(Locale locale, Model model) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("main");
		// todo
		// 메인 페이지
		
		return mv;
	}

	@RequestMapping(value = "/insertDocument")
	public ModelAndView insertDocument(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("content/insertDocument");
		// todo
		// 문서 등록  
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/uploadDocument")
	public String uploadDocument(HttpServletRequest request, @ModelAttribute DocumentBean bean) {
		
		return documentService.uploadDocument(request,bean);
	}
	
	@RequestMapping(value = "/listDocument")
	public ModelAndView listDocument(HttpServletRequest request, @ModelAttribute DocumentBean bean,
			@RequestParam("title") String title,@RequestParam("sub") String sub) {

		
		return mainService.getListDocument(request, bean);
		
	}
	

	@RequestMapping(value = "/viewDetail")
	public ModelAndView viewDetail(HttpServletRequest request, @RequestParam("idx") String idx,@RequestParam("title") String title) {
		
		ModelAndView mv = new ModelAndView();
		
		System.out.println(idx);
		System.out.println("viewDetail : "+ title);
		mv.addObject("title",title);
		mv.setViewName("content/viewDetail");
		
		return mv;
		
	}

	@RequestMapping(value = "/setting")
	public ModelAndView setting(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("content/setting");
		// todo
		// 메인 페이지
		
		return mv;
	}
	

}
