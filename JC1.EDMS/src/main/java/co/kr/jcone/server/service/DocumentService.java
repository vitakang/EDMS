package co.kr.jcone.server.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import co.kr.jcone.server.bean.DocumentBean;

public interface DocumentService{

	String uploadDocument(HttpServletRequest request, DocumentBean bean, HttpSession session);

	ModelAndView viewDetail(HttpServletRequest request, DocumentBean bean, HttpSession session);

	ModelAndView teamFolderInsert(HttpServletRequest request, DocumentBean bean, HttpSession session);

	ModelAndView teamFolderManager(HttpServletRequest request, DocumentBean bean, HttpSession session);

	String insertFolder(HttpServletRequest request, DocumentBean bean, HttpSession session);

}
