package com.ca.cacore.csm.domain.CustomerContact;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.csm.model.bo.ICustomerContactModel;

@Service
public interface ICustomerContactDomain {
	public boolean addCoustomerContact(ICustomerContactModel model,IUserModel user,boolean flag);
	public boolean updateCustomerContact(ICustomerContactModel model,IUserModel user);
	public List<ICustomerContactModel> queryCustomerContact(ICustomerContactModel model);
}
