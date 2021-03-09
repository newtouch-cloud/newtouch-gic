package com.newtouch.customerManager.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.newtouch.core.daobase.BaseDao;
import com.newtouch.customerManager.model.IPolicyManagerPersonModel;

@Component
public class PolicyManagerPersonDao extends BaseDao implements IPolicyManagerPersonDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<IPolicyManagerPersonModel> policyQueryList(IPolicyManagerPersonModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("policypersonmanager.inPolicyQuery_count", model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage() + 1);
		List<IPolicyManagerPersonModel> list = this.getSqlMapClientTemplate().queryForList("policypersonmanager.InPolicyQuery", model);
		return list;
	}

	@Override
	public IPolicyManagerPersonModel policyQueryMsg(IPolicyManagerPersonModel model) {

		IPolicyManagerPersonModel inPolicy = (IPolicyManagerPersonModel) this.getSqlMapClientTemplate().queryForObject("policypersonmanager.InPolicyMsg", model);
		return inPolicy;
	}
}
