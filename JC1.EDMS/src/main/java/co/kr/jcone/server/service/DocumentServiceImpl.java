package co.kr.jcone.server.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.kr.jcone.server.bean.DocumentBean;
import co.kr.jcone.server.dao.DocumentDao;
import co.kr.jcone.server.util.MainUtls;

@Service
public class DocumentServiceImpl implements DocumentService{
	
	@Autowired
	private DocumentDao documentDao;

	@Override
	public String uploadDocument(HttpServletRequest request, DocumentBean bean) {
		
		try {
			
			DocumentBean db = new DocumentBean();
			db.setBIND_TITLE(MainUtls.changeTextUTF8(bean.getBIND_TITLE()));
			db.setDOCUMENT_TITLE(MainUtls.changeTextUTF8(bean.getDOCUMENT_TITLE()));
			db.setDOCUMENT_DESCRIPTION(MainUtls.changeTextUTF8(bean.getDOCUMENT_DESCRIPTION()));
			db.setREGISTER_DATE(bean.getREGISTER_DATE());
			
			
			System.out.println(documentDao.testDb());
			//documentDao.insertDocument(db);
			
			
//		System.out.println(bean.getBIND_ID());
////		System.out.println(new String(bean.getBIND_ID().getBytes("8859_1"),"utf-8"));
//		System.out.println(bean.getDOCUMENT_TITLE());
//		System.out.println(bean.getDOCUMENT_DESCRIPTION());
//		System.out.println(bean.getREGISTER_DATE());
//		//System.out.println(bean.getMultiPartFiles().length);
//			for(MultipartFile file : bean.getMultiPartFiles()) {
//				//multiPartFiles  apache-tomcat-8.5.29-windows-x64.zip  application/x-zip-compressed  11181877  [B@6567fba6
//				System.out.println(file.getName() + "  " + file.getOriginalFilename() + "  " + file.getContentType() + "  " + file.getSize() + "  " + file.getBytes());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
