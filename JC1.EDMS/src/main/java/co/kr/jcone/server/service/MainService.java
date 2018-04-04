package co.kr.jcone.server.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import co.kr.jcone.server.bean.DocumentBean;

public interface MainService {

	ModelAndView getListDocument(HttpServletRequest request, DocumentBean bean, HttpSession session);

	ModelAndView mainPageView(HttpServletRequest request, HttpSession session);

	String download(HttpServletRequest request, HttpServletResponse response, HttpSession session, DocumentBean bean);

}
