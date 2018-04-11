package co.kr.jcone.server.dao;

import java.util.List;
import java.util.Map;

import co.kr.jcone.server.bean.GroupBean;

public interface MainDao {

	List<GroupBean> selectGroupList();

	List<GroupBean> selectGroupInFolderList(String groupId);

	abstract public int insertFavoriteDocument(Map<String, String> dataMap);

	abstract public List<Map<String, Object>> selectFavoriteList(Map<String, String> paramMap);

	abstract public int deleteFavorite(Map<String, String> dataMap);

}
