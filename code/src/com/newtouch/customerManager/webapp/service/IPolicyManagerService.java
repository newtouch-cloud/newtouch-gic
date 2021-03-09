package com.newtouch.customerManager.webapp.service;

import java.util.List;

import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.customerManager.model.IPolicyManagerModel;

public interface IPolicyManagerService {
	// 查询人员列表
	public ReturnMsg policyQueryList(IPolicyManagerModel model);

	// 查询人员
	public ReturnMsg policyQueryMsg(IPolicyManagerModel model);

	List<IPolicyManagerModel> policyQueryForExcel(IPolicyManagerModel model);

}
