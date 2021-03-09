package com.ca.cacore.ibs.webapp.service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.vo.IContractLifeVOModel;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IContractLifeManageService {
	
	/**
	 * 查询所有的保单
	 * @param cm
	 * @return
	 */
	public ReturnMsg queryContractMaster(IContractLifeVOModel cm);
	
	 /**
	  * 得到保单明细
	  * @param seq_id
	  * @return
	  */
	 public ReturnMsg getContractLifeView(Integer seq_id);
	 
		/**
		 * 得到投保人的详细信息
		 * @param customer
		 * @return
		 */
		public ReturnMsg getHolderView(String customer_id);
		
		/**
		 * 得到被保人的详细信息
		 * @param customer
		 * @return
		 */
		public ReturnMsg getInsurantView(String customer_id);
		
		/**
		 * 得到受益人详细信息
		 * @param customer
		 * @return
		 */
		public ReturnMsg getBenefView(String customer_id);
		

		/**
		 * 得到险种的明细
		 * @param customer_id
		 * @return
		 */
		public ReturnMsg getProductView(int seq_id) ;
		
		/**
		 * 修改保单的状态
		 * @param model
		 * @param user
		 * @return
		 */
		public ReturnMsg modifyContractLifeStatus(IContractLifeVOModel model,IUserModel user) ;
		
		/**
		 * 查询保单信息:不包括无效投保单
		 * @param cm
		 * @return
		 */
		public ReturnMsg  queryCLFModifyStatus(IContractLifeVOModel cm);
		
		/**
		 * 查询保单信息:不包括无效保单和终止保单
		 * @param cm
		 * @return
		 */
		public ReturnMsg queryCLFModify(IContractLifeVOModel cm);
}
