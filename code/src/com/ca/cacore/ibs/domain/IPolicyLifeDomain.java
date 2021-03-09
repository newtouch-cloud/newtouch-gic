package com.ca.cacore.ibs.domain;

import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.bo.PolicyLifeBeneficiaryModel;
import com.ca.cacore.ibs.model.bo.PolicyLifeHolderModel;
import com.ca.cacore.ibs.model.bo.PolicyLifeInsuredModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProductFeePremVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProductVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeInfoVOModel;

/**
 * 保单、投保单 人员客户保存
 * @author szl
 *
 */
public interface IPolicyLifeDomain {
	/**
	 * 增加客户基本信息保存方法
	 * @param CustomerModel
	 * @return 
	 */
	public boolean addCustome(IPolicyLifePeopleVOModel CustomerModel , IUserModel user) ;
	
	/**
	 * 增加客户联系信息
	 * @param CustomerContactModel
	 * @return boolean
	 */
	public boolean addCustomerContactModel(IPolicyLifePeopleVOModel model, IUserModel user);
	
	/**
	 * 修改客户信息
	 * @param model
	 * @return
	 */
	public boolean modifyCustomer(IPolicyLifePeopleVOModel model, IUserModel user);
	/**
	 * 添加客户日志信息
	 * @param model
	 * @return
	 */
	public boolean addCustomerLog(IPolicyLifePeopleVOModel model, IUserModel user) ;
	/**
	 *  寿险投保单投保人信息保存方法
	 * @param IPolicyLifeHolderModel
	 * @return  boolean
	 */
	public boolean addPolicyLifeHolder(PolicyLifeHolderModel model,IUserModel user);
	/**
	 * 根据客户id查询对应的数据
	 * @param IPolicyLifeHolderModel
	 * @return  CustomerAndContactViewVOModel
	 */
	public int getCustomeSeqId(String customer_id);
	/**
	 *  被保险人信息保存方法
	 * @param IPolicyLifeHolderModel
	 * @return  boolean
	 */
	public boolean addPolicyLifeInsured(PolicyLifeInsuredModel model, IUserModel user);
	/**
	 *  受益人信息保存方法
	 * @param PolicyLifeBeneficiaryModel
	 * @return  boolean
	 */
	public boolean addPolicyLifeBeneficiary(PolicyLifeBeneficiaryModel model, IUserModel user);
	/**
	 *  投保单信息保存方法
	 * @param PolicyLifeModel
	 * @return  boolean
	 */
	public boolean addPolicyLife(PolicyLifeInfoVOModel model,IUserModel user );
	/**
	 *  险种保存方法
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean addPolicyLifeProduct(IPolicyLifeProductFeePremVOModel model,IUserModel user);
	
	/**
	 *  添加投保单费用信息
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean addPolicyLifePrem(IPolicyLifeProductFeePremVOModel model,IUserModel user);
	
	/**
	 *  添加投保单费用分险种明细信息
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean addPolicyLifeProductFee(IPolicyLifeProductFeePremVOModel model,IUserModel user);
	
	/**
	 * 查询被保人列表
	 * @param policy_id
	 * @return
	 */
	public List<IPolicyLifePeopleVOModel> getInsurantList(Long policy_id) ;
	
	/**
	 * 查询投保人列表
	 * @param policy_id
	 * @return
	 */
	public  List<IPolicyLifePeopleVOModel> getHolderList(Long policy_id) ;
	
	/**
	 * 查询受益人列表
	 * @param policy_id
	 * @return
	 */
	public  List<IPolicyLifePeopleVOModel> getBeneficiaryList(Long policy_id) ;
	
	/**
	 * 得到险种的列表
	 * @param customer
	 * @return
	 */
	public List<IPolicyLifeProductVOModel> getProductList(Long policy_id);
	
	/**
	 * 更改投保单信息
	 * @param pl
	 * @return
	 */
	public boolean modifyPolicyLife(PolicyLifeInfoVOModel pl,IUserModel user);
	
	/**
	 * 修改投保人
	 * @param model
	 * @return
	 */
	public boolean modifyPolicyLifeHolder(PolicyLifeHolderModel model,IUserModel user);
	
	/**
	 *  被保险人信息修改方法
	 * @param IPolicyLifeHolderModel
	 * @return  boolean
	 */
	public boolean modifyPolicyLifeInsured(PolicyLifeInsuredModel model,IUserModel user);
	
	/**
	 *  受益人信息修改方法
	 * @param PolicyLifeBeneficiaryModel
	 * @return  boolean
	 */
	public boolean modifyPolicyLifeBeneficiary(PolicyLifeBeneficiaryModel model,IUserModel user);
	
	/**
	 *  险种修改方法
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean modifyPolicyLifeProduct(IPolicyLifeProductFeePremVOModel model,IUserModel user);
	
	/**
	 *  修改投保单费用信息
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean modifyPolicyLifePrem(IPolicyLifeProductFeePremVOModel model,IUserModel user);

	/**
	 *  修改投保单费用分险种明细信息
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean modifyPolicyLifeProductFee(IPolicyLifeProductFeePremVOModel model,IUserModel user);
	
	/**
	 * 得到seq_id
	 * @return
	 */
	public int getSeq_id() ;
	/**
	 *  投保单日志保存方法
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean addPolicyLifeLog(PolicyLifeInfoVOModel model,IUserModel user);
	/**
	 *  险种日志保存方法
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean addPolicyLifeProductLog(IPolicyLifeProductFeePremVOModel model,IUserModel user);
	/**
	 * 寿险投保单费用分险种明细Log信息
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean addPolicyLifeProductFeeLog(IPolicyLifeProductFeePremVOModel model,IUserModel user);
	/**
	 *  寿险投保单费用Log信息
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean addPolicyLifeProductPremLog(IPolicyLifeProductFeePremVOModel model,IUserModel user);
	
	/** 
	* 获得产品可选缴费方式的信息  ss
	* @param model
	* @return List<String>
	* @description:
	*/
	public List<String> getChargeType(IPolicyLifeProductFeePremVOModel model);
	/** 
	* 获得产品可选缴费期限类型的信息  ss
	* @param model
	* @return List<String>
	* @description:
	*/
	public List<String> getCharge_Period(IPolicyLifeProductFeePremVOModel model);
	
	/** 
	* 获得产品可选保障期限类型的信息 ss
	* @param model
	* @return List<String>
	* @description:
	*/
	public List<String> getCoveragePeriod(IPolicyLifeProductFeePremVOModel model);
	
	/**
	 * 汇总被保人对应的保费
	 * @param policy_id
	 * @return
	 */
	public List<IPolicyLifeProductFeePremVOModel> sumPolicylifePrem(Long policy_id);
	
	/**
	 * 投保单录入完根据核心校验：修改投保单的状态
	* @param model
	* @param user
	* @return boolean
	* @description:
	 */
	public boolean modifyPolicyLifeStatus(IPolicyLifeVOModel model,IUserModel user) ;
	
}
