package com.ca.cacore.manage.dao.funcmenu;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.manage.model.bo.FuncMenuURITypeModel;
import com.newtouch.core.daobase.BaseDao;
/**
 * @author admin
 *	获取菜单打开方式信息
 */
@Component
public class FuncMenuURITypeDao extends BaseDao {
	@SuppressWarnings("unchecked")
	public List<FuncMenuURITypeModel> getAllFuncMenuURITypeData() {
		return (List<FuncMenuURITypeModel>)this.getSqlMapClientTemplate().queryForList("funcMenuURIType.getFuncMenuURITypeData");
	}
}
