package com.ca.cacore.csm.domain.customerAction;

import java.util.List;

import com.ca.cacore.csm.model.bo.CustomerActionTypeModel;

public interface ICustActionTypeDomain {
	public List<CustomerActionTypeModel> queryCustActionType();
}
