package co.kr.jcone.server.controller;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import co.kr.jcone.server.bean.DocumentBean;
import co.kr.jcone.server.service.MainService;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = "/")
	public ModelAndView login(Locale locale, Model model) {
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("login");
		// todo
		// 로그인 페이지
		
		
		return mv;
	}

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
	public String uploadDocument(HttpServletRequest request,
			@RequestParam(value = "files[]", required = false) MultipartFile[] mfl) {
		
		System.out.println(mfl);
		System.out.println(mfl.length);
		
		return "asdf";
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
