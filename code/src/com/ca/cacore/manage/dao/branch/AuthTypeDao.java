package com.ca.cacore.manage.dao.branch;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.manage.model.bo.AuthTypeModel;
import com.newtouch.core.daobase.BaseDao;
/**
 * @author admin
 *	获取权限类型标签内容
 */
@Component
public class AuthTypeDao extends BaseDao {
	@SuppressWarnings("unchecked")
	public List<AuthTypeModel> getAllAuthTypeData() {
		return (List<AuthTypeModel>)this.getSqlMapClientTemplate().queryForList("authType.getAuthTypeData");
	}
}
