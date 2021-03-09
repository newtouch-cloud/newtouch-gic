package com.ca.cacore.ibs.dao;

import java.util.List;

import com.ca.cacore.ibs.model.bo.CustomerIncomTypeModel;

public interface ICustomerIncomTypeDao {
	public List<CustomerIncomTypeModel> queryIncomType();
}
