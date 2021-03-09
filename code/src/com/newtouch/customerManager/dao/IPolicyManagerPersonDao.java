package com.newtouch.customerManager.dao;

import java.util.List;

import com.newtouch.customerManager.model.IPolicyManagerPersonModel;

public interface IPolicyManagerPersonDao {
	public List<IPolicyManagerPersonModel> policyQueryList(IPolicyManagerPersonModel model);

	public IPolicyManagerPersonModel policyQueryMsg(IPolicyManagerPersonModel model);

}
