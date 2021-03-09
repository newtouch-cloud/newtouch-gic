package com.ca.cacore.csm.webapp.service;

import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.csm.model.bo.ICustomerActionModel;
import com.ca.cacore.csm.model.bo.ICustomerModel;
import com.ca.cacore.csm.model.vo.CustomerViewVOModel;
import com.ca.cacore.csm.model.vo.ICustomerActionVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.returnmsg.ReturnMsg;

@Service
public interface ICustomerActionService {
	public ReturnMsg addCustomerAction(ICustomerActionModel model,IUserModel user) throws BusinessException;
	public ReturnMsg queryList(ICustomerActionVOModel model);
	public CustomerViewVOModel queryForAction(ICustomerModel model);
	public ReturnMsg queryForDetail(ICustomerModel modle);


}
