package co.kr.jcone.server.dao;

import java.util.List;

import co.kr.jcone.server.bean.GroupBean;

public interface MainDao {

	List<GroupBean> selectGroupList();

	List<GroupBean> selectGroupInFolderList();

}
