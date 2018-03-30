package co.kr.jcone.server.service;

import java.text.SimpleDateFormat;
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
		List<DocumentBean> list = new ArrayList<>();
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		for(int i = 0; i < 10; i++) {
			cal.add(Calendar.DATE, i);
			DocumentBean db = new DocumentBean();
			db.setDOCUMENT_ID(""+i+1);
			db.setGROUP_NAME("그룹"+i);
			db.setBIND_TITLE("섭타"+i);
			db.setDOCUMENT_TITLE("메인제목ㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱ"+i);
			db.setUSER_ID("작성"+i);
			db.setREGISTER_DATE(dateFormat.format(cal.getTime()));
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
