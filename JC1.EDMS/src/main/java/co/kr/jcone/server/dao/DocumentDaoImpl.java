package co.kr.jcone.server.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.kr.jcone.server.bean.DocumentBean;

@Repository
public class DocumentDaoImpl implements DocumentDao{

	@Autowired
	@Resource(name = "sqlSession")
	SqlSessionTemplate sqlSession;
	
	@Override
	public int insertDocument(DocumentBean db) {
		return 0;
	}

	@Override
	public String testDb() {
		
		return sqlSession.selectOne("edmsMapper.selectTest");
	}
	
	

}
