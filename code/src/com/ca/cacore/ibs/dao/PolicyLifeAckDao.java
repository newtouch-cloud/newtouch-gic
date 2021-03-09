package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.bo.IContractLifeModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeAckVOModel;
import com.newtouch.core.daobase.BaseDao;
@SuppressWarnings("unchecked")
@Component
public class PolicyLifeAckDao extends BaseDao implements IPolicyLifeAckDao{

	@Override
	public boolean addPolicyLifeAck(IPolicyLifeAckVOModel pla) {
		this.getSqlMapClientTemplate().insert("policyLifeAck.addPolicyLifeAck",pla);
		return true;
	}

	@Override
	public List<IPolicyLifeAckVOModel> queryPolicyLifeAckList(IPolicyLifeAckVOModel cm) {
		Integer count  = (Integer) this.getSqlMapClientTemplate().queryForObject("policyLifeAck.queryPolicyLifeAck_count",cm);
		cm.getPageCount().setAllRows(count);
		cm.getPageCount().calcPage();
		cm.getPageCount().setNowPage(cm.getPageCount().getNowPage()+1);
		return this.getSqlMapClientTemplate().queryForList("policyLifeAck.queryPolicyLifeAck", cm);
	}

	@Override
	public IPolicyLifeAckVOModel getPolicyLifeAckViewr(Integer seq_id) {
		return (IPolicyLifeAckVOModel) this.getSqlMapClientTemplate().queryForObject("policyLifeAck.getPolicyLifeAck",seq_id);
	}

	@Override
	public boolean modifyConstractLife(IContractLifeModel cm) {
		this.getSqlMapClientTemplate().update("policyLifeAck.updateConstractLife",cm);
		return true;
	}

	@Override
	public boolean modifyPolicyLifeAck(IPolicyLifeAckVOModel pa) {
		this.getSqlMapClientTemplate().update("policyLifeAck.updatePolicyLifeAck",pa);
		return true;
	}

	@Override
	public IContractLifeModel getContractLife(IContractLifeModel pl) {
		return (IContractLifeModel) this.getSqlMapClientTemplate().queryForObject("policyLifeAck.getContractLife",pl);
	}
}
