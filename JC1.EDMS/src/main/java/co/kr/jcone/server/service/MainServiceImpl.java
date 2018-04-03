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
import co.kr.jcone.server.dao.DocumentDao;
import co.kr.jcone.server.dao.MainDao;

@Service
public class MainServiceImpl implements MainService{
	
	@Autowired
	private MainDao mainDao;
	
	@Autowired
	private DocumentDao documentDao;
	
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

		String folderName = bean.getFOLDER_NAME();	
		
		DocumentBean documentBean = new DocumentBean();
//		documentBean.setUSER_ID("vitakang");
		documentBean.setFOLDER_ID(bean.getFOLDER_ID());
		documentBean.setGROUP_ID("4");
		
		List<DocumentBean> list = documentDao.getDocumentList(documentBean);

		mv.addObject("d_list", list);
		mv.setViewName("content/listDocument");
		mv.addObject("folderName", folderName);
		// todo
		// 문서 목록
		return mv;
	}

}
