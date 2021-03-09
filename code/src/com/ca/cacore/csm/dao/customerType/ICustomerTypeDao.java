package com.ca.cacore.csm.dao.customerType;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ca.cacore.csm.model.bo.ICustomerTypeModel;

@Repository
public interface ICustomerTypeDao {
	public List<ICustomerTypeModel> getTypes();
}
