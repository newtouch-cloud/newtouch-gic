package com.ca.cacore.manage.dao;


import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.manage.model.bo.PassChgTypeModel;
import com.newtouch.core.daobase.BaseDao;

/**
* @company:  newtouch
* @since:    2013年11月16日 上午11:40:14   
* @author    GCH
* @description:查询密码变更类型字典信息dao层代码
*/
@Component
public class UserPassChgTypeDao extends BaseDao implements IUserPassChgTypeDao{

	@SuppressWarnings("unchecked") 
	@Override
	public List<PassChgTypeModel> queryPassChgType() {
		PassChgTypeModel model=new PassChgTypeModel();
		List<PassChgTypeModel> list=this.getSqlMapClientTemplate().queryForList("passChgType.querypassChgType", model);
		return list;
	}
	
}
