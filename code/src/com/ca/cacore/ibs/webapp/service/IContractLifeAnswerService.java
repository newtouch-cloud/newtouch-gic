package com.ca.cacore.ibs.webapp.service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.bo.IContractLifeAnswerModel;
import com.ca.cacore.ibs.model.bo.IContractLifeModel;
import com.ca.cacore.ibs.model.vo.IContractLifeAnswerVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IContractLifeAnswerService {
	
	/**
	 * 回执录入信息
	 * @param pam
	 * @param user
	 * @return
	 */
	public ReturnMsg addPolicyAnswer(IContractLifeAnswerModel pam,IUserModel user) throws BusinessException;
	 
	/**
	 * 根据保险公司和保单号得到机构投保人信息
	 * @param cm
	 * @return
	 */
	public IContractLifeModel getConstractMasterInfo(IContractLifeModel cm);
	
	/**
	 * 查询所有的保单回访信息
	 * @param cm
	 * @return
	 */
	public ReturnMsg queryPolicyAnswerInfo(IContractLifeVOModel cm);
	/**
	 * 查询回访明细
	 * @param cm
	 * @return
	 */
	public ReturnMsg getPolicyAnswer(IContractLifeAnswerVOModel cm);
	
	/**
	 * 修改回访信息
	 * @param pa
	 * @param user
	 * @return
	 */
	public ReturnMsg modifyPolicyAnswer(IContractLifeAnswerVOModel pa,IUserModel user) throws BusinessException;
	
}
