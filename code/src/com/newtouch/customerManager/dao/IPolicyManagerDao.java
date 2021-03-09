package com.newtouch.customerManager.dao;

import java.util.List;

import com.newtouch.customerManager.model.IPolicyManagerModel;

public interface IPolicyManagerDao {
	public List<IPolicyManagerModel> policyQueryList(IPolicyManagerModel model);

	public IPolicyManagerModel policyQueryMsg(IPolicyManagerModel model);

}
