package com.ca.cacore.csm.domain.CustomerContact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.csm.dao.customer.ICustomerDao;
import com.ca.cacore.csm.dao.customerContact.ICustomerContactDao;
import com.ca.cacore.csm.model.bo.CustomerModel;
import com.ca.cacore.csm.model.bo.ICustomerContactModel;
import com.ca.cacore.csm.model.bo.ICustomerModel;

@Service
public class CustomerContactDomain implements ICustomerContactDomain{

	@Autowired private ICustomerContactDao customerContactDao ;
	@Autowired private ICustomerDao customerDao;
	
	
	public boolean addCoustomerContact(ICustomerContactModel model,IUserModel user,boolean flag) {
		
		
		
		model.setCreateuser(user.getEmp_id());
		model.setModifyuser(user.getEmp_id());
		model.setStatus("1");
		model.setLog_bustype("111");
		ICustomerModel customer = new CustomerModel();
		customer.setCustomer_id(model.getCustomer_id());
		
		if(flag){
			model.setLog_remark("客户基本信息为添加型操作");
			int log_seq_id = customerDao.validateCustomer(customer).get(0).getSeq_id(); 
			model.setLog_seq_id(log_seq_id);
		}else{
			model.setLog_remark("客户基本信息为修改型操作");
		}
		
		return customerContactDao.addCustomerContanct(model);
	}
	
	
	
	

	@Override
	public boolean updateCustomerContact(ICustomerContactModel model,
			IUserModel user) {
		return false;
	}

	@Override
	public List<ICustomerContactModel> queryCustomerContact(
			ICustomerContactModel model) {
		return customerContactDao.queryCustomerContact(model);
	}



}
