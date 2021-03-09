package com.ca.cacore.csm.domain.CustomerAndCustomerContact;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.csm.model.bo.ICustomerContactModel;
import com.ca.cacore.csm.model.bo.ICustomerJieChuModel;
import com.ca.cacore.csm.model.bo.ICustomerLogModel;
import com.ca.cacore.csm.model.bo.ICustomerModel;
import com.ca.cacore.csm.model.bo.IInfclaimsModel;
import com.ca.cacore.csm.model.vo.CustomerVOModel;
import com.ca.cacore.csm.model.vo.CustomerViewVOModel;
import com.ca.cacore.csm.model.vo.ICustomerVOModel;
import com.ca.cacore.csm.model.vo.ILaspolicyModel;


@Service
public interface  ICustomerDomain {
	public boolean addCustomerr(ICustomerModel customer,ICustomerContactModel customerContact,
			ICustomerLogModel customerLog,IUserModel user,String customer_id,String[] contactpersonInfo);
	public List<ICustomerVOModel> getVOAll(ICustomerVOModel model);
	public CustomerViewVOModel getModifyView(ICustomerModel model);
	public boolean queryCustomer1(ICustomerVOModel model);//新增
	public List<ICustomerContactModel> queryCustomerContact(ICustomerModel model);
	public ICustomerModel queryRigid(ICustomerModel model);
	public boolean modifyCustomerAndCustomerContact(CustomerViewVOModel model, IUserModel user,String[] contactpersonInfo);
	public boolean verifyIsExistBranch(ICustomerContactModel model);
	public boolean verifyIsExistIdentityAndType(ICustomerModel model);
	public ICustomerVOModel getCustomerNewInfo(ICustomerVOModel model);
	public boolean checkMemberId(ICustomerModel model);
	public List<ICustomerVOModel> doachuCustomer(ICustomerVOModel model); 
	public boolean addCustomerJieChu(ICustomerJieChuModel model,IUserModel model1);
	public CustomerViewVOModel queryJieChu(ICustomerModel model);
	public List<IInfclaimsModel> queryInfclaimsAll(ICustomerModel model);
	public List<ICustomerJieChuModel> queryJieChuAll(ICustomerModel model);
	public List<ILaspolicyModel> queryLas(ICustomerModel model);
	public ICustomerModel getClasscode(ICustomerModel model);
	public CustomerViewVOModel queryCustomer(ICustomerModel model);
	public boolean modifySave(ICustomerVOModel model);//修改
	public List<CustomerVOModel> goModify(String customer_id);
	
}
