package com.ca.cacore.csm.dao.customerAction;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ca.cacore.csm.model.bo.ICustomerActionModel;
import com.ca.cacore.csm.model.bo.ICustomerModel;
import com.ca.cacore.csm.model.vo.CustomerViewVOModel;
import com.ca.cacore.csm.model.vo.ICustomerActionVOModel;

@Repository
public interface ICustomerActionDao {
	public boolean addCustomerAction(ICustomerActionModel model);
	public List<ICustomerActionModel> queryAll(ICustomerActionVOModel model);
	public CustomerViewVOModel queryForAction(ICustomerModel model);
	public CustomerViewVOModel queryForDetail(ICustomerModel model);
}
