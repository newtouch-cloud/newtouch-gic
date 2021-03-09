package com.ca.cacore.ibs.dao;

import java.util.List;

import com.ca.cacore.ibs.model.bo.IContractLifeAnswerModel;
import com.ca.cacore.ibs.model.bo.IContractLifeModel;
import com.ca.cacore.ibs.model.bo.IPolicyLifeModel;
import com.ca.cacore.ibs.model.vo.IContractLifeAnswerVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeVOModel;

public interface IContractLifeAnswerDao {
	/**
	 * 添加回访信息
	 * @param pam
	 * @return
	 */
	public boolean addPolicyAnswer(IContractLifeAnswerModel pam);
	
	/**
	 * 添加回访信息时：回访成功修改保单表
	 * @param cm
	 * @return
	 */
	public boolean modifyConstractMaster(IContractLifeModel cm);
	
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
	 * 根据seq_id查询回访明细
	 * @param pa
	 * @return
	 */
	public IContractLifeAnswerVOModel getPolicyAnswer(IContractLifeAnswerVOModel pa);
	
	/**
	 * 根据seq_id修改保单的回访记录
	 * @param pam
	 * @return
	 */
	public boolean modifyPolicyAnswer(IContractLifeAnswerVOModel pa);
	
	/**
	 * 修改投保单
	 * @param pl
	 * @return
	 */
	public boolean modifyPolicyLife(IPolicyLifeModel pl);
	
	 /**
		 * 异步请求：获得投保单的信息
		 * @param pl
		 * @return
		 */
		public  IPolicyLifeModel getPolicyLife(IPolicyLifeModel pl);
}
