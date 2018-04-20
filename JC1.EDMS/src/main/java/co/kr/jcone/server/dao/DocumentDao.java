package co.kr.jcone.server.dao;

import java.util.List;
import java.util.Map;

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

	DocumentBean getFileOriginalPath(DocumentBean bean);

	List<DocumentBean> selectGroupFolderList(DocumentBean bean);

	int insertFolder(DocumentBean bean);

	List<DocumentBean> selectTeamFolderList(DocumentBean bean);

	int selectDocumentPageCount(DocumentBean documentBean);

	int selectCountDocument(DocumentBean documentBean);

	int selectTeamFolderPageCount(DocumentBean bean);

	int selectCountTeamFolderList(DocumentBean bean);

	int documentDelete(DocumentBean bean);

	int selectFavoriteDocumentPageCount(Map<String, String> paramMap);

	int selectCountFavoriteDocument(Map<String, String> paramMap);

	int updateDocumentReadHistory(DocumentBean historyBean);

	int changePwd(DocumentBean bean);

}
