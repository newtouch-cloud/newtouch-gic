package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.dao.ICustomerIncomTypeDao;
import com.ca.cacore.ibs.model.bo.CustomerIncomTypeModel;

@Service
public class CustomerIncomTypeDomain implements ICustomerIncomTypeDomain {
	@Autowired private ICustomerIncomTypeDao customerIncomTypeDao;
	@Override
	public List<CustomerIncomTypeModel> queryIncomType() {
		return customerIncomTypeDao.queryIncomType();
	}
	
}
