package com.ca.cacore.csm.dao.customerAction;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.csm.model.bo.CustomerActionTypeModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class CustActionTypeDao extends BaseDao implements ICustActionTypeDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerActionTypeModel> queryCustActionType() {
		return this.getSqlMapClientTemplate().queryForList("CustomerActionType.queryCustActionType");
	}

}
