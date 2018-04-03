package co.kr.jcone.server.dao;

import java.util.List;

import co.kr.jcone.server.bean.DocumentBean;

public interface DocumentDao {

	int insertDocument(DocumentBean db);

	String testDb();

	int insertDocumentFile(DocumentBean documentFileBean);

	int insertDocumentVersion(DocumentBean documentVersion);

	int insertDocumentFolder(DocumentBean documentFolder);

	List<DocumentBean> getDocumentList(DocumentBean documentBean);

}
