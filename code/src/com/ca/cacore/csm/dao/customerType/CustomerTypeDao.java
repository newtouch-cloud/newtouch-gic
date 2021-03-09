package com.ca.cacore.csm.dao.customerType;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ca.cacore.csm.model.bo.ICustomerTypeModel;
import com.newtouch.core.daobase.BaseDao;

@Repository
public class CustomerTypeDao extends BaseDao implements ICustomerTypeDao{

	@Override
	public List<ICustomerTypeModel> getTypes() {
		return (List<ICustomerTypeModel>)this.getSqlMapClientTemplate().queryForList("customerType.getTypes");
	};
	
}
