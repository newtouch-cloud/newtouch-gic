package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.bo.PolicyLifeBeneficiaryModel;
import com.ca.cacore.ibs.model.bo.PolicyLifeHolderModel;
import com.ca.cacore.ibs.model.bo.PolicyLifeInsuredModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProductFeePremVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeInfoVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeTypeSelect;
import com.newtouch.core.daobase.BaseDao;
@Component
public class PolicyLifeDao extends BaseDao implements IPolicyLifeDao{

	@Override
	public boolean addCustome(IPolicyLifePeopleVOModel CustomerModel) {
		this.getSqlMapClientTemplate().insert("PolicyLife.addCustome",CustomerModel);
		return true;
	}
	
	@Override
	public boolean addCustomerContactModel(IPolicyLifePeopleVOModel model) {
		this.getSqlMapClientTemplate().insert("PolicyLife.addCustomerContactModel",model);
		return true;
	}
	
	@Override
	public boolean addCustomerLog(IPolicyLifePeopleVOModel model) {
		this.getSqlMapClientTemplate().insert("PolicyLife.addCustomerLog",model);
		return true;
	}
	
	/**
	 *  寿险投保单投保人信息保存方法
	 * @param IPolicyLifeHolderModel
	 * @return  boolean
	 */
	@Override
	public boolean addPolicyLifeHolder(PolicyLifeHolderModel model) {
		this.getSqlMapClientTemplate().insert("PolicyLife.addPolicyLifeHolder",model);
		return true;
	}
	
	@Override
	public boolean modifyCustomer(IPolicyLifePeopleVOModel model) {
		this.getSqlMapClientTemplate().update("PolicyLife.updateCustomerInfo",model);
		return true;
	}
	
	/**
	 * 根据客户id查询对应的数据
	 * @param IPolicyLifeHolderModel
	 * @return  CustomerAndContactViewVOModel
	 */
	@Override
	public int getCustomeSeqId(String customer_id) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("PolicyLife.getCustomerSeq_id", customer_id);
	}
	
	/**
	 *  被保险人信息保存方法
	 * @param IPolicyLifeHolderModel
	 * @return  boolean
	 */
	@Override
	public boolean addPolicyLifeInsured(PolicyLifeInsuredModel model) {
		 this.getSqlMapClientTemplate().insert("PolicyLife.addPolicyLifeInsured",model);
		return true;
	}
	
	/**
	 *  受益人信息保存方法
	 * @param PolicyLifeBeneficiaryModel
	 * @return  boolean
	 */
	@Override
	public boolean addPolicyLifeBeneficiary(PolicyLifeBeneficiaryModel model) {
		 this.getSqlMapClientTemplate().insert("PolicyLife.addPolicyLifeBeneficiary",model);
		return true;
	}
	
	@Override
	public boolean addPolicyLife(PolicyLifeInfoVOModel model) {
		 this.getSqlMapClientTemplate().insert("PolicyLife.addPolicyLife",model);
		return true;
	}
	
	@Override
	public boolean addPolicyLifeProduct(IPolicyLifeProductFeePremVOModel model) {
		this.getSqlMapClientTemplate().insert("PolicyLife.addPolicyLifeProduct",model);
		return true;
	}
	@Override
	public boolean addPolicyLifePrem(IPolicyLifeProductFeePremVOModel model) {
		this.getSqlMapClientTemplate().insert("PolicyLife.addPolicyLifePrem",model);
		return true;
	}
	@Override
	public boolean addPolicyLifeProductFee(IPolicyLifeProductFeePremVOModel model) {
		this.getSqlMapClientTemplate().insert("PolicyLife.addPolicyLifeProductFee",model);
		return true;
	}

	@Override
	public PolicyLifeInfoVOModel getPolicyLifeView(Long policy_id) {
		return (PolicyLifeInfoVOModel) this.getSqlMapClientTemplate().queryForObject("PolicyLife.getPolicyLifeView",policy_id);
	}

	@Override
	public boolean modifyPolicyLife(PolicyLifeInfoVOModel pl) {
		this.getSqlMapClientTemplate().update("PolicyLife.updatePolicyLife",pl);
		return true;
	}

	@Override
	public boolean modifyPolicyLifeHolder(PolicyLifeHolderModel model) {
		this.getSqlMapClientTemplate().update("PolicyLife.updateHolder",model);
		return true;
	}

	@Override
	public boolean modifyPolicyLifeInsured(PolicyLifeInsuredModel model) {
		this.getSqlMapClientTemplate().update("PolicyLife.updateInsurant",model);
		return true;
	}

	@Override
	public boolean modifyPolicyLifeBeneficiary(PolicyLifeBeneficiaryModel model) {
		this.getSqlMapClientTemplate().update("PolicyLife.updateBene",model);
		return true;
	}

	@Override
	public boolean modifyPolicyLifeProduct(IPolicyLifeProductFeePremVOModel model) {
		this.getSqlMapClientTemplate().update("PolicyLife.updateProduct",model);
		return true;
	}

	@Override
	public boolean modifyPolicyLifePrem(IPolicyLifeProductFeePremVOModel model) {
		this.getSqlMapClientTemplate().update("PolicyLife.updatePolicyLifePrem",model);
		return true;
	}

	@Override
	public boolean modifyPolicyLifeProductFee(IPolicyLifeProductFeePremVOModel model) {
		this.getSqlMapClientTemplate().update("PolicyLife.updatePolicyLifeProductFee",model);
		return true;
	}

	@Override
	public int getSeq_id() {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("PolicyLife.getSeq_id");
	}

	@Override
	public boolean addPolicyLifeLog(PolicyLifeInfoVOModel model) {
		   this.getSqlMapClientTemplate().insert("PolicyLife.addPolicyLifeLog",model);
		   return true;
	}

	@Override
	public boolean addPolicyLifeProductLog(IPolicyLifeProductFeePremVOModel model) {
		   this.getSqlMapClientTemplate().insert("PolicyLife.addProductLog",model);
		   return true;
	}

	@Override
	public boolean addPolicyLifeProductFeeLog(IPolicyLifeProductFeePremVOModel model) {
	       this.getSqlMapClientTemplate().insert("PolicyLife.addPolicyLifeProductFeeLog",model);
		   return true;
	}

	@Override
	public boolean addPolicyLifeProductPremLog(IPolicyLifeProductFeePremVOModel model) {
		   this.getSqlMapClientTemplate().insert("PolicyLife.addPolicyLifePremLog",model);
		   return true;
	}
	
	/** 
	* 获得产品可选缴费方式的信息  ss
	* @param model
	* @return List<PolicyLifeTypeSelect>
	* @description:用于保单录入时险种的动态下拉选
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<PolicyLifeTypeSelect> getChargeType(IPolicyLifeProductFeePremVOModel model) {
		return this.getSqlMapClientTemplate().queryForList("PolicyLife.queryProductChargeType",model);
	}

	/** 
	* 获得产品可选缴费期限类型的信息  ss
	* @param model
	* @return List<PolicyLifeTypeSelect>
	* @description:用于保单录入时险种的动态下拉选
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<PolicyLifeTypeSelect> getCharge_Period(IPolicyLifeProductFeePremVOModel model) {
		return this.getSqlMapClientTemplate().queryForList("PolicyLife.queryProductChargePeriod",model);
	}

	/** 
	* 获得产品可选保障期限类型的信息 ss
	* @param model
	* @return List<PolicyLifeTypeSelect>
	* @description:用于保单录入时险种的动态下拉选
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<PolicyLifeTypeSelect> getCoveragePeriod(IPolicyLifeProductFeePremVOModel model) {
		return this.getSqlMapClientTemplate().queryForList("PolicyLife.queryProductCoveragePeriod",model);
	}

	@Override
	public List<IPolicyLifeProductFeePremVOModel> sumPolicylifePrem(Long policy_id) {
		return  this.getSqlMapClientTemplate().queryForList("PolicyLife.sumPolicylifePrem",policy_id);
	}

}
