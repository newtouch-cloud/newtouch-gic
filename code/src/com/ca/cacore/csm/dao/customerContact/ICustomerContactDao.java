package com.ca.cacore.csm.dao.customerContact;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ca.cacore.csm.model.bo.ICustomerContactModel;

@Repository
public interface  ICustomerContactDao {
	public boolean addCustomerContanct(ICustomerContactModel model);
	public List<ICustomerContactModel> queryCustomerContact(ICustomerContactModel model);
	public boolean updateCustomerContact(ICustomerContactModel model);
	
}
