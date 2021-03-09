package com.ca.cacore.ibs.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.dao.IPolicyLifeDao;
import com.ca.cacore.ibs.dao.IPolicyLifeManageDao;
import com.ca.cacore.ibs.model.bo.PolicyLifeBeneficiaryModel;
import com.ca.cacore.ibs.model.bo.PolicyLifeHolderModel;
import com.ca.cacore.ibs.model.bo.PolicyLifeInsuredModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProductFeePremVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProductVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeInfoVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeTypeSelect;
@Service
public class PolicyLifeDomain implements IPolicyLifeDomain{
	@Autowired private IPolicyLifeDao policyLifeDao;
	@Autowired private IPolicyLifeManageDao policyLifeManageDao;
	/**
	 * 增加客户基本信息保存方法
	 * @param CustomerModel
	 * @return 
	 */
	@Override
	public boolean addCustome(IPolicyLifePeopleVOModel model, IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return policyLifeDao.addCustome(model);
	}
	
	/**
	 * 增加客户联系信息保存方法
	 * @param CustomerModel
	 * @return 
	 */
	@Override
	public boolean addCustomerContactModel(IPolicyLifePeopleVOModel model, IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return policyLifeDao.addCustomerContactModel(model);
	}
	
	@Override
	public boolean addCustomerLog(IPolicyLifePeopleVOModel model, IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return policyLifeDao.addCustomerLog(model);
	}
	
	@Override
	public boolean modifyCustomer(IPolicyLifePeopleVOModel model, IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return policyLifeDao.modifyCustomer(model);
	}
	
	@Override
	public boolean addPolicyLifeHolder(PolicyLifeHolderModel model ,IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return policyLifeDao.addPolicyLifeHolder(model);
	}

	@Override
	public int getCustomeSeqId(String customer_id) {
		IPolicyLifePeopleVOModel vo=new PolicyLifePeopleVOModel();
		vo.setCustomer_id(customer_id);
		return policyLifeDao.getCustomeSeqId(customer_id);
	}

	@Override
	public boolean addPolicyLifeInsured(PolicyLifeInsuredModel model, IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		policyLifeDao.addPolicyLifeInsured(model);
		return true;
	}

	@Override
	public boolean addPolicyLifeBeneficiary(PolicyLifeBeneficiaryModel model, IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		policyLifeDao.addPolicyLifeBeneficiary(model);
		return true;
	}
	
	@Override
	public boolean addPolicyLife(PolicyLifeInfoVOModel model,IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return policyLifeDao.addPolicyLife(model);
	}
	
	@Override
	public boolean addPolicyLifeProduct(IPolicyLifeProductFeePremVOModel model,IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return policyLifeDao.addPolicyLifeProduct(model);
	}

	@Override
	public boolean addPolicyLifePrem(IPolicyLifeProductFeePremVOModel model,IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return policyLifeDao.addPolicyLifePrem(model);
	}

	@Override
	public boolean addPolicyLifeProductFee(IPolicyLifeProductFeePremVOModel model,IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return policyLifeDao.addPolicyLifeProductFee(model);
	}

	@Override
	public List<IPolicyLifePeopleVOModel> getInsurantList(Long policy_id) {
		return policyLifeManageDao.getInsurantList(policy_id);
	}

	@Override
	public List<IPolicyLifePeopleVOModel> getHolderList(Long policy_id) {
		return policyLifeManageDao.getHolderList(policy_id);
	}

	@Override
	public List<IPolicyLifePeopleVOModel> getBeneficiaryList(Long policy_id) {
		return policyLifeManageDao.getBeneficiaryList(policy_id);
	}

	@Override
	public List<IPolicyLifeProductVOModel> getProductList(Long policy_id) {
		return policyLifeManageDao.getProductList(policy_id);
	}

	@Override
	public boolean modifyPolicyLife(PolicyLifeInfoVOModel pl, IUserModel user) {
		pl.setModifyUser(user.getEmp_id());
		return policyLifeDao.modifyPolicyLife(pl);
	}

	@Override
	public boolean modifyPolicyLifeHolder(PolicyLifeHolderModel model,IUserModel user) {
		model.setModifyUser(user.getEmp_id());
		return policyLifeDao.modifyPolicyLifeHolder(model);
	}

	@Override
	public boolean modifyPolicyLifeInsured(PolicyLifeInsuredModel model,IUserModel user) {
		model.setModifyUser(user.getEmp_id());
		return policyLifeDao.modifyPolicyLifeInsured(model);
	}

	@Override
	public boolean modifyPolicyLifeBeneficiary(PolicyLifeBeneficiaryModel model, IUserModel user) {
		model.setModifyUser(user.getEmp_id());
		return policyLifeDao.modifyPolicyLifeBeneficiary(model);
	}

	@Override
	public boolean modifyPolicyLifeProduct(IPolicyLifeProductFeePremVOModel model, IUserModel user) {
		model.setModifyUser(user.getEmp_id());
		return policyLifeDao.modifyPolicyLifeProduct(model);
	}

	@Override
	public boolean modifyPolicyLifePrem(IPolicyLifeProductFeePremVOModel model,IUserModel user) {
		model.setModifyUser(user.getEmp_id());
		return policyLifeDao.modifyPolicyLifePrem(model);
	}

	@Override
	public boolean modifyPolicyLifeProductFee(IPolicyLifeProductFeePremVOModel model, IUserModel user) {
		model.setModifyUser(user.getEmp_id());
		return policyLifeDao.modifyPolicyLifeProductFee(model);
	}

	@Override
	public int getSeq_id() {
		return policyLifeDao.getSeq_id();
	}

	@Override
	public boolean addPolicyLifeLog(PolicyLifeInfoVOModel model, IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return policyLifeDao.addPolicyLifeLog(model);
	}

	@Override
	public boolean addPolicyLifeProductLog(IPolicyLifeProductFeePremVOModel model, IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return policyLifeDao.addPolicyLifeProductLog(model);
	}

	@Override
	public boolean addPolicyLifeProductFeeLog(IPolicyLifeProductFeePremVOModel model, IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return policyLifeDao.addPolicyLifeProductFeeLog(model);
	}

	@Override
	public boolean addPolicyLifeProductPremLog(IPolicyLifeProductFeePremVOModel model, IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return policyLifeDao.addPolicyLifeProductPremLog(model);
	}

	/** 
	* 获得产品可选缴费方式的信息  ss
	* @param model
	* @return List<String>
	* @description:
	*/
	@Override
	public List<String> getChargeType(IPolicyLifeProductFeePremVOModel model) {
		List<String> pList=new ArrayList<String>();
		List<PolicyLifeTypeSelect> list=policyLifeDao.getChargeType(model);
		for (PolicyLifeTypeSelect policyLifeTypeSelect : list) {
			String str="{'code':'"+policyLifeTypeSelect.getCharge_type_code()+"','name':'"+policyLifeTypeSelect.getCharge_type_name()+"'}";
			pList.add(str); 
		}
		return pList;
	}

	/** 
	* 获得产品可选缴费期限类型的信息  ss
	* @param model
	* @return List<String>
	* @description:
	*/
	@Override
	public List<String> getCharge_Period(IPolicyLifeProductFeePremVOModel model) {
		List<String> pList=new ArrayList<String>();
		List<PolicyLifeTypeSelect> list=policyLifeDao.getCharge_Period(model);
		for (PolicyLifeTypeSelect policyLifeTypeSelect : list) {
			String str="{'code':'"+policyLifeTypeSelect.getCharge_period_code()+"','name':'"+policyLifeTypeSelect.getCharge_period_name()+"'}";
			pList.add(str); 
		}
		return pList;
	}
	
	/** 
	* 获得产品可选保障期限类型的信息 ss
	* @param model
	* @return List<String>
	* @description:
	*/
	@Override
	public List<String> getCoveragePeriod(IPolicyLifeProductFeePremVOModel model) {
		List<String> pList=new ArrayList<String>();
		List<PolicyLifeTypeSelect> list=policyLifeDao.getCoveragePeriod(model);
		for (PolicyLifeTypeSelect policyLifeTypeSelect : list) {
			String str="{'code':'"+policyLifeTypeSelect.getCoverage_period_code()+"','name':'"+policyLifeTypeSelect.getCoverage_period_name()+"'}";
			pList.add(str); 
		}
		return pList;
	}

	@Override
	public List<IPolicyLifeProductFeePremVOModel> sumPolicylifePrem(Long policy_id) {
		return policyLifeDao.sumPolicylifePrem(policy_id);
	}

	@Override
	public boolean modifyPolicyLifeStatus(IPolicyLifeVOModel model,IUserModel user) {
		model.setModifyUser(user.getEmp_id());
		return policyLifeManageDao.modifyPolicyLifeStatus(model);
	}
}
