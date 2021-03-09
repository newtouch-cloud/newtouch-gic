package com.ca.cacore.ibs.webapp.service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.vo.ContractLifeSaveVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IContractLifeService {
	
		/**
		 * 保存投保单：全部的保存
		 * @param pl
		 * @return
		 */
	public ReturnMsg saveAllContractLife(ContractLifeSaveVOModel pl, IUserModel user) throws BusinessException;
	
	/**
	 * 修改保单
	 * @param cl
	 * @param user
	 * @return
	 */
	public ReturnMsg modifyAllContractLife(ContractLifeSaveVOModel cl,IUserModel user) throws BusinessException;
	
	/**
	 * 得到seq_id
	 * @return
	 */
	public Integer getSeq_id();
	
	/**
	 * 添加保单费用信息
	 * @param pl
	 * @param user
	 * @return
	 */
	public ReturnMsg modifyContractlifePrem(ContractLifeSaveVOModel pl,IUserModel user) ;
	
	/**
	 * 修改保单费用信息
	 * @param pl
	 * @param user
	 * @return
	 */
	public ReturnMsg addContractlifePrem(ContractLifeSaveVOModel pl, IUserModel user) ;
	
	/**
	 * 根据保单号码和保险公司机构带出信息
	 * @return
	 */
	public IPolicyLifeVOModel getPolicyLifeInfoBySendCode(PolicyLifeVOModel model);
	
}
