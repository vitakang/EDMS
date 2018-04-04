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

	DocumentBean viewDetail(String documentId);

	List<DocumentBean> selectFileListFromDocumentId(String documentId);

	String getFileOriginalPath(DocumentBean bean);

	List<DocumentBean> selectGroupFolderList(DocumentBean bean);

	int insertFolder(DocumentBean bean);

	List<DocumentBean> selectTeamFolderList(DocumentBean bean);

}
