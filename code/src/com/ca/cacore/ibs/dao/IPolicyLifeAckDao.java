package com.ca.cacore.ibs.dao;

import java.util.List;

import com.ca.cacore.ibs.model.bo.IContractLifeModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeAckVOModel;

public interface IPolicyLifeAckDao {
	/**
	 * 添加回执录入信息
	 * @param pla
	 * @return
	 */
  public boolean addPolicyLifeAck(IPolicyLifeAckVOModel pla);
  
	/**
	 * 添加回访信息时：回访成功修改保单表
	 * @param cm
	 * @return
	 */
	public boolean modifyConstractLife(IContractLifeModel cm);
	
	/**
	 * 查询所有的投保单回执信息
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
	public boolean modifyPolicyLifeAck(IPolicyLifeAckVOModel pa);
	
	  /**
		 * 异步请求：获得保单的信息
		 * @param pl
		 * @return
		 */
		public  IContractLifeModel getContractLife(IContractLifeModel pl);
		
}
