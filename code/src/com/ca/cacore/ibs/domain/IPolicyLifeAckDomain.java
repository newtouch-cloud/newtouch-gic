package com.ca.cacore.ibs.domain;

import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.bo.IContractLifeModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeAckVOModel;

public interface IPolicyLifeAckDomain {
	
	/**
	 * 添加回执录入信息
	 * @param pla
	 * @return
	 */
  public boolean addPolicyLifeAck(IPolicyLifeAckVOModel pla,IUserModel user);
  
	/**
	 * 回执成功修改保单表
	 * @param cm
	 * @return
	 */
	public boolean modifyConstractLifeForAdd(IContractLifeModel cm,IUserModel user);
	
	/**
	 * 回执修改成功的时候修改保单
	 * @param pl
	 * @return
	 */
	public boolean modifyClfForModify(IContractLifeModel pl,IUserModel user);
	
	/**
	 * 查询所有的保单回执信息
	 * @param cm
	 * @return
	 */
	public List<IPolicyLifeAckVOModel>  queryPolicyLifeAckList(IPolicyLifeAckVOModel cm);
	
	/**
	 * 根据seq_id查询回执明细
	 * @param pa
	 * @return
	 */
	public IPolicyLifeAckVOModel getPolicyLifeAckViewr(Integer seq_id);
	
	/**
	 * 根据seq_id修改保单的回执记录
	 * @param pam
	 * @return
	 */
	public boolean modifyPolicyLifeAck(IPolicyLifeAckVOModel pa,IUserModel user);
	
	  /**
		 * 异步请求：获得保单的信息
		 * @param pl
		 * @return
		 */
		public  IContractLifeModel getContractLife(IContractLifeModel pl);

}
