package com.ca.cacore.csm.domain.customerAction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.csm.dao.customerAction.ICustActionTypeDao;
import com.ca.cacore.csm.model.bo.CustomerActionTypeModel;

@Service
public class CustActionTypeDomain implements ICustActionTypeDomain{
	@Autowired private ICustActionTypeDao custActionTypeDao;
	
	@Override
	public List<CustomerActionTypeModel> queryCustActionType() {
		return custActionTypeDao.queryCustActionType();
	}
}
