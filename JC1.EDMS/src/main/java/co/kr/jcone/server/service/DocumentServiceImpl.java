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
import co.kr.jcone.server.bean.PathBean;
import co.kr.jcone.server.dao.DocumentDao;
import co.kr.jcone.server.util.MainUtls;

@Service
public class DocumentServiceImpl implements DocumentService{

	private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);
	
	@Autowired
	private DocumentDao documentDao;

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
			String documentId = documentTitle + "_" + dateMiliSecond;
			
			DocumentBean document = new DocumentBean();
			// 철ID
			document.setBIND_ID(bindTitle + "_" + dateMiliSecond);
			// 철제목
			document.setBIND_TITLE(bindTitle);
			// 문서 아이디
			document.setDOCUMENT_ID(documentId);
			// 문서 제목
			document.setDOCUMENT_TITLE(documentTitle);
			// 문서 설명
			document.setDOCUMENT_DESCRIPTION(MainUtls.changeTextUTF8(bean.getDOCUMENT_DESCRIPTION()));
			// 보안등급
			document.setSECURITY_GRADE(securityCode);
			// 등록일
			document.setREGISTER_DATE(bean.getREGISTER_DATE());
			// 버전 (최초 1.0)
			document.setVERSION("1.0");
			// 사용자 아이디 셋팅 	db.setUSER_ID(uSER_ID);
			// 사용자 이름 			db.setUSER_NAME(uSER_NAME);
			// 그룹 아이디			db.setGROUP_ID(gROUP_ID);
			// 그룹이름			db.setGROUP_NAME(gROUP_NAME);
			// 삭제유형 DEFAULT = 0
			
			if(documentDao.insertDocument(document) < 1) {
				logger.info("documentDao.insertDocument(document) FAIL");	
				return "fail";			
			}
			
			System.out.println(PathBean.PATH_UPLOADFILE);
			
			if(multiPartUploadFile != null) {
				DocumentBean documentFileBean = new DocumentBean();
				
				for(int i = 0; i < bean.getMultiPartFiles().length; i++) {
					MultipartFile mFile = bean.getMultiPartFiles()[i];
					byte[] bytes = mFile.getBytes();
					String fileName = mFile.getOriginalFilename();
					String uploadPath = PathBean.PATH_UPLOADFILE;
					
					File pathFile = new File(uploadPath + dateFilePath);
					
					if(!pathFile.exists()) {
						pathFile.mkdirs();
					}
					
					uploadPath = uploadPath + dateFilePath + File.separator + dateMiliSecond + "_" + fileName;
					
					documentFileBean.setDOCUMENT_FILE_ID(fileName + dateMiliSecond);
					documentFileBean.setDOCUMENT_ID(documentId);
					documentFileBean.setORIGINAL_FILE_NAME(uploadPath);
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
			documentVersion.setDOCUMENT_ID(documentId);
			documentVersion.setVERSION("1.0");
			documentVersion.setMODIFY_REASON("최초");
			//documentVersion.setORIGINAL_DOCUMENT_ID(oRIGINAL_DOCUMENT_ID);
			if(documentDao.insertDocumentVersion(documentVersion) < 1) {
				logger.info("documentDao.insertDocumentVersion(documentVersion) FAIL");
				return "fail";
			}
			
			DocumentBean documentFolder = new DocumentBean();
			documentFolder.setDOCUMENT_ID(documentId);
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
		String documentId = bean.getDOCUMENT_ID();
		
		System.out.println(documentId);
		
		DocumentBean documentBean = documentDao.viewDetail(documentId);
		List<DocumentBean> documentFileBean = documentDao.selectFileListFromDocumentId(documentId);
		
		documentBean.setSECURITY_GRADE(MainUtls.changeSecurityGradeCode(documentBean.getSECURITY_GRADE(),"2"));
		
		mv.addObject("folderName",bean.getFOLDER_NAME());
		mv.addObject("documentBean",documentBean);
		mv.addObject("fileList",documentFileBean);
		mv.setViewName("content/viewDetail");
		
		
		return mv;
	}

	@Override
	public ModelAndView teamFolderInsert(HttpServletRequest request, DocumentBean bean, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		List<DocumentBean> groupFolderList = documentDao.selectGroupFolderList(bean);
		
		mv.addObject("groupName", "3사업부");
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
		String searchType = bean.getSearchType();
		
		//System.out.println(searchType);
		
		//DocumentBean documentBean = new DocumentBean();
//		documentBean.setUSER_ID("vitakang");
		//documentBean.setFOLDER_ID(bean.getFOLDER_ID());
		//documentBean.setGROUP_ID("4");
		//documentBean.setStartPage(String.valueOf(Integer.parseInt(page)*10-10));
		//documentBean.setEndPage(String.valueOf(Integer.parseInt(page)*10));
		//documentBean.setSearchText(bean.getSearchText());
		//documentBean.setSearchType(bean.getSearchType());
		
		//List<DocumentBean> list = documentDao.getDocumentList(documentBean);
		//int maxPage = documentDao.selectDocumentPageCount(documentBean);
		//int maxDocument = documentDao.selectCountDocument(documentBean);

	    bean.setGROUP_ID("4");
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
		mv.setViewName("content/teamFolderManager");
		
		return mv;
	}

	@Override
	public String insertFolder(HttpServletRequest request, DocumentBean bean, HttpSession session) {

		// ID를 위한 날짜 (밀리세컨드)
	 	Date today = new Date();

	 	SimpleDateFormat date = new SimpleDateFormat("MMddHHmmssSSS");

	    String dateMiliSecond = date.format(today);
	    bean.setGROUP_ID("4");
	    bean.setFOLDER_ID(bean.getGROUP_ID() + "_" + dateMiliSecond);
		
		if(documentDao.insertFolder(bean) < 1) {
			return "fail";
		}
		
		return "success";
	}
	

}
