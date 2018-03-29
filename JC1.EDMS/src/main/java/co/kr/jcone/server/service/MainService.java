package co.kr.jcone.server.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import co.kr.jcone.server.bean.DocumentBean;

public interface MainService {

	ModelAndView getListDocument(HttpServletRequest request, DocumentBean bean);

}
