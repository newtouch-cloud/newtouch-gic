package com.newtouch.customerManager.domain;

import java.util.List;

import com.newtouch.customerManager.model.IPolicyManagerPersonModel;

public interface IPolicyManagerPersonDomain {
	/**
	 * @author pz
	 * @param model
	 * @return List<IEmployeeVMengModel>
	 * @description:查询列表
	 */
	public List<IPolicyManagerPersonModel> policyQueryList(IPolicyManagerPersonModel model);

	/**
	 * @author pz
	 * @param model
	 * @return IEmployeeVMengModel
	 * @description:查询列表
	 */
	public IPolicyManagerPersonModel policyQueryMsg(IPolicyManagerPersonModel model);

}
