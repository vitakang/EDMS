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
		String searchType = bean.getSearchType();
		
		//System.out.println(searchType);
		
		DocumentBean documentBean = new DocumentBean();
//		documentBean.setUSER_ID("vitakang");
		documentBean.setFOLDER_ID(bean.getFOLDER_ID());
		documentBean.setGROUP_ID("4");
		documentBean.setStartPage(String.valueOf(Integer.parseInt(page)*10-10));
		documentBean.setEndPage(String.valueOf(Integer.parseInt(page)*10));
		documentBean.setSearchText(bean.getSearchText());
		documentBean.setSearchType(bean.getSearchType());
		
		List<DocumentBean> list = documentDao.getDocumentList(documentBean);
		int maxPage = documentDao.selectDocumentPageCount(documentBean);
		int maxDocument = documentDao.selectCountDocument(documentBean);
		
		mv.addObject("startingPage", MainUtls.getStartpage(maxPage,Integer.parseInt(page)));
		mv.addObject("endPage", MainUtls.getEndpage(maxPage,Integer.parseInt(page)));
		mv.addObject("nowPage", Integer.parseInt(page));
		mv.addObject("maxPage", maxPage);
		mv.addObject("maxDocument", maxDocument);
		
		mv.addObject("d_list", list);
		mv.addObject("folderName", folderName);
		mv.addObject("folderId", folderId);
		mv.setViewName("content/listDocument");
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
		Map<String, String> dataMap = new HashMap<>();
		dataMap.put("userId", (String) paramMap.get("userId"));
		dataMap.put("userName", (String) paramMap.get("userName"));
		int errorCnt = 0;
		
		if (paramMap.get("favoriteName") instanceof List) {
			List<String> favoriteNameList = (ArrayList<String>) paramMap.get("favoriteName");
			List<String> favoriteDescriptionList = (ArrayList<String>) paramMap.get("favoriteDescription");
			List<String> docIdList = (ArrayList<String>) paramMap.get("docId");
			int size = favoriteNameList.size();
			
			for (int i = 0; i < size; i++) {
//				favoriteDescription = favoriteDescriptionList.get(i);
//				favoriteName = favoriteNameList.get(i);
//				
//				dataMap.put("favoriteName", favoriteName);
//				dataMap.put("favoriteDescription", favoriteDescription);
//				dataMap.put("docId", docIdList.get(i));
				if (!this.addFavoriteDocument(favoriteDescriptionList.get(i), favoriteNameList.get(i), docIdList.get(i), dataMap)) errorCnt++;
//				if (mainDao.insertFavoriteDocument(dataMap) <= 0 ) errorCnt++;
			}
		} else if (paramMap.get("favoriteName") instanceof String) {
//			favoriteName = (String) paramMap.get("favoriteName");
//			favoriteDescription = (String) paramMap.get("favoriteDescription");
//			docId = (String) paramMap.get("docId"); 
//			
//			dataMap.put("favoriteName", favoriteName);
//			dataMap.put("favoriteDescription", favoriteDescription);
//			dataMap.put("docId", docId);
//			
//			if (mainDao.insertFavoriteDocument(dataMap) <= 0 ) errorCnt++;
			
			if (!this.addFavoriteDocument((String) paramMap.get("favoriteDescription"), (String) paramMap.get("favoriteName"), (String) paramMap.get("docId"), dataMap)) errorCnt++;
		}
		
		return errorCnt > 0 ? "F" : "S";
	}
	
	private boolean addFavoriteDocument(String favoriteDescription, String favoriteName,String docId, Map<String, String> dataMap) {
		dataMap.put("favoriteName", favoriteName);
		dataMap.put("favoriteDescription", favoriteDescription);
		dataMap.put("docId", docId);
		
		if (mainDao.insertFavoriteDocument(dataMap) <= 0) return false;
		else return true;
	}

	@Override
	public void favoriteList(Map<String, String> paramMap, ModelAndView model) {
		List<Map<String, Object>> favoriteList = null;
		model.addObject("favoriteList", favoriteList);
		List<GroupBean> groupInFolderList = mainDao.selectGroupInFolderList();
		List<GroupBean> groupList = new ArrayList<>();
		int overCnt = 0;
		String pageStr = (String) paramMap.get("page");
		int page = 1;
		
		if (pageStr != null) {
			page = Integer.parseInt(pageStr);
		}
		
		paramMap.put("startIdx", String.valueOf(page * 10 - 10));
		paramMap.put("endIdx", String.valueOf(page * 10));
		
		favoriteList = mainDao.selectFavoriteList(paramMap);
		
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
		
		int maxPage = documentDao.selectFavoriteDocumentPageCount(paramMap);
		int maxDocument = documentDao.selectCountFavoriteDocument(paramMap);
		
		model.addObject("startingPage", MainUtls.getStartpage(maxPage, page));
		model.addObject("endPage", MainUtls.getEndpage(maxPage,page));
		model.addObject("nowPage", page);
		model.addObject("maxPage", maxPage);
		model.addObject("maxDocument", maxDocument);
		model.addObject("groupList", groupList);
		model.addObject("groupInFolderList", groupInFolderList);
		model.addObject("favoriteList", favoriteList);
	}

	@Override
	public String deleteFavorite(Map<String, Object> paramMap) {
		String[] docIdArr = ((String) paramMap.get("deleteStr")).split("[|]");
		Map<String, String> dataMap = new HashMap<>(); 
		int failCnt = 0;
		dataMap.put("userId", (String) paramMap.get("userId"));
		
		for (String docId : docIdArr) {
			dataMap.put("docId", docId);
			
			if (mainDao.deleteFavorite(dataMap) <= 0) failCnt++;  
		}
		
		return failCnt == 0 ? "S" : "F";
	}

	@Override
	public String documentDelete(HttpServletRequest request, HttpServletResponse response, HttpSession session, DocumentBean bean) {
		if(documentDao.documentDelete(bean) < 1) {
			
		}
		return null;
	}
	
	
	
}
