package co.kr.jcone.server.service;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
	
}
