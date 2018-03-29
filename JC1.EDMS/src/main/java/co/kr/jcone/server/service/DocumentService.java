package co.kr.jcone.server.service;

import javax.servlet.http.HttpServletRequest;

import co.kr.jcone.server.bean.DocumentBean;

public interface DocumentService{

	String uploadDocument(HttpServletRequest request, DocumentBean bean);

}
