package com.ca.cacore.csm.domain.customerAction;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.csm.model.bo.ICustomerActionModel;
import com.ca.cacore.csm.model.bo.ICustomerModel;
import com.ca.cacore.csm.model.vo.CustomerViewVOModel;
import com.ca.cacore.csm.model.vo.ICustomerActionVOModel;

@Repository
public interface ICustomerActionDomain {
	public boolean addCustomerAction(ICustomerActionModel model,IUserModel user);
	public List<ICustomerActionModel> queryAll(ICustomerActionVOModel model);
	public CustomerViewVOModel queryForAction(ICustomerModel model);
	public CustomerViewVOModel queryForDetail(ICustomerModel model);
}
