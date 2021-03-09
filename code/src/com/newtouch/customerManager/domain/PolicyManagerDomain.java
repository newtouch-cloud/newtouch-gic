package com.newtouch.customerManager.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.customerManager.dao.IPolicyManagerDao;
import com.newtouch.customerManager.model.IPolicyManagerModel;

@Service
public class PolicyManagerDomain implements IPolicyManagerDomain {
	@Autowired
	private IPolicyManagerDao policyDo;
	
	@Override
	public List<IPolicyManagerModel> policyQueryList(IPolicyManagerModel model) {
		return policyDo.policyQueryList(model);
	}

	@Override
	public IPolicyManagerModel policyQueryMsg(IPolicyManagerModel model) {

		return policyDo.policyQueryMsg(model);
	}

}
