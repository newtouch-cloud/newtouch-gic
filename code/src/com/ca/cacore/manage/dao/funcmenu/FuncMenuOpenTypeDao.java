package com.ca.cacore.manage.dao.funcmenu;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.manage.model.bo.FuncMenuOpenTypeModel;
import com.newtouch.core.daobase.BaseDao;
/**
 * @author admin
 *	获取菜单打开方式标签内容
 */
@Component
public class FuncMenuOpenTypeDao extends BaseDao {
	@SuppressWarnings("unchecked")
	public List<FuncMenuOpenTypeModel> getAllFuncMenuOpenTypeData() {
		return (List<FuncMenuOpenTypeModel>)this.getSqlMapClientTemplate().queryForList("funcMenuOpenType.getFuncMenuOpenTypeData");
	}
}
