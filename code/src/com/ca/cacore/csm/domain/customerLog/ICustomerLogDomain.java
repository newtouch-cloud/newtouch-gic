package com.ca.cacore.csm.domain.customerLog;

import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.csm.model.bo.ICustomerModel;


@Service
public interface ICustomerLogDomain{
	public boolean addCustomerLogDomain(ICustomerModel model,IUserModel user,boolean flag);
}
