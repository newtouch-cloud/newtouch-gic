package com.ca.cacore.ibs.webapp.service;

import java.util.List;

import com.ca.cacore.ibs.model.vo.ContractLifeVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeInfoVOModel;
import com.ca.cacore.msss.model.bo.IProductLlifeModel;

public interface ICommonAsynRequestService {
	
	public IPolicyLifePeopleVOModel getSalesInfo(String sales_id);

	/**
	* @param model
	* @return IProductLlifeModel
	* @description:查询产品信息
	 */
	public IProductLlifeModel getProductInfo(IProductLlifeModel model);
	
	public List<IPolicyLifePeopleVOModel> getCustomerInfo(IPolicyLifePeopleVOModel plp);
	
	public boolean validateSendCode(IPolicyLifeVOModel model);
	
	public boolean validatePolicyCode(ContractLifeVOModel model);

	public String modifyPolicyLifeStatus(PolicyLifeInfoVOModel pl);
}
