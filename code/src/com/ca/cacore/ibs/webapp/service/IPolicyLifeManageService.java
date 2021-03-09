package com.ca.cacore.ibs.webapp.service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IPolicyLifeManageService {
	/**
	 * 查询所有的投保单
	 * @param pl
	 * @return
	 */
	 public ReturnMsg queryAllPolicyLife(IPolicyLifeVOModel pl);
	 
	 /**
	  * 得到投保单明细
	  * @param seq_id
	  * @return
	  */
	 public ReturnMsg getPolicyLifeView(IPolicyLifeVOModel m);
	 
		/**
		 * 得到投保人的详细信息
		 * @param customer
		 * @return
		 */
		public ReturnMsg getHolderView(IPolicyLifePeopleVOModel plp);
		
		/**
		 * 得到被保人的详细信息
		 * @param customer
		 * @return
		 */
		public ReturnMsg getInsurantView(IPolicyLifePeopleVOModel plp);
		
		/**
		 * 得到受益人详细信息
		 * @param customer
		 * @return
		 */
		public ReturnMsg getBenefView(IPolicyLifePeopleVOModel plp);
		

		/**
		 * 得到险种的明细
		 * @param customer_id
		 * @return
		 */
		public ReturnMsg getProductView(Integer seq_id) ;
		
//		/**
//		 * 修改客户表
//		 * @param cus
//		 * @return
//		 */
//		public ReturnMsg modifyCustomerAndContact(IPolicyLifePeopleVOModel cus, IUserModel user) throws BusinessException;
		
//		/**
//		 * 修改投保单产品表
//		 * @param plp
//		 * @param user
//		 * @return
//		 */
//		public ReturnMsg modifyProduct(IPolicyLifeProductVOModel plp, IUserModel user) throws BusinessException;
		
		/**
		 * 修改投保单的状态
		 * @param model
		 * @param user
		 * @return
		 */
		public ReturnMsg modifyPolicyLifeStatus(IPolicyLifeVOModel model,IUserModel user);

		/**
		 * 查询部分投保单：状态不为首期待承保
		 * @param pl
		 * @return
		 */
		public ReturnMsg querySomePolicyLife(IPolicyLifeVOModel pl) ;
		
		public ReturnMsg queryPolicyLifeModify(IPolicyLifeVOModel pl) ;
}
