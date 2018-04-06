package co.kr.jcone.server.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.kr.jcone.server.bean.DocumentBean;
import co.kr.jcone.server.dao.TempDao;

@Service
public class TempServiceImpl implements TempService{
	
	@Autowired
	private TempDao tempDao; 

	@Override
	public String teamFolderDelete(HttpServletRequest request, HttpSession session, DocumentBean bean) {
		
		String jData = request.getParameter("jsonData");
		
		try {
			JSONParser jObj = new JSONParser();
			Object obj = jObj.parse(jData);
			JSONArray jArr = (JSONArray) obj;
			
			for(int i = 0; i < jArr.size(); i++ ) {

				JSONObject jsonObject = (JSONObject) jArr.get(i);
				System.out.println(jsonObject.get("FOLDER_IDS"));
				
				tempDao.teamFolderDelete((String)jsonObject.get("FOLDER_IDS"));
				
			}
			
			
		} catch (ParseException e) {
			e.printStackTrace();
			return "fail";
		}
		
		
		return "success";
	}

}
