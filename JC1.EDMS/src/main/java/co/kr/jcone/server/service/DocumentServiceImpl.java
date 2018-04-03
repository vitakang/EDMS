package co.kr.jcone.server.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	public String uploadDocument(HttpServletRequest request, DocumentBean bean) {
		
		// ID를 위한 날짜 (밀리세컨드)
	 	Date today = new Date();
	    
	 	SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	    SimpleDateFormat dateFilePathFormat = new SimpleDateFormat("yyyy/MMdd");

	    String dateMiliSecond = date.format(today);
	    String dateFilePath = dateFilePathFormat.format(today);

		try {
			
//			최초등록,최초등록20180402152410007
//			최초등록,최초등록
//			안녕하세요,안녕하세요20180402152410007
//			안녕하세요,안녕하세요
//			안녕하세요 처음 문서 등록하겠습니다.,안녕하세요 처음 문서 등록하겠습니다.
//			FAIL
//			2018-04-02,2018-04-02
			
			MultipartFile[] multiPartUploadFile = bean.getMultiPartFiles();
			
			String bindTitle = MainUtls.changeTextUTF8(bean.getBIND_TITLE());
			String documentTitle = MainUtls.changeTextUTF8(bean.getDOCUMENT_TITLE());
			String securityCode = MainUtls.changeSecurityGradeCode(MainUtls.changeTextUTF8(bean.getSECURITY_GRADE()));
			String documentId = documentTitle + "_" + dateMiliSecond;
			
//			System.out.println(bindTitle + dateMiliSecond);
//			System.out.println(bindTitle);
//			System.out.println(documentId);
//			System.out.println(documentTitle);
//			System.out.println(MainUtls.changeTextUTF8(bean.getDOCUMENT_DESCRIPTION()));
//			System.out.println(securityCode);
//			System.out.println(bean.getREGISTER_DATE());
			
			
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

}
