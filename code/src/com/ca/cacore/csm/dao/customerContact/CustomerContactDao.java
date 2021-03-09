package com.ca.cacore.csm.dao.customerContact;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ca.cacore.csm.model.bo.ICustomerContactModel;
import com.newtouch.core.daobase.BaseDao;

@Repository
public class CustomerContactDao extends BaseDao implements ICustomerContactDao {
	/**
	 * 
	* 
	* @param model
	* @return 
	* @description:插入客户联系信息
	 */
	public boolean addCustomerContanct(ICustomerContactModel model) {
		this.getSqlMapClientTemplate().insert("customerContact.insertCustomerContact",model);
		return true;
	}
	
	/**
	 * 
	* 
	* @param model
	* @return 
	* @description:查询客户联系根据
	 */
	public List<ICustomerContactModel> queryCustomerContact(
			ICustomerContactModel model) {
		return (List<ICustomerContactModel>)this.getSqlMapClientTemplate().queryForList("customerContact.queryCustomerContactModel", model);
	}
	
	/**
	 * 
	* 
	* @param model
	* @return 
	* @description:更新客户联系信息
	 */
	public boolean updateCustomerContact(ICustomerContactModel model) {
		this.getSqlMapClientTemplate().update("customerContact.updateCustomerContact",model);
		return true;
	}
	
}
