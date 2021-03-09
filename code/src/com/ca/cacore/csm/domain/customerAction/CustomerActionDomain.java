package com.ca.cacore.csm.domain.customerAction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ca.cacore.manage.dao.user.IUserDao;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.csm.dao.customerAction.ICustomerActionDao;
import com.ca.cacore.csm.model.bo.ICustomerActionModel;
import com.ca.cacore.csm.model.bo.ICustomerModel;
import com.ca.cacore.csm.model.vo.CustomerViewVOModel;
import com.ca.cacore.csm.model.vo.ICustomerActionVOModel;

/**
 * 
* @since:    2013年12月24日   
* @author    newtouchlxy
* @description:客户行为信息Domain
 */

@Repository
public class CustomerActionDomain implements ICustomerActionDomain{

	@Autowired private ICustomerActionDao customerActionDao;
	@Autowired private IUserDao userDao;
	
	/**
	 * 
	* 
	* @param model
	* @param user
	* @return 
	* @description:添加客户行为信息功能
	 */
	public boolean addCustomerAction(ICustomerActionModel model,IUserModel user) {

		model.setCreateuser(user.getEmp_id());
		model.setModifyuser(user.getEmp_id());
		customerActionDao.addCustomerAction(model);
		return true;
	}

	/**
	 * 
	* 
	* @param model
	* @return 
	* @description:分页查询客户行为信息
	 */
	public List<ICustomerActionModel> queryAll(ICustomerActionVOModel model) {
		return customerActionDao.queryAll(model);
	}
	
	/**
	 * 
	* 2014-1-6 15:50
	* @param model
	* @return CustomerViewVOModel
	* @description: 用于客户行为录入客户信息查询
	 */
	public CustomerViewVOModel queryForAction(ICustomerModel model) {
		
		return customerActionDao.queryForAction(model);
	}

	/**
	 * 
	* 2014-1-6 16:08张晨
	* @param modle
	* @return ReturnMsg
	* @description:用户行为明细操作方法
	 */
	public CustomerViewVOModel queryForDetail(ICustomerModel model) {

		return customerActionDao.queryForDetail(model);
	}
	
}
