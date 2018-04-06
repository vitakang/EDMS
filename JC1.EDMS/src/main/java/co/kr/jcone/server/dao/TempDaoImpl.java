package co.kr.jcone.server.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TempDaoImpl implements TempDao{

	@Autowired 
	private SqlSession sqlSession;

	@Override
	public int teamFolderDelete(String string) {
		return sqlSession.update("edmsMapper.teamFolderDelete",string);
	}
}
