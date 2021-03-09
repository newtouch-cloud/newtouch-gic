package com.ca.cacore.ibs.domain;

import java.util.List;

import com.ca.cacore.ibs.model.vo.ContractLifeVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeInfoVOModel;
import com.ca.cacore.csm.model.vo.CustomerVOModel;
import com.ca.cacore.msss.model.bo.IProductLlifeModel;

public interface ICommonAsynRequestDomain {
	
	public IPolicyLifePeopleVOModel getSalesInfo(String sales_id);

	/**
	* @param model
	* @return IProductLlifeModel
	* @description:查询产品信息
	 */
	public IProductLlifeModel getProductInfo(IProductLlifeModel model);

	
	public List<IPolicyLifePeopleVOModel> getCustomerInfo(IPolicyLifePeopleVOModel plp);
	
	public IPolicyLifeVOModel validateSendCode(IPolicyLifeVOModel model) ;
	
	public boolean modifyPolicyLifeStatus(PolicyLifeInfoVOModel pl);
	
	/**
	 * 得到总公司的机构代码
	 * @return
	 */
	public String getBranchID();
	
	public CustomerVOModel getCustomerInfoByCusBranID(CustomerVOModel model);
	
	/**
	 * 验证保单号是否重复
	 * @param model
	 * @return
	 */
	public ContractLifeVOModel validatePolicyCode(ContractLifeVOModel model);
}
