package com.newtouch.customerManager.webapp.service;

import java.util.List;

import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.customerManager.model.IPolicyManagerPersonModel;

public interface IPolicyManagerPersonService {
	// 查询人员列表
	public ReturnMsg policyQueryList(IPolicyManagerPersonModel model);

	// 查询人员
	public ReturnMsg policyQueryMsg(IPolicyManagerPersonModel model);

	List<IPolicyManagerPersonModel> policyQueryForExcel(IPolicyManagerPersonModel model);

}
