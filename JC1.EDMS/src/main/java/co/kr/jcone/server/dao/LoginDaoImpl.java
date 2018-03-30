package co.kr.jcone.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDaoImpl implements LoginDao{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int loginChceck(Map<String, String> paramMap) {
		int cnt = sqlSession.selectOne("edmsMapper.loginCheck", paramMap);
		return cnt;
	}
	
	public List<Map<String, String>> getList() {
		return sqlSession.selectList("edmsMapper.getList");
	}
	

}
