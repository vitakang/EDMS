package co.kr.jcone.server.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import co.kr.jcone.server.bean.DocumentBean;
import co.kr.jcone.server.bean.GroupBean;
import co.kr.jcone.server.bean.PathBean;
import co.kr.jcone.server.dao.DocumentDao;
import co.kr.jcone.server.dao.MainDao;
import co.kr.jcone.server.util.MainUtls;

@Service
public class DocumentServiceImpl implements DocumentService{

	private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

	@Autowired
	private DocumentDao documentDao;
	
	@Autowired
	private MainDao mainDao;
	
	@Override
	public ModelAndView insertDocument(HttpServletRequest request, HttpSession session) {
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("content/insertDocument");

		String userId = (String) session.getAttribute("userId");
		String groupId = (String) session.getAttribute("groupId");
		System.out.println(userId + groupId);
		mv.addObject("myGroup", groupId);
		
		List<GroupBean> groupInFolderList = mainDao.selectGroupInFolderList(groupId);

		mv.addObject("groupList", MainUtls.getGroupList(groupInFolderList));
		mv.addObject("groupInFolderList", groupInFolderList);
		
		return mv;
	}

	@Override
	public ModelAndView modifyDocument(HttpServletRequest request, HttpSession session, DocumentBean bean) {
		ModelAndView mv = new ModelAndView();
		
		try {
			mv.setViewName("content/modifyDocument");

			String userId = (String) session.getAttribute("userId");
			String groupId = (String) session.getAttribute("groupId");
			System.out.println(userId + groupId);
			mv.addObject("myGroup", groupId);
			
			List<GroupBean> groupInFolderList = mainDao.selectGroupInFolderList(groupId);
			
			String documentId = bean.getDOCUMENT_ID();
			documentId = MainUtls.changeTextUTF8(documentId);
			DocumentBean documentBean = documentDao.viewDetail(documentId);
			List<DocumentBean> documentFileBean = documentDao.selectFileListFromDocumentId(documentId);

			documentBean.setSECURITY_GRADE(MainUtls.changeSecurityGradeCode(documentBean.getSECURITY_GRADE(),"2"));
			mv.addObject("folderName",MainUtls.changeTextUTF8(bean.getFOLDER_NAME()));
			mv.addObject("folderId",MainUtls.changeTextUTF8(bean.getFOLDER_ID()));
			mv.addObject("documentBean",documentBean);
			mv.addObject("fileList",documentFileBean);
			mv.addObject("userId", userId);
			mv.addObject("myGroup", groupId);
			mv.addObject("nowPage", bean.getPage());
			mv.addObject("groupList", MainUtls.getGroupList(groupInFolderList));
			mv.addObject("groupInFolderList", groupInFolderList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
		
	}



	@Override
	public String uploadDocument(HttpServletRequest request, DocumentBean bean, HttpSession session) {
		
		// ID를 위한 날짜 (밀리세컨드)
	 	Date today = new Date();
	    
	 	SimpleDateFormat date = new SimpleDateFormat("MMddHHmmssSSS");
	    SimpleDateFormat dateFilePathFormat = new SimpleDateFormat("yyyy/MMdd");

	    String dateMiliSecond = date.format(today);
	    String dateFilePath = dateFilePathFormat.format(today);

		try {
			
			MultipartFile[] multiPartUploadFile = bean.getMultiPartFiles();
			
			String bindTitle = MainUtls.changeTextUTF8(bean.getBIND_TITLE());
			String documentTitle = MainUtls.changeTextUTF8(bean.getDOCUMENT_TITLE());
			String securityCode = MainUtls.changeSecurityGradeCode(MainUtls.changeTextUTF8(bean.getSECURITY_GRADE()),"1");
			String oldDocumentId = bean.getDOCUMENT_ID();
			String newDocumentId = documentTitle + "_" + dateMiliSecond;

			String userId = (String) session.getAttribute("userId");
			String userName = (String) session.getAttribute("userName");
			String groupId = (String) session.getAttribute("groupId");
			String groupName = (String) session.getAttribute("groupName");
			
			System.out.println(oldDocumentId);
			
			if(oldDocumentId != null && !"".equals(oldDocumentId) && !oldDocumentId.isEmpty()) {

				if(documentDao.documentDelete(bean) < 1) {
					logger.info("documentDao.documentDelete(bean) FAIL");	
					return "fail";			
				}
			}
			
			String isEdit = bean.getUpdateType();
			
			DocumentBean document = new DocumentBean();
			// 철ID
			document.setBIND_ID(bindTitle + "_" + dateMiliSecond);
			// 철제목
			document.setBIND_TITLE(bindTitle);
			// 문서 아이디
			document.setDOCUMENT_ID(newDocumentId);
			// 문서 제목
			document.setDOCUMENT_TITLE(documentTitle);
			// 문서 설명
			document.setDOCUMENT_DESCRIPTION(MainUtls.changeTextUTF8(bean.getDOCUMENT_DESCRIPTION()));
			// 보안등급
			document.setSECURITY_GRADE(securityCode);
			// 등록일
			document.setREGISTER_DATE(bean.getREGISTER_DATE());
			// 버전 (최초 1.0)
			if("edit".equals(isEdit)) {
				String version = bean.getVERSION();
				Float versionFloat = (float) (Float.parseFloat(version)+0.1);
				
				document.setVERSION(String.valueOf(versionFloat));
			} else {
				document.setVERSION("1.0");
			}
			// 사용자 아이디 셋팅
			document.setUSER_ID(userId);
			// 사용자 이름
			document.setUSER_NAME(userName);
			// 그룹 아이디
			document.setGROUP_ID(groupId);
			// 그룹이름
			document.setGROUP_NAME(groupName);
			// 삭제유형 DEFAULT = 0
			
			if(documentDao.insertDocument(document) < 1) {
				logger.info("documentDao.insertDocument(document) FAIL");	
				return "fail";			
			}
			
			
			if(multiPartUploadFile != null) {
				DocumentBean documentFileBean = new DocumentBean();
				
				for(int i = 0; i < bean.getMultiPartFiles().length; i++) {
					MultipartFile mFile = bean.getMultiPartFiles()[i];
					byte[] bytes = mFile.getBytes();
					String fileName = MainUtls.changeTextUTF8(mFile.getOriginalFilename());
					String uploadPath = PathBean.PATH_UPLOADFILE;
					
					File pathFile = new File(uploadPath + dateFilePath);
					
					if(!pathFile.exists()) {
						pathFile.mkdirs();
					}
					
					uploadPath = uploadPath + dateFilePath + File.separator + dateMiliSecond + "_" + fileName;

					System.out.println(PathBean.PATH_UPLOADFILE);
					
					documentFileBean.setDOCUMENT_FILE_ID(fileName + dateMiliSecond);
					documentFileBean.setDOCUMENT_ID(newDocumentId);
					documentFileBean.setORIGINAL_FILE_NAME(fileName);
					documentFileBean.setFILE_PATH(uploadPath);
					documentFileBean.setFILE_ORDER(i);
					documentFileBean.setFILE_SIZE((int) mFile.getSize());
					
					File file = new File(uploadPath);
					
					OutputStream out = null;
					PrintWriter pw = null;
					
					try {
						out = new FileOutputStream(file);
						
						out.write(bytes);
						out.flush();
					} catch (Exception e) {
						e.printStackTrace();
						return "fail";
					}finally {
						if(out != null) {
							out.close();
						}
						if(pw != null) {
							pw.close();
						}
					}
					
					if(documentDao.insertDocumentFile(documentFileBean) < 1) {
						logger.info("documentDao.insertDocumentFile(documentFileBean) FAIL");	
						return "fail";
					}
				}
			}
			
			DocumentBean documentVersion = new DocumentBean();
			documentVersion.setDOCUMENT_ID(newDocumentId);
			if("edit".equals(isEdit)) {
				String version = bean.getVERSION();
				Float versionFloat = (float) (Float.parseFloat(version)+0.1);
				
				documentVersion.setVERSION(String.valueOf(versionFloat));
				documentVersion.setMODIFY_REASON("수정");
				documentVersion.setUSER_ID(userId);
				documentVersion.setUSER_NAME(userName);
				documentVersion.setORIGINAL_DOCUMENT_ID(oldDocumentId);
			} else {
				documentVersion.setVERSION("1.0");
				documentVersion.setMODIFY_REASON("최초");
				documentVersion.setUSER_ID(userId);
				documentVersion.setUSER_NAME(userName);
				
			}
			
			//documentVersion.setORIGINAL_DOCUMENT_ID(oRIGINAL_DOCUMENT_ID);
			if(documentDao.insertDocumentVersion(documentVersion) < 1) {
				logger.info("documentDao.insertDocumentVersion(documentVersion) FAIL");
				return "fail";
			}
			
			DocumentBean documentFolder = new DocumentBean();
			documentFolder.setDOCUMENT_ID(newDocumentId);
			documentFolder.setFOLDER_ID(MainUtls.changeTextUTF8(bean.getFOLDER_ID()));
			
			if(documentDao.insertDocumentFolder(documentFolder) < 1) {
				logger.info("documentDao.insertDocumentFolder(documentFolder) FAIL");
				return "fail";
			}			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
		return "success";
	}

	@Override
	public ModelAndView viewDetail(HttpServletRequest request, DocumentBean bean, HttpSession session) {

		ModelAndView mv = new ModelAndView();
		
		try {
			String documentId = bean.getDOCUMENT_ID();
			documentId = MainUtls.changeTextUTF8(documentId);
			DocumentBean documentBean = documentDao.viewDetail(documentId);
			List<DocumentBean> documentFileBean = documentDao.selectFileListFromDocumentId(documentId);

			String userId = (String) session.getAttribute("userId");
			String userName = (String) session.getAttribute("userName");
			String groupId = (String) session.getAttribute("groupId");
			
			List<GroupBean> groupInFolderList = mainDao.selectGroupInFolderList(groupId);
			
			DocumentBean historyBean = new DocumentBean();
			historyBean.setDOCUMENT_ID(documentBean.getDOCUMENT_ID());
			historyBean.setUSER_ID(userId);
			historyBean.setUSER_NAME(userName);
			
			if(documentDao.updateDocumentReadHistory(historyBean) < 1) {
				logger.info("documentDao.updateDocumentReadHistory(historyBean) FAIL");
			}
			
			System.out.println(bean.getPage());
			System.out.println(MainUtls.changeTextUTF8(bean.getFOLDER_NAME()));
			documentBean.setSECURITY_GRADE(MainUtls.changeSecurityGradeCode(documentBean.getSECURITY_GRADE(),"2"));
			mv.addObject("folderName",MainUtls.changeTextUTF8(bean.getFOLDER_NAME()));
			mv.addObject("folderId",MainUtls.changeTextUTF8(bean.getFOLDER_ID()));
			mv.addObject("documentBean",documentBean);
			mv.addObject("fileList",documentFileBean);
			mv.addObject("groupList", MainUtls.getGroupList(groupInFolderList));
			mv.addObject("groupInFolderList", groupInFolderList);
			mv.addObject("userId", userId);
			mv.addObject("myGroup", groupId);
			mv.addObject("nowPage", bean.getPage());
			mv.setViewName("content/viewDetail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}

	@Override
	public ModelAndView teamFolderInsert(HttpServletRequest request, DocumentBean bean, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		List<DocumentBean> groupFolderList = documentDao.selectGroupFolderList(bean);

		String userId = (String) session.getAttribute("userId");
		String groupId = (String) session.getAttribute("groupId");
		String groupName = (String) session.getAttribute("groupName");
		
		List<GroupBean> groupInFolderList = mainDao.selectGroupInFolderList(groupId);

		mv.addObject("groupList", MainUtls.getGroupList(groupInFolderList));
		mv.addObject("groupInFolderList", groupInFolderList);
		mv.addObject("userId", userId);
		mv.addObject("myGroup", groupId);
		
		mv.addObject("groupName", groupName);
		mv.addObject("groupFolderList", groupFolderList);
		mv.setViewName("content/teamFolderInsert");
		
		return mv;
	}

	@Override
	public ModelAndView teamFolderManager(HttpServletRequest request, DocumentBean bean, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		String folderName = bean.getFOLDER_NAME();
		String folderId = bean.getFOLDER_ID();
		String page = bean.getPage();

		String userId = (String) session.getAttribute("userId");
		String groupId = (String) session.getAttribute("groupId");
		
		List<GroupBean> groupInFolderList = mainDao.selectGroupInFolderList(groupId);
		
	    bean.setGROUP_ID(groupId);
	    bean.setStartPage(String.valueOf(Integer.parseInt(page)*10-10));
	    bean.setEndPage(String.valueOf(Integer.parseInt(page)*10));
		
		List<DocumentBean> folderList = documentDao.selectTeamFolderList(bean); 
		int maxPage = documentDao.selectTeamFolderPageCount(bean);
		int maxFolder = documentDao.selectCountTeamFolderList(bean);

		mv.addObject("startingPage", MainUtls.getStartpage(maxPage,Integer.parseInt(page)));
		mv.addObject("endPage", MainUtls.getEndpage(maxPage,Integer.parseInt(page)));
		mv.addObject("nowPage", Integer.parseInt(page));
		mv.addObject("maxPage", maxPage);
		mv.addObject("maxFolder", maxFolder);
		mv.addObject("folderList", folderList);
		mv.addObject("groupList", MainUtls.getGroupList(groupInFolderList));
		mv.addObject("groupInFolderList", groupInFolderList);
		mv.addObject("userId", userId);
		mv.addObject("myGroup", groupId);
		mv.setViewName("content/teamFolderManager");
		
		return mv;
	}

	@Override
	public String insertFolder(HttpServletRequest request, DocumentBean bean, HttpSession session) {

		// ID를 위한 날짜 (밀리세컨드)
	 	Date today = new Date();

		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		
	 	SimpleDateFormat date = new SimpleDateFormat("MMddHHmmssSSS");

	    String dateMiliSecond = date.format(today);
	    bean.setGROUP_ID("4");
	    bean.setFOLDER_ID(bean.getGROUP_ID() + "_" + dateMiliSecond);
	    bean.setUSER_ID(userId);
	    bean.setUSER_NAME(userName);
		
		if(documentDao.insertFolder(bean) < 1) {
			return "fail";
		}
		
		return "success";
	}
	

}
