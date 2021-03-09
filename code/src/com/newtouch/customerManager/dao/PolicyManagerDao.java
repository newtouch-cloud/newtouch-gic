package com.newtouch.customerManager.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.newtouch.core.daobase.BaseDao;
import com.newtouch.customerManager.model.IPolicyManagerModel;

@Component
public class PolicyManagerDao extends BaseDao implements IPolicyManagerDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<IPolicyManagerModel> policyQueryList(IPolicyManagerModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("policymanager.inPolicyQuery_count", model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage() + 1);
		List<IPolicyManagerModel> list = this.getSqlMapClientTemplate().queryForList("policymanager.InPolicyQuery", model);
		return list;
	}

	@Override
	public IPolicyManagerModel policyQueryMsg(IPolicyManagerModel model) {
		IPolicyManagerModel inPolicy = (IPolicyManagerModel) this.getSqlMapClientTemplate().queryForObject("policymanager.InPolicyMsg", model);
		return inPolicy;
	}
}
