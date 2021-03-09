package com.ca.cacore.ibs.dao;

import java.util.List;

import com.ca.cacore.ibs.model.bo.PolicyLifeBeneficiaryModel;
import com.ca.cacore.ibs.model.bo.PolicyLifeHolderModel;
import com.ca.cacore.ibs.model.bo.PolicyLifeInsuredModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProductFeePremVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeInfoVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeTypeSelect;


public interface IPolicyLifeDao {
	/**
	 * 增加客户基本信息保存方法
	 * @param CustomerModel
	 * @return Customer_Id
	 */
	public boolean addCustome(IPolicyLifePeopleVOModel model) ;
	
	/**
	 * 修改客户基本信息保存方法
	 * @param CustomerModel
	 * @return Customer_Id
	 */
	public boolean modifyCustomer(IPolicyLifePeopleVOModel model) ;
	/**
	 * 增加客户联系信息保存方法
	 * @param CustomerModel
	 * @return 
	 */
	public boolean addCustomerContactModel(IPolicyLifePeopleVOModel model);
	
	/**
	 * 添加客户日志表信息
	 * @param model
	 * @return
	 */
	public boolean addCustomerLog(IPolicyLifePeopleVOModel model) ;
	
	/**
	 *  寿险投保单投保人信息保存方法
	 * @param IPolicyLifeHolderModel
	 * @return  boolean
	 */
	public boolean addPolicyLifeHolder(PolicyLifeHolderModel model);
	/**
	 * 根据客户id查询对应的数据
	 * @param IPolicyLifeHolderModel
	 * @return  int
	 */
	public int getCustomeSeqId(String customer_id);
	/**
	 *  被保险人信息保存方法
	 * @param IPolicyLifeHolderModel
	 * @return  boolean
	 */
	public boolean addPolicyLifeInsured(PolicyLifeInsuredModel model);
	/**
	 *  受益人信息保存方法
	 * @param PolicyLifeBeneficiaryModel
	 * @return  boolean
	 */
	public boolean addPolicyLifeBeneficiary(PolicyLifeBeneficiaryModel model);
	/**
	 *  投保单保存方法
	 * @param PolicyLifeModel
	 * @return  boolean
	 */
	public boolean addPolicyLife(PolicyLifeInfoVOModel model );
	/**
	 *  险种保存方法
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean addPolicyLifeProduct(IPolicyLifeProductFeePremVOModel model);
	
	/**
	 *  添加投保单费用信息
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean addPolicyLifePrem(IPolicyLifeProductFeePremVOModel model);
	

	/**
	 *  添加投保单费用分险种明细信息
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean addPolicyLifeProductFee(IPolicyLifeProductFeePremVOModel model);
	
	/**
	 * 查询投保单明细
	 * @param policy_id
	 * @return
	 */
	public PolicyLifeInfoVOModel getPolicyLifeView(Long policy_id);
	
	/**
	 * 更改投保单信息
	 * @param pl
	 * @return
	 */
	public boolean modifyPolicyLife(PolicyLifeInfoVOModel pl);
	
	/**
	 * 修改投保人
	 * @param model
	 * @return
	 */
	public boolean modifyPolicyLifeHolder(PolicyLifeHolderModel model);
	
	/**
	 *  被保险人信息修改方法
	 * @param IPolicyLifeHolderModel
	 * @return  boolean
	 */
	public boolean modifyPolicyLifeInsured(PolicyLifeInsuredModel model);
	
	/**
	 *  受益人信息修改方法
	 * @param PolicyLifeBeneficiaryModel
	 * @return  boolean
	 */
	public boolean modifyPolicyLifeBeneficiary(PolicyLifeBeneficiaryModel model);
	
	/**
	 *  险种修改方法
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean modifyPolicyLifeProduct(IPolicyLifeProductFeePremVOModel model);
	
	/**
	 *  修改投保单费用信息
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean modifyPolicyLifePrem(IPolicyLifeProductFeePremVOModel model);

	/**
	 *  修改投保单费用分险种明细信息
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean modifyPolicyLifeProductFee(IPolicyLifeProductFeePremVOModel model);
	
	/**
	 * 得到seq_id
	 */
	public int getSeq_id();
	/**
	 *  投保单日志保存方法
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean addPolicyLifeLog(PolicyLifeInfoVOModel model);
	/**
	 *  险种日志保存方法
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean addPolicyLifeProductLog(IPolicyLifeProductFeePremVOModel model);
	/**
	 * 寿险投保单费用分险种明细Log信息
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean addPolicyLifeProductFeeLog(IPolicyLifeProductFeePremVOModel model);
	/**
	 *  寿险投保单费用Log信息
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean addPolicyLifeProductPremLog(IPolicyLifeProductFeePremVOModel model);
	/** 
	* 获得产品可选缴费方式的信息
	* @param model
	* @return List<PolicyLifeTypeSelect>
	* @description:用于保单录入时险种的动态下拉选
	*/
	public List<PolicyLifeTypeSelect> getChargeType(IPolicyLifeProductFeePremVOModel model);
	
	/** 
	* 获得产品可选缴费期限类型的信息
	* @param model
	* @return List<PolicyLifeTypeSelect>
	* @description:用于保单录入时险种的动态下拉选
	*/
	public List<PolicyLifeTypeSelect> getCharge_Period(IPolicyLifeProductFeePremVOModel model);
	
	/** 
	* 获得产品可选保障期限类型的信息
	* @param model
	* @return List<PolicyLifeTypeSelect>
	* @description:用于保单录入时险种的动态下拉选
	*/
	public List<PolicyLifeTypeSelect> getCoveragePeriod(IPolicyLifeProductFeePremVOModel model); 
	
	/**
	 * 汇总被保人对应的保费
	 * @param policy_id
	 * @return
	 */
	public List<IPolicyLifeProductFeePremVOModel> sumPolicylifePrem(Long policy_id);
}
