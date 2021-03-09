package com.ca.cacore.manage.dao;

import java.util.List;

import com.ca.cacore.manage.model.bo.PassChgTypeModel;

/**
* @since:    2013年11月16日 上午11:39:15   
* @author    GCH
* @description:查询密码变更类型字典信息dao层接口类
*/
public interface IUserPassChgTypeDao {
	public List<PassChgTypeModel> queryPassChgType();
}
