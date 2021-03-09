package com.newtouch.customerManager.webapp.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.domain.ICmainPolicyDomain;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.customerManager.domain.IPolicyManagerDomain;
import com.newtouch.customerManager.model.IPolicyManagerModel;
import com.newtouch.customerManager.model.IPolicyManagerPersonModel;

@Service
public class PolicyManagerService implements IPolicyManagerService {
	@Autowired
	private IPolicyManagerDomain policyDomain;
	@Autowired
	private ICmainPolicyDomain cmainPolicyDomain;
	
	@Override
	public ReturnMsg policyQueryList(IPolicyManagerModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IPolicyManagerModel> relist = policyDomain.policyQueryList(model);
		HashMap<String,String> interMap = cmainPolicyDomain.findCodeToMap("ins_cmain.inter_flag"); 
		HashMap<String,String> businessMap =  cmainPolicyDomain.findCodeToMap("ins_cmain.business_flag");
		for (int i = 0; i < relist.size(); i++) {
			IPolicyManagerModel model2 = relist.get(i);
			model2.setInter_flag(interMap.get(model2.getInter_flag()));
			model2.setBusiness_flag(businessMap.get(model2.getBusiness_flag()));
			
		}
		returnMsg.setDataList(TransHelper.list2MapList(relist));
		return returnMsg;
	}
	
	@Override
	public  List<IPolicyManagerModel> policyQueryForExcel(IPolicyManagerModel model) {
		return policyDomain.policyQueryList(model);
	}

	@Override
	public ReturnMsg policyQueryMsg(IPolicyManagerModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		IPolicyManagerModel Policy = policyDomain.policyQueryMsg(model);
		returnMsg.setDataTable(TransHelper.obj2map(Policy));
		return returnMsg;
	}

}
