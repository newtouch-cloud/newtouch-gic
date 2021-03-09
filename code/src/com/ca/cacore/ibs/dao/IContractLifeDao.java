package com.ca.cacore.ibs.dao;

import java.util.List;

import com.ca.cacore.ibs.model.bo.IContractLifeBeneficiaryModel;
import com.ca.cacore.ibs.model.bo.IContractLifeHolderModel;
import com.ca.cacore.ibs.model.bo.IContractLifeInsurantModel;
import com.ca.cacore.ibs.model.vo.ContractLifeProductVOModel;
import com.ca.cacore.ibs.model.vo.ContractLifeSaveVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeVOModel;


public interface IContractLifeDao {
	
	/**
	 *  寿险投保单投保人信息保存方法
	 * @param IPolicyLifeHolderModel
	 * @return  boolean
	 */
	public boolean addContractLifeHolder(IContractLifeHolderModel model);
	/**
	 *  被保险人信息保存方法
	 * @param 
	 * @return  boolean
	 */
	public boolean addContractLifeInsured(IContractLifeInsurantModel model);
	
	/**
	 *  受益人信息保存方法
	 * @param 
	 * @return  boolean
	 */
	public boolean addContractLifeBeneficiary(IContractLifeBeneficiaryModel model);
	
	/**
	 *  保单信息保存方法
	 * @param 
	 * @return  boolean
	 */
	public boolean addContractLife(ContractLifeSaveVOModel model );
	
	/**
	 *  险种保存方法
	 * @param 
	 * @return  boolean
	 */
	public boolean addContractLifeProduct(ContractLifeProductVOModel model);
	
	/**
	 *  寿险保单手续费分险种明细信息保存方法
	 * @param 
	 * @return  boolean
	 */
	public boolean addContractLifeProductInsFee(ContractLifeProductVOModel model);
	
	/**
	 *  险种费用保存方法
	 * @param
	 * @return  boolean
	 */
	public boolean addContractLifeProductPre(ContractLifeProductVOModel model);
	
	/**
	 *  险种费用明细保存方法
	 * @param 
	 * @return  boolean
	 */
	public boolean addContractLifeProductFee(ContractLifeProductVOModel model);
	
	/**
	 *  保单信息保存方法
	 * @param 
	 * @return  boolean
	 */
	public IContractLifeVOModel getContractLifeView(String policy_code);
	
	/**
	 * 更改保单信息
	 * @param pl
	 * @return
	 */
	public boolean modifyContractLife(ContractLifeSaveVOModel pl);
	
	/**
	 * 修改投保人
	 * @param model
	 * @return
	 */
	public boolean modifycontractLifeHolder(IContractLifeHolderModel model);
	
	/**
	 *  被保险人信息修改方法
	 * @param IPolicyLifeHolderModel
	 * @return  boolean
	 */
	public boolean modifyContractLifeInsured(IContractLifeInsurantModel model);
	
	/**
	 *  受益人信息修改方法
	 * @param PolicyLifeBeneficiaryModel
	 * @return  boolean
	 */
	public boolean modifyContractLifeBeneficiary(IContractLifeBeneficiaryModel model);
	
	/**
	 *  险种修改方法
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean modifyContractLifeProduct(ContractLifeProductVOModel model);
	
	/**
	 *  修改保单费用信息
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean modifyContractLifePrem(ContractLifeProductVOModel model);

	/**
	 *  修改保单费用分险种明细信息
	 * @param PolicyLifeProductModel
	 * @return  boolean
	 */
	public boolean modifyContractLifeProductFee(ContractLifeProductVOModel model);
	
	/**
	 *  寿险保单手续费分险种明细信息修改方法
	 * @param 
	 * @return  boolean
	 */
	public boolean modifyContractLifeProductInsFee(ContractLifeProductVOModel model);
	
	/**
	 * 得到seq_id
	 * @return
	 */
	public Integer getSeq_id();
	
	
	/**
	 *  保单日志信息保存方法
	 * @param 
	 * @return  boolean
	 */
	public boolean addContractLifeLog(ContractLifeSaveVOModel model );
	
	/**
	 *  险种日志保存方法
	 * @param 
	 * @return  boolean
	 */
	public boolean addContractLifeProductLog(ContractLifeProductVOModel model);
	
	/**
	 *  寿险保单手续费分险种明细信息日志保存方法
	 * @param 
	 * @return  boolean
	 */
	public boolean addContractLifeProductInsFeeLog(ContractLifeProductVOModel model);
	
	/**
	 *  险种费用日志保存方法
	 * @param
	 * @return  boolean
	 */
	public boolean addContractLifeProductPreLog(ContractLifeProductVOModel model);
	
	/**
	 *  险种费用明细日志保存方法
	 * @param 
	 * @return  boolean
	 */
	public boolean addContractLifeProductFeeLog(ContractLifeProductVOModel model);
	
	/**
	 * 汇总保单的费用信息
	 * @param policy_id
	 * @return
	 */
	public List<ContractLifeProductVOModel> sumContractlifePrem(Long policy_id) ;
	
}
