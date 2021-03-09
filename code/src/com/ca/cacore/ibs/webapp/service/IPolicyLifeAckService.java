package com.ca.cacore.ibs.webapp.service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.bo.IContractLifeModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeAckVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IPolicyLifeAckService {
	
	/**
	 * 回执信息录入：添加和更新
	 * @param pla
	 * @param user
	 * @return
	 */
	public ReturnMsg policyLifeAck(IPolicyLifeAckVOModel pla ,IUserModel user) throws BusinessException;
	
	/**
	 * 查询所有的保单回访信息
	 * @param cm
	 * @return
	 */
	public ReturnMsg queryPolicyAnswerList(IPolicyLifeAckVOModel cm);
	
	/**
	 * 查询回执明细
	 * @param cm
	 * @return
	 */
	public ReturnMsg getPolicyLifeAckView(Integer seq_id);
	
	/**
	 * 修改回访信息
	 * @param pa
	 * @param user
	 * @return
	 */
	public ReturnMsg modifyPolicyLifeAck(IPolicyLifeAckVOModel pla,IUserModel user) throws BusinessException;
	
	  /**
		 * 异步请求：获得保单和投保單的信息
		 * @param pl
		 * @return
		 */
		public  IContractLifeModel getContractLife(IContractLifeModel pl);
}
