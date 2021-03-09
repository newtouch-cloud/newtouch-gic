package com.newtouch.customerManager.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.customerManager.dao.IPolicyManagerPersonDao;
import com.newtouch.customerManager.model.IPolicyManagerPersonModel;

@Service
public class PolicyManagerPersonDomain implements IPolicyManagerPersonDomain {
	@Autowired
	private IPolicyManagerPersonDao policyDo;

	@Override
	public List<IPolicyManagerPersonModel> policyQueryList(IPolicyManagerPersonModel model) {

		return policyDo.policyQueryList(model);
	}

	@Override
	public IPolicyManagerPersonModel policyQueryMsg(IPolicyManagerPersonModel model) {

		return policyDo.policyQueryMsg(model);
	}

}
