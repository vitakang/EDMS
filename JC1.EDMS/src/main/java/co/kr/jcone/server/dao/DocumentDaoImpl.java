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

		System.out.println(db.getDOCUMENT_ID());
		System.out.println(db.getREGISTER_DATE());
		System.out.println(db.getDOCUMENT_DESCRIPTION());
		System.out.println(db.getSECURITY_GRADE());
		System.out.println(db.getBIND_TITLE());
		System.out.println(db.getBIND_ID());
		System.out.println(db.getDOCUMENT_TITLE());
		System.out.println(db.getVERSION());
		
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
	
	

}
