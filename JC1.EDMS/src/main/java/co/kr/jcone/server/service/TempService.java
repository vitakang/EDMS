package co.kr.jcone.server.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import co.kr.jcone.server.bean.DocumentBean;

public interface TempService{

	String teamFolderDelete(HttpServletRequest request, HttpSession session, DocumentBean bean);

}
