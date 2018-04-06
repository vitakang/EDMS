package co.kr.jcone.server.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.kr.jcone.server.bean.DocumentBean;
import co.kr.jcone.server.util.MainUtls;

@Repository
public class DocumentDaoImpl implements DocumentDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertDocument(DocumentBean db) {
		return sqlSession.insert("edmsMapper.insertDocument", db);
	}

	@Override
	public String testDb() {
		return sqlSession.selectOne("edmsMapper.selectTest");
	}

	@Override
	public int insertDocumentFile(DocumentBean documentFileBean) {
		return sqlSession.insert("edmsMapper.insertDocumentFile", documentFileBean);
	}

	@Override
	public int insertDocumentVersion(DocumentBean documentVersion) {
		return sqlSession.insert("edmsMapper.insertDocumentVersion", documentVersion);
	}

	@Override
	public int insertDocumentFolder(DocumentBean documentFolder) {
		return sqlSession.insert("edmsMapper.insertDocumentFolder", documentFolder);
	}

	@Override
	public List<DocumentBean> getDocumentList(DocumentBean documentBean) {
		return sqlSession.selectList("edmsMapper.getDocumentList", documentBean);
	}

	@Override
	public DocumentBean viewDetail(String documentId) {
		return sqlSession.selectOne("edmsMapper.viewDetail", documentId);
	}

	@Override
	public List<DocumentBean> selectFileListFromDocumentId(String documentId) {
		return sqlSession.selectList("edmsMapper.selectFileListFromDocumentId", documentId);
	}

	@Override
	public String getFileOriginalPath(DocumentBean bean) {
		return sqlSession.selectOne("edmsMapper.getFileOriginalPath", bean);
	}

	@Override
	public List<DocumentBean> selectGroupFolderList(DocumentBean bean) {
		return sqlSession.selectList("edmsMapper.selectGroupFolderList", bean);
	}

	@Override
	public int insertFolder(DocumentBean bean) {
		return sqlSession.insert("edmsMapper.insertFolder", bean);
	}

	@Override
	public List<DocumentBean> selectTeamFolderList(DocumentBean bean) {
		
		return sqlSession.selectList("edmsMapper.selectTeamFolderList", bean);
	}

	@Override
	public int selectDocumentPageCount(DocumentBean documentBean) {
		return sqlSession.selectOne("edmsMapper.selectDocumentPageCount",documentBean);
	}

	@Override
	public int selectCountDocument(DocumentBean documentBean) {
		return sqlSession.selectOne("edmsMapper.selectCountDocument",documentBean);
	}
	
	
	
	
	
	
	
}
