package co.kr.jcone.server.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import co.kr.jcone.server.bean.DocumentBean;
import co.kr.jcone.server.bean.GroupBean;
import co.kr.jcone.server.dao.DocumentDao;
import co.kr.jcone.server.dao.MainDao;
import co.kr.jcone.server.util.MainUtls;

@Service
public class MainServiceImpl implements MainService{
	
	@Autowired
	private MainDao mainDao;
	
	@Autowired
	private DocumentDao documentDao;
	
	@Override
	public ModelAndView mainPageView(HttpServletRequest request, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		List<GroupBean> groupInFolderList = mainDao.selectGroupInFolderList();
		List<GroupBean> groupList = new ArrayList<>();
		int overCnt = 0;
		String myTeam = "";
		
		for(int i = 0; i < groupInFolderList.size(); i++) {
			
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
		mv.addObject("groupList", groupList);
		mv.addObject("groupInFolderList", groupInFolderList);
		
		mv.addObject("myGroup", "4");
		mv.setViewName("main");
		return mv;
	}

	@Override
	public ModelAndView getListDocument(HttpServletRequest request, DocumentBean bean, HttpSession session) {
		ModelAndView mv = new ModelAndView();

		String folderName = bean.getFOLDER_NAME();
		String folderId = bean.getFOLDER_ID();
		String page = bean.getPage();
		
		DocumentBean documentBean = new DocumentBean();
//		documentBean.setUSER_ID("vitakang");
		documentBean.setFOLDER_ID(bean.getFOLDER_ID());
		documentBean.setGROUP_ID("4");
		documentBean.setStartPage(String.valueOf(Integer.parseInt(page)*10-10));
		documentBean.setEndPage(String.valueOf(Integer.parseInt(page)*10));
		
		List<DocumentBean> list = documentDao.getDocumentList(documentBean);
		int maxPage = documentDao.selectDocumentPageCount(documentBean);
		int maxDocument = documentDao.selectCountDocument(documentBean);
		
		mv.addObject("startingPage", MainUtls.getStartpage(maxPage,Integer.parseInt(page)));
		mv.addObject("endPage", MainUtls.getEndpage(maxPage,Integer.parseInt(page)));
		mv.addObject("nowPage", Integer.parseInt(page));
		mv.addObject("maxPage", maxPage);
		mv.addObject("maxDocument", maxDocument);
		
		System.out.println(maxPage);
		
		
		mv.addObject("d_list", list);
		mv.addObject("folderName", folderName);
		mv.addObject("folderId", folderId);
		mv.setViewName("content/listDocument");
		// todo
		// 문서 목록
		return mv;
	}

	@Override
	public String download(HttpServletRequest request, HttpServletResponse response, HttpSession session, DocumentBean bean) {
		
		String documentFileId = bean.getDOCUMENT_FILE_ID();
		
		response.setHeader("Cache-Control", "max-age=0");

		if (documentFileId == null || "".equals(documentFileId)) {
			return "fail"; 
		} else {

			String fullPath = documentDao.getFileOriginalPath(bean);

			File file = new File(fullPath);

			if (!file.exists()) {
				return "fail"; 
			} else {

				FileInputStream fin = null;
				ServletOutputStream sout = null;

				try {
					int len = (int) file.length();
					fin = new FileInputStream(file);
					byte[] data = new byte[len];
					fin.read(data);

					response.setContentType("multipart/form-data;boundary=dkjseu40f9844djs8dviwdf;charset=UTF-8");
					response.setHeader("Content-Transfer-Encoding", "base64");
					response.setHeader("Content-Disposition", "attachment;filename=" + documentFileId + ";");
					response.setHeader("Content-Length", String.valueOf(len));

					sout = response.getOutputStream();
					sout.write(data, 0, len);
					sout.flush();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (sout != null) {
						try {
							sout.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (fin != null) {
						try {
							fin.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		return "ok";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String addFavoriteDocument(Map<String, Object> paramMap) {
		List<String> favoriteNameList = (ArrayList<String>) paramMap.get("favoriteName");
		List<String> favoriteDescriptionList = (ArrayList<String>) paramMap.get("favoriteDescription");
		List<String> docIdList = (ArrayList<String>) paramMap.get("docId");
//		List<String> mapInList = null;
		int size = favoriteNameList.size();
		String favoriteName = null;
		String favoriteDescription = null;
		Map<String, String> dataMap = new HashMap<>();
		int errorCnt = 0;
		
		dataMap.put("userId", (String) paramMap.get("userId"));
		dataMap.put("userName", (String) paramMap.get("userName"));
//		dataMap.put("objectId", UUID.randomUUID().toString());
		
//		Iterator<String> keys = paramMap.keySet().iterator();
//		String key = null;
		
//		while (keys.hasNext()) {
//			key = keys.next();
//			mapInList = (ArrayList<String>) paramMap.get(key);
//			size = mapInList.size();
			
			for (int i = 0; i < size; i++) {
				favoriteDescription = favoriteDescriptionList.get(i);
				favoriteName = favoriteNameList.get(i);
				dataMap.put("favoriteName", favoriteName);
				dataMap.put("favoriteDescription", favoriteDescription);
				dataMap.put("docId", docIdList.get(i));
				
				if (mainDao.insertFavoriteDocument(dataMap) <= 0 ) errorCnt++;
			}
//		}
		
		
		return errorCnt > 0 ? "F" : "S";
	}

	@Override
	public void favoriteList(String userId, ModelAndView model) {
		List<Map<String, Object>> favoriteList = mainDao.selectFavoriteList(userId);
		model.addObject("favoriteList", favoriteList);
	}
}
