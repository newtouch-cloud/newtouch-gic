package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.bo.IContractLifeBeneficiaryModel;
import com.ca.cacore.ibs.model.bo.IContractLifeHolderModel;
import com.ca.cacore.ibs.model.bo.IContractLifeInsurantModel;
import com.ca.cacore.ibs.model.vo.ContractLifeProductVOModel;
import com.ca.cacore.ibs.model.vo.ContractLifeSaveVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeVOModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class ContractLifeDao extends BaseDao implements IContractLifeDao {

	@Override
	public boolean addContractLifeHolder(IContractLifeHolderModel model) {
		this.getSqlMapClientTemplate().insert("contractLife.addContractLifeHolder",model);
		return true;
	}

	@Override
	public boolean addContractLifeInsured(IContractLifeInsurantModel model) {
		this.getSqlMapClientTemplate().insert("contractLife.addContractLifeInsured",model);
		return true;
	}

	@Override
	public boolean addContractLifeBeneficiary(IContractLifeBeneficiaryModel model) {
		this.getSqlMapClientTemplate().insert("contractLife.addContractLifeBeneficiary",model);
		return true;
	}

	@Override
	public boolean addContractLife(ContractLifeSaveVOModel model) {
		this.getSqlMapClientTemplate().insert("contractLife.addContractLife",model);
		return true;
	}

	@Override
	public boolean addContractLifeProduct(ContractLifeProductVOModel model) {
		this.getSqlMapClientTemplate().insert("contractLife.addContractLifeProduct",model);
		return true;
	}

	@Override
	public boolean addContractLifeProductPre(ContractLifeProductVOModel model) {
		this.getSqlMapClientTemplate().insert("contractLife.addContractLifePrem",model);
		return true;
	}

	@Override
	public boolean addContractLifeProductFee(ContractLifeProductVOModel model) {
		this.getSqlMapClientTemplate().insert("contractLife.addContractLifeProductFee",model);
		return true;
	}

	@Override
	public IContractLifeVOModel getContractLifeView(String policy_code) {
		return (IContractLifeVOModel) this.getSqlMapClientTemplate().queryForObject("contractLife.getContractLifeView",policy_code);
	}

	@Override
	public boolean modifyContractLife(ContractLifeSaveVOModel pl) {
		this.getSqlMapClientTemplate().update("contractLife.updateContractLife",pl);
		return true;
	}

	@Override
	public boolean modifycontractLifeHolder(IContractLifeHolderModel model) {
		this.getSqlMapClientTemplate().update("contractLife.updateContractLifeHolder",model);
		return true;
	}

	@Override
	public boolean modifyContractLifeInsured(IContractLifeInsurantModel model) {
		this.getSqlMapClientTemplate().update("contractLife.updateContractLifeInsurant",model);
		return true;
	}

	@Override
	public boolean modifyContractLifeBeneficiary(IContractLifeBeneficiaryModel model) {
		this.getSqlMapClientTemplate().update("contractLife.updateContractLifeBene",model);
		return true;
	}

	@Override
	public boolean modifyContractLifeProduct(ContractLifeProductVOModel model) {
		this.getSqlMapClientTemplate().update("contractLife.updateContractLifeProduct",model);
		return true;
	}

	@Override
	public boolean modifyContractLifePrem(ContractLifeProductVOModel model) {
		this.getSqlMapClientTemplate().update("contractLife.updateContractLifePrem",model);
		return true;
	}

	@Override
	public boolean modifyContractLifeProductFee(ContractLifeProductVOModel model) {
		this.getSqlMapClientTemplate().update("contractLife.updateContractLifeProductFee",model);
		return true;
	}

	@Override
	public boolean addContractLifeProductInsFee(ContractLifeProductVOModel model) {
		this.getSqlMapClientTemplate().insert("contractLife.addContractLifeInsFee",model);
		return true;
	}

	@Override
	public boolean modifyContractLifeProductInsFee(ContractLifeProductVOModel model) {
		this.getSqlMapClientTemplate().update("contractLife.updateContractLifeInsFee",model);
		return true;
	}
	
	public Integer getSeq_id(){
		return (Integer) this.getSqlMapClientTemplate().queryForObject("contractLife.getSeq_id");
	}

	@Override
	public boolean addContractLifeLog(ContractLifeSaveVOModel model) {
		this.getSqlMapClientTemplate().insert("contractLife.addContractLifeLog",model);
		return true;
	}

	@Override
	public boolean addContractLifeProductLog(ContractLifeProductVOModel model) {
		this.getSqlMapClientTemplate().insert("contractLife.addContractLifeProductLog",model);
		return true;
	}

	@Override
	public boolean addContractLifeProductInsFeeLog(ContractLifeProductVOModel model) {
		this.getSqlMapClientTemplate().insert("contractLife.addContractLifeInsFeeLog",model);
		return true;
	}

	@Override
	public boolean addContractLifeProductPreLog(ContractLifeProductVOModel model) {
		this.getSqlMapClientTemplate().insert("contractLife.addCntractLifePremLog",model);
		return true;
	}

	@Override
	public boolean addContractLifeProductFeeLog(ContractLifeProductVOModel model) {
		this.getSqlMapClientTemplate().insert("contractLife.addContractLifeProductFeeLog",model);
		return true;
	}
	

	@Override
	public List<ContractLifeProductVOModel> sumContractlifePrem(Long policy_id) {
		return  this.getSqlMapClientTemplate().queryForList("contractLife.sumContractlifePrem",policy_id);
	}
	
}
