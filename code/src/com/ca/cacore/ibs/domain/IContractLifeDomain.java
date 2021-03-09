package com.ca.cacore.ibs.domain;

import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.bo.IContractLifeBeneficiaryModel;
import com.ca.cacore.ibs.model.bo.IContractLifeHolderModel;
import com.ca.cacore.ibs.model.bo.IContractLifeInsurantModel;
import com.ca.cacore.ibs.model.vo.ContractLifeProductVOModel;
import com.ca.cacore.ibs.model.vo.ContractLifeSaveVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;


public interface IContractLifeDomain {
	
	/**
	 *  寿险投保单投保人信息保存方法
	 * @param IPolicyLifeHolderModel
	 * @return  boolean
	 */
	public boolean addContractLifeHolder(IContractLifeHolderModel model,IUserModel user);
	/**
	 *  被保险人信息保存方法
	 * @param 
	 * @return  boolean
	 */
	public boolean addContractLifeInsured(IContractLifeInsurantModel model,IUserModel user);
	
	/**
	 *  受益人信息保存方法
	 * @param 
	 * @return  boolean
	 */
	public boolean addContractLifeBeneficiary(IContractLifeBeneficiaryModel model,IUserModel user);
	
	/**
	 * 添加寿险保单手续费分险种明细信息
	 * @param model
	 * @return
	 */
	public boolean addContractLifeProductInsFee(ContractLifeProductVOModel model,IUserModel user);
	
	/**
	 * 
	 *  保单信息保存方法
	 * @param 
	 * @return  boolean
	 */
	public boolean addContractLife(ContractLifeSaveVOModel model,IUserModel user );
	
	/**
	 *  险种保存方法
	 * @param 
	 * @return  boolean
	 */
	public boolean addContractLifeProduct(ContractLifeProductVOModel model,IUserModel user);
	
	/**
	 *  险种费用保存方法
	 * @param
	 * @return  boolean
	 */
	public boolean addContractLifeProductPre(ContractLifeProductVOModel model,IUserModel user);
	
	/**
	 *  险种费用明细保存方法
	 * @param 
	 * @return  boolean
	 */
	public boolean addContractLifeProductFee(ContractLifeProductVOModel model,IUserModel user);
	
	/**
	 *  保单信息保存方法
	 * @param 
	 * @return  boolean
	 */
	public IContractLifeVOModel getContractLifeView(IContractLifeVOModel cl);
	
	/**
	 * 更改保单信息
	 * @param pl
	 * @return
	 */
	public boolean modifyContractLife(ContractLifeSaveVOModel pl,IUserModel user);
	
	/**
	 * 修改投保人
	 * @param model
	 * @return
	 */
	public boolean modifycontractLifeHolder(IContractLifeHolderModel model,IUserModel user);
	
	/**
	 *  被保险人信息修改方法
	 * @param IPolicyLifeHolderModel
	 * @return  boolean
	 */
	public boolean modifyContractLifeInsured(IContractLifeInsurantModel model,IUserModel user);
	
	/**
	 *  受益人信息修改方法
	 * @param PolicyLifeBeneficiaryModel
	 * @return  boolean
	 */
	public boolean modifyContractLifeBeneficiary(IContractLifeBeneficiaryModel model,IUserModel user);
	
	/**
	 *  险种修改方法
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean modifyContractLifeProduct(ContractLifeProductVOModel model,IUserModel user);
	
	/**
	 *  修改保单费用信息
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean modifyContractLifePrem(ContractLifeProductVOModel model,IUserModel user);

	/**
	 *  修改保单费用分险种明细信息
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean modifyContractLifeProductFee(ContractLifeProductVOModel model,IUserModel user);
	
	/**
	 *  寿险保单手续费分险种明细信息修改方法
	 * @param 
	 * @return  boolean
	 */
	public boolean modifyContractLifeProductInsFee(ContractLifeProductVOModel model,IUserModel user);
	
	public Integer getSeq_id();
	
	/**
	 *  保单日志信息保存方法
	 * @param 
	 * @return  boolean
	 */
	public boolean addContractLifeLog(ContractLifeSaveVOModel model ,IUserModel user);
	
	/**
	 *  险种日志保存方法
	 * @param 
	 * @return  boolean
	 */
	public boolean addContractLifeProductLog(ContractLifeProductVOModel model,IUserModel user);
	
	/**
	 *  寿险保单手续费分险种明细信息日志保存方法
	 * @param 
	 * @return  boolean
	 */
	public boolean addContractLifeProductInsFeeLog(ContractLifeProductVOModel model,IUserModel user);
	
	/**
	 *  险种费用日志保存方法
	 * @param
	 * @return  boolean
	 */
	public boolean addContractLifeProductPreLog(ContractLifeProductVOModel model,IUserModel user);
	
	/**
	 *  险种费用明细日志保存方法
	 * @param 
	 * @return  boolean
	 */
	public boolean addContractLifeProductFeeLog(ContractLifeProductVOModel model,IUserModel user);
	
	/**
	 * 汇总
	 * @param policy_id
	 * @return
	 */
	public List<ContractLifeProductVOModel> sumContractlifePrem(Long policy_id) ;
	
	/**
	 * 保单录入完：修改投保单的状态
	* @param model
	* @param user
	* @return boolean
	* @description:
	 */
	public boolean modifyPolicyLifeStatus(IPolicyLifeVOModel model,IUserModel user) ;
	
	
}
