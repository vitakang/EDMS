package co.kr.jcone.server.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import co.kr.jcone.server.bean.DocumentBean;

@Service
public class MainServiceImpl implements MainService{

	@Override
	public ModelAndView getListDocument(HttpServletRequest request, DocumentBean bean) {
		ModelAndView mv = new ModelAndView();

		String title = bean.getTitle();	
		System.out.println(title);
		
		mv.addObject("title", title);
		
		List<DocumentBean> list = new ArrayList<>();
		Calendar cal = new GregorianCalendar();

		for(int i = 0; i < 10; i++) {
			cal.add(Calendar.DATE, i);
			DocumentBean db = new DocumentBean();
			db.setIdx(i+1);
			db.setGroup("그룹"+i);
			db.setSubTitle("섭타"+i);
			db.setTitle("메인제목ㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱ"+i);
			db.setWriter("작성"+i);
			db.setWriteDate(cal.getTime());
			list.add(db);
		}
		
		mv.addObject("d_list", list);
		mv.setViewName("content/listDocument");
		mv.addObject("table_title", title);
		// todo
		// 문서 목록
		return mv;
	}

}
