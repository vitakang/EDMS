package co.kr.jcone.server.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.kr.jcone.server.bean.DocumentBean;
import co.kr.jcone.server.service.DocumentService;
import co.kr.jcone.server.service.MainService;
import co.kr.jcone.server.util.CommonUtil;

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
		
		return documentService.insertDocument(request, session);
	}

	@RequestMapping(value = "/modifyDocument")
	public ModelAndView modifyDocument(HttpServletRequest request, HttpSession session, @ModelAttribute DocumentBean bean) {
		
		return documentService.modifyDocument(request, session, bean);
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
	
	@RequestMapping(value = "/teamFolderInsert")
	public ModelAndView teamFolderInsert(HttpServletRequest request, @ModelAttribute DocumentBean bean, HttpSession session) {
		
		return documentService.teamFolderInsert(request, bean, session);
	}
	
	@RequestMapping(value = "/teamFolderManager")
	public ModelAndView teamFolderManager(HttpServletRequest request, @ModelAttribute DocumentBean bean, HttpSession session) {
		
		return documentService.teamFolderManager(request, bean, session);
	}

	@ResponseBody
	@RequestMapping(value = "/insertFolder")
	public String insertFolder(HttpServletRequest request, @ModelAttribute DocumentBean bean, HttpSession session) {
		
		return documentService.insertFolder(request, bean, session);
	}

	@RequestMapping(value = "/setting")
	public ModelAndView setting(HttpServletRequest request, HttpSession session) {
		
		return mainService.settingPage(request, session);
	}
	
	@RequestMapping(value = "/changePwd")
	public String changePwd(HttpServletRequest request, HttpSession session, @ModelAttribute DocumentBean bean) {
		
		return mainService.changePwd(request, session, bean);
	}
	
	@ResponseBody
	@RequestMapping("/documentDelete")
	public String documentDelete(HttpServletRequest request, HttpServletResponse response, HttpSession session, @ModelAttribute DocumentBean bean) {
		return mainService.documentDelete(request, response, session, bean);
	}
	
	@ResponseBody
	@RequestMapping(value="download")
	public void download(HttpServletRequest request, HttpServletResponse response, HttpSession session, @ModelAttribute DocumentBean bean) {
		mainService.download(request, response, session, bean);
		//return 
	}
	
	@RequestMapping(value = "favoriteList")
	public ModelAndView favoriteList(HttpServletRequest request, @RequestParam Map<String, String> paramMap) {
		ModelAndView model = new ModelAndView("content/favoriteList");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String groupId = (String) session.getAttribute("groupId");
		paramMap.put("userId", userId);
		paramMap.put("groupId", groupId);
		model.addObject("myGroup", groupId);
		
		mainService.favoriteList(paramMap, model);
		
		return model;
	}

	@RequestMapping(value = "addFavorite")
	@ResponseBody
	public String addFavorite(HttpServletRequest request) {
		Map<String, Object> paramMap = CommonUtil.getMap(request);
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		String userId = (String) session.getAttribute("userId");
		
		paramMap.put("userName", userName);
		paramMap.put("userId", userId);
		
		return mainService.addFavoriteDocument(paramMap);
	}
	
	@RequestMapping(value = "deleteFavorite")
	@ResponseBody
	public String deleteFavorite(HttpServletRequest request) {
		Map<String, Object> paramMap = CommonUtil.getMap(request);
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		
		paramMap.put("userId", userId);
		
		return mainService.deleteFavorite(paramMap);
	}
	
}
