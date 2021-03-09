package com.ca.cacore.ibs.domain;

import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.bo.IContractLifeAnswerModel;
import com.ca.cacore.ibs.model.bo.IContractLifeModel;
import com.ca.cacore.ibs.model.bo.IPolicyLifeModel;
import com.ca.cacore.ibs.model.vo.IContractLifeAnswerVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeVOModel;

public interface IContractLifeAnswerDomain {
	/**
	 * 添加回访信息
	 * @param pam
	 * @return
	 */
	public boolean addPolicyAnswer(IContractLifeAnswerModel pam,IUserModel user);
	
	/**
	 * 修改保单信息：回访成功修改保单回访为是
	 * @param cm
	 * @return
	 */
	public boolean modifyConstractMaster(IContractLifeModel cm,IUserModel user);
	
	/**
	 * 根据保单号和保险机构 查询保单信息
	 * @param cm
	 * @return
	 */
	public IContractLifeModel getConstractMaster(IContractLifeModel cm);
	
	/**
	 * 查询所有的保单回访信息
	 * @param cm
	 * @return
	 */
	public List<IContractLifeVOModel>  queryPolicyAnswerInfo(IContractLifeVOModel cm);
	
	/**
	 * 查询回访明细
	 * @param cm
	 * @return
	 */
	public IContractLifeAnswerVOModel getPolicyAnswer(IContractLifeAnswerVOModel cm);
	
	/**
	 * 修改保单的回访记录
	 * @param pam
	 * @return
	 */
	public boolean modifyPolicyAnswer(IContractLifeAnswerVOModel pa ,IUserModel user);
	
	/**
	 * 回执录入时修改投保单的状态
	 * @param pl
	 * @return
	 */
	public boolean modifyPolicyLife(IPolicyLifeModel pl,IUserModel user);
	
}
