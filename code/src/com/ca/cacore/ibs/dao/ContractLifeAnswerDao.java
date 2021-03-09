package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.bo.IContractLifeAnswerModel;
import com.ca.cacore.ibs.model.bo.IContractLifeModel;
import com.ca.cacore.ibs.model.bo.IPolicyLifeModel;
import com.ca.cacore.ibs.model.vo.IContractLifeAnswerVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeVOModel;
import com.newtouch.core.daobase.BaseDao;

@SuppressWarnings("unchecked")
@Component
public class ContractLifeAnswerDao extends BaseDao implements IContractLifeAnswerDao{

	@Override
	public boolean addPolicyAnswer(IContractLifeAnswerModel pam) {
		this.getSqlMapClientTemplate().insert("policyAnswer.addPolicyAnswer",pam);
		return true;
	}

	@Override
	public boolean modifyConstractMaster(IContractLifeModel cm) {
		this.getSqlMapClientTemplate().update("policyAnswer.updateConstractMaster",cm);
		return true;
	}

	@Override
	public IContractLifeModel getConstractMaster(IContractLifeModel cm) {
		return (IContractLifeModel) this.getSqlMapClientTemplate().queryForObject("policyAnswer.getContractMaster",cm);
	}
	
	@Override
	public IContractLifeAnswerVOModel getPolicyAnswer(IContractLifeAnswerVOModel cm) {
		return (IContractLifeAnswerVOModel) this.getSqlMapClientTemplate().queryForObject("policyAnswer.getPolicyAnswer",cm);
	}
	
	@Override
	public boolean modifyPolicyAnswer(IContractLifeAnswerVOModel pa) {
		this.getSqlMapClientTemplate().update("policyAnswer.updatePolicyAnswer",pa);
		return true;
	}
	
	@Override
	public List<IContractLifeVOModel> queryPolicyAnswerInfo(IContractLifeVOModel cm) {
		Integer count  = (Integer) this.getSqlMapClientTemplate().queryForObject("policyAnswer.queryContractMaster_count",cm);
		cm.getPageCount().setAllRows(count);
		cm.getPageCount().calcPage();
		cm.getPageCount().setNowPage(cm.getPageCount().getNowPage()+1);
		return (List<IContractLifeVOModel>) this.getSqlMapClientTemplate().queryForList("policyAnswer.queryContractMaster",cm);
	}
	
	@Override
	public IPolicyLifeModel getPolicyLife(IPolicyLifeModel pl) {
		return (IPolicyLifeModel) this.getSqlMapClientTemplate().queryForObject("policyAnswer.getPolicyLife",pl);
	}

	@Override
	public boolean modifyPolicyLife(IPolicyLifeModel pl) {
		this.getSqlMapClientTemplate().update("policyAnswer.updatePolicyLife",pl);
		return true;
	}
}
