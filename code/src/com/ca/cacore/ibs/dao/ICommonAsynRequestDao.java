package com.ca.cacore.ibs.dao;

import java.util.List;

import com.ca.cacore.ibs.model.vo.ContractLifeVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;
import com.ca.cacore.csm.model.vo.CustomerVOModel;
import com.ca.cacore.msss.model.bo.IProductLlifeModel;

public interface ICommonAsynRequestDao {
	/**
	 * 根据人员代码带出机构人员姓名
	 * @param sales_id
	 * @return
	 */
	public IPolicyLifePeopleVOModel getSalesInfo(String sales_id);
	
	/**
	 * 根据险种代码带出险种名称
	 * @param product_id
	 * @return
	 */
	public IProductLlifeModel getProductInfo(IProductLlifeModel model);
	
	/**
	 * 根据人员证件信息带出人员信息
	 * @param plp
	 * @return
	 */
	public List<IPolicyLifePeopleVOModel> getCustomerInfo(IPolicyLifePeopleVOModel plp);
	
	/**
	 * 判断投保单号是否重复
	 * @param send_code
	 * @return
	 */
	public  IPolicyLifeVOModel validateSendCode(IPolicyLifeVOModel model);
	
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
	public ContractLifeVOModel validatePolicyCode(ContractLifeVOModel model) ;

	
}
