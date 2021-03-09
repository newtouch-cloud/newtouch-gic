package com.ca.cacore.csm.webapp.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.csm.model.bo.ICustomerContactModel;
import com.ca.cacore.csm.model.bo.ICustomerJieChuModel;
import com.ca.cacore.csm.model.bo.ICustomerLogModel;
import com.ca.cacore.csm.model.bo.ICustomerModel;
import com.ca.cacore.csm.model.vo.CustomerVOModel;
import com.ca.cacore.csm.model.vo.CustomerViewVOModel;
import com.ca.cacore.csm.model.vo.ICustomerVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.returnmsg.ReturnMsg;

@Service
public interface ICustomerService {
	public ReturnMsg addCustomerAndCustomerContact(ICustomerModel customermodel,ICustomerContactModel contactmodel,
			ICustomerLogModel logmodel,IUserModel user,String customer_id,String[] contactpersonInfo) throws BusinessException;
	public ReturnMsg getVOAll(ICustomerVOModel model);
	public ReturnMsg getView(ICustomerModel model);
	public ReturnMsg modifyCustomerAndCustomerContact(CustomerViewVOModel model, IUserModel user,String[] info) throws BusinessException;//修改
	public ReturnMsg getModifyView(ICustomerModel model);
	public boolean customerIsRepeat(ICustomerModel model);
	public ICustomerVOModel getCustomerNewInfo(ICustomerVOModel model);
	public CustomerVOModel getCustomerInfoByCusBranID(CustomerVOModel model);
	public boolean checkMemberId(ICustomerModel model);
	public List<ICustomerVOModel> daochuCustomer(ICustomerVOModel model);
	public ReturnMsg addCustomerJieChu(ICustomerJieChuModel model,IUserModel user);
	public ReturnMsg queryJieChu(ICustomerModel model);
	public ICustomerModel getClasscode(ICustomerModel model);
	public ReturnMsg queryCustomer1(ICustomerVOModel model);//新增
	public ReturnMsg modifySave(CustomerVOModel model);//修改
	ReturnMsg goModify(String customer_id);
	public ReturnMsg updateCustomer(ICustomerVOModel model);


}
