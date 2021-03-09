package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.bo.CustomerIncomTypeModel;
import com.newtouch.core.daobase.BaseDao;

/**
 * 
 * @author xxz521
 *
 */
@Component
public class CustomerIncomTypeDao  extends BaseDao implements ICustomerIncomTypeDao{

	@Override
	public List<CustomerIncomTypeModel> queryIncomType() {
		return this.getSqlMapClientTemplate().queryForList("IncomType.queryIncomType");
	}
}
