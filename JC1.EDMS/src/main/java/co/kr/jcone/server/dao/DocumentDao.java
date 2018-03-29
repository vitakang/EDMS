package co.kr.jcone.server.dao;

import co.kr.jcone.server.bean.DocumentBean;

public interface DocumentDao {

	int insertDocument(DocumentBean db);

	String testDb();

}
