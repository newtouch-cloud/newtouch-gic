package com.ca.cacore.csm.dao.customer;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ca.cacore.csm.model.bo.ICustomerContactModel;
import com.ca.cacore.csm.model.bo.ICustomerJieChuModel;
import com.ca.cacore.csm.model.bo.ICustomerLogModel;
import com.ca.cacore.csm.model.bo.ICustomerModel;
import com.ca.cacore.csm.model.bo.IInfclaimsModel;
import com.ca.cacore.csm.model.vo.CustomerVOModel;
import com.ca.cacore.csm.model.vo.CustomerViewVOModel;
import com.ca.cacore.csm.model.vo.ICustomerVOModel;
import com.ca.cacore.csm.model.vo.ILaspolicyModel;


@Repository
public interface  ICustomerDao {
	/**
	 * 
	 * @param model
	 * @return
	 */
	public boolean addCustomer(ICustomerModel model);
	public Boolean addCustomerContanct(ICustomerContactModel model);
	public boolean addCustomerLog(ICustomerLogModel model);
	public List<ICustomerVOModel> getVOAll(ICustomerVOModel model);
	public CustomerViewVOModel getView(ICustomerModel model);
	public List<CustomerViewVOModel> queryCustomer(ICustomerModel model);
	public List<ICustomerContactModel> queryCustomerContact(ICustomerModel model);
	public boolean updateCustomer(ICustomerModel model);
	public boolean updateCustomerContact(ICustomerContactModel model);
	public ICustomerContactModel queryNewestCustomerContact(ICustomerContactModel model);
	public ICustomerModel queryRigid(ICustomerModel model);
	public boolean modifyCustomer(ICustomerModel model);
	public List<ICustomerModel> validateCustomer(ICustomerModel model);
	public ICustomerVOModel getCustomerNewInfo(ICustomerVOModel model);
	public Integer getSeqId(ICustomerModel model);
	public Integer checkMemberId(ICustomerModel model);
	public List<ICustomerVOModel> daochuCustomer(ICustomerVOModel model);
	public boolean addCustomerJieChu(ICustomerJieChuModel model);
	public CustomerViewVOModel queryJieChu(ICustomerModel model);
	public List<IInfclaimsModel> queryInfclaimsAll(ICustomerModel model);
	public List<ICustomerJieChuModel> queryJiechuAll(ICustomerModel model);
	public List<ILaspolicyModel> queryLas(ICustomerModel model);
	public boolean deleteCustomerContanct(ICustomerModel model);
	/**
	 * 
	* @param model
	* @return 
	* @description:查询客户客户最新的一条联系信息 by 张晨
	 */
	public ICustomerContactModel getNewestCustomerContact(ICustomerContactModel model);
	
	/** 
	* 
	* @param model
	* @return boolean
	* @description:更新附件时间
	*/
	public boolean updateCustomterTime(ICustomerModel model);
	public ICustomerModel getClasscode(ICustomerModel model);
	public boolean queryCustomer1(ICustomerVOModel model);//添加
	public boolean modifySave(ICustomerVOModel model);//修改
	public List<CustomerVOModel> goModify(String customer_id);//单个修改
}
