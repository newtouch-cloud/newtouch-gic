package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.vo.ContractLifeVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;
import com.ca.cacore.csm.model.vo.CustomerVOModel;
import com.ca.cacore.msss.model.bo.IProductLlifeModel;
import com.newtouch.core.daobase.BaseDao;
@SuppressWarnings("unchecked")
@Component
public class CommonAsynRequestDao extends BaseDao implements ICommonAsynRequestDao{

	@Override
	public IPolicyLifePeopleVOModel getSalesInfo(String sales_id) {
		return (IPolicyLifePeopleVOModel) this.getSqlMapClientTemplate().queryForObject("asynRequest.getSalesInfo", sales_id);
	}

	@Override
	public IProductLlifeModel getProductInfo(IProductLlifeModel model) {
		return (IProductLlifeModel) this.getSqlMapClientTemplate().queryForObject("asynRequest.getProductInfo", model);
	}

	@Override
	public List<IPolicyLifePeopleVOModel> getCustomerInfo(IPolicyLifePeopleVOModel plp) {
		return this.getSqlMapClientTemplate().queryForList("asynRequest.getCustomerInfo",plp);
	}

	@Override
	public IPolicyLifeVOModel validateSendCode(IPolicyLifeVOModel model) {
		return (IPolicyLifeVOModel) this.getSqlMapClientTemplate().queryForObject("asynRequest.validateSendCodeRepeat", model);
	}

	@Override
	public ContractLifeVOModel validatePolicyCode(ContractLifeVOModel model) {
		return (ContractLifeVOModel) this.getSqlMapClientTemplate().queryForObject("asynRequest.validatePolicyCodeRepeat", model);
	}
	
	@Override
	public String getBranchID() {
		return (String) this.getSqlMapClientTemplate().queryForObject("asynRequest.getBranchID");
	}
	
	public CustomerVOModel getCustomerInfoByCusBranID(CustomerVOModel model){
		return (CustomerVOModel) this.getSqlMapClientTemplate().queryForObject("asynRequest.getCustomerInfoByBCId",model);
	};
}
