package com.ca.cacore.ibs.domain;

import java.util.List;

import com.ca.cacore.ibs.model.bo.CustomerIncomTypeModel;

public interface ICustomerIncomTypeDomain {
	public List<CustomerIncomTypeModel> queryIncomType();
}
