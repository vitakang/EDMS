package co.kr.jcone.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.kr.jcone.server.bean.GroupBean;

@Repository
public class MainDaoImpl implements MainDao {

	@Autowired private SqlSession sqlSession;

	@Override
	public List<GroupBean> selectGroupList() {
		return sqlSession.selectList("edmsMapper.selectGroupList");
	}

	@Override
	public List<GroupBean> selectGroupInFolderList(String groupId) {
		return sqlSession.selectList("edmsMapper.selectGroupInFolderList", groupId);
	}

	@Override
	public int insertFavoriteDocument(Map<String, String> dataMap) {
		return sqlSession.insert("edmsMapper.insertFavoriteDocument", dataMap);
	}

	@Override
	public List<Map<String, Object>> selectFavoriteList(Map<String, String> paramMap) {
		return sqlSession.selectList("edmsMapper.selectFavoriteList", paramMap);
	}

	@Override
	public int deleteFavorite(Map<String, String> dataMap) {
		return sqlSession.delete("edmsMapper.deleteFavorite", dataMap);
	}
	
}
