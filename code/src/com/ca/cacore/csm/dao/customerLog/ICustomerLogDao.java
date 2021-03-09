package com.ca.cacore.csm.dao.customerLog;

import org.springframework.stereotype.Repository;

import com.ca.cacore.csm.model.bo.ICustomerLogModel;

@Repository
public interface ICustomerLogDao {
	public boolean addCustomerLog(ICustomerLogModel model);
}
