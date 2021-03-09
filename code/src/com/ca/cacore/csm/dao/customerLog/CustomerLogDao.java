package com.ca.cacore.csm.dao.customerLog;

import org.springframework.stereotype.Repository;

import com.ca.cacore.csm.model.bo.ICustomerLogModel;
import com.newtouch.core.daobase.BaseDao;
/**
 * 
* @since:    2014年1月2日   
* @author    newtouchlxy
* @description:
 */

@Repository
public class CustomerLogDao extends BaseDao implements ICustomerLogDao{

	@Override
	public boolean addCustomerLog(ICustomerLogModel model) {
		this.getSqlMapClientTemplate().insert("customerLog.addCustomerLog", model);
		return true;
	}
	
}
