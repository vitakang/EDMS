package co.kr.jcone.server.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.kr.jcone.server.bean.GroupBean;

@Repository
public class MainDaoImpl implements MainDao{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<GroupBean> selectGroupList() {
		return sqlSession.selectList("edmsMapper.selectGroupList");
	}
	
	
	
}
