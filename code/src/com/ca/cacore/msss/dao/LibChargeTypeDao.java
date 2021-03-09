package com.ca.cacore.msss.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import com.ca.cacore.msss.model.bo.LibChargeTypeModel;
import com.newtouch.core.daobase.BaseDao;
/**
 * 
 * @author Wangds
 * @since 2013-12-9
 * @description 缴费方式数据字典
 */
@Component
public class LibChargeTypeDao extends BaseDao implements ILibChargeTypeDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<LibChargeTypeModel> queryLibChargeType() {
		return this.getSqlMapClientTemplate().queryForList("LibChargeType.queryLibChargeType");
	}

}
