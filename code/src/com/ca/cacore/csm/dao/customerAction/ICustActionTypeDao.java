package com.ca.cacore.csm.dao.customerAction;

import java.util.List;

import com.ca.cacore.csm.model.bo.CustomerActionTypeModel;

public interface ICustActionTypeDao {
	public List<CustomerActionTypeModel> queryCustActionType();
}
