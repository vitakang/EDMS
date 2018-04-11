package co.kr.jcone.server.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import co.kr.jcone.server.bean.DocumentBean;

public interface MainService {

	ModelAndView getListDocument(HttpServletRequest request, DocumentBean bean, HttpSession session);

	ModelAndView mainPageView(HttpServletRequest request, HttpSession session);

	String download(HttpServletRequest request, HttpServletResponse response, HttpSession session, DocumentBean bean);
	
	abstract public String addFavoriteDocument(Map<String, Object> paramMap);

	abstract public void favoriteList(Map<String, String> paramMap, ModelAndView model);

	abstract public String deleteFavorite(Map<String, Object> paramMap);

	String documentDelete(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			DocumentBean bean);

}
