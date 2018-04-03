package co.kr.jcone.server.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import co.kr.jcone.server.bean.DocumentBean;
import co.kr.jcone.server.bean.GroupBean;
import co.kr.jcone.server.dao.MainDao;

@Service
public class MainServiceImpl implements MainService{
	
	@Autowired
	private MainDao mainDao;
	
	@Override
	public ModelAndView mainPageView(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		List<GroupBean> groupInFolderList = mainDao.selectGroupList();
		List<GroupBean> groupList = new ArrayList<>();
		int overCnt = 0;
		
		for(int i = 0; i < groupInFolderList.size(); i++) {
			
//			System.out.println(" test : " + groupInFolderList.get(i));
//			System.out.println(" test2 : " + groupList.contains(groupInFolderList.get(i)));
			
			if (groupList.size() <= 0) {
				groupList.add(groupInFolderList.get(i));
			} else {
				for (int j = 0; j < groupList.size(); j++) {
					if (groupList.get(j).getGroup_id().equals(groupInFolderList.get(i).getGroup_id())) {
						overCnt++;
					}
				}
				
				if (overCnt == 0) groupList.add(groupInFolderList.get(i));
				else overCnt = 0;
			}
		}
		
//		for(int i = 0; i < groupInFolderList.size(); i++) {
//			groupList.add(groupInFolderList.get(i));
//		}
//		for(int i=0; i<groupList.size();i++) {
//			for(int j = 0; j<groupList.size();j++) {
//				if(groupList.get(i).equals(groupList.get(j))) {
//					groupList.remove(i);
//				}
//			}
//		}
		
		mv.addObject("groupList", groupList);
		mv.addObject("groupInFolderList", groupInFolderList);
		
		mv.addObject("myGroup", "4");
		mv.setViewName("main");
		return mv;
	}

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
