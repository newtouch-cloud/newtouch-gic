package com.newtouch.customerManager.domain;

import java.util.List;

import com.newtouch.customerManager.model.IPolicyManagerModel;

public interface IPolicyManagerDomain {
	/**
	 * @author pz
	 * @param model
	 * @return List<IEmployeeVMengModel>
	 * @description:查询列表
	 */
	public List<IPolicyManagerModel> policyQueryList(IPolicyManagerModel model);

	/**
	 * @author pz
	 * @param model
	 * @return IEmployeeVMengModel
	 * @description:查询列表
	 */
	public IPolicyManagerModel policyQueryMsg(IPolicyManagerModel model);

}
