package com.ca.cacore.ibs.domain;

import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProductVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;

public interface IPolicyLifeManageDomain {
	/**
	 * 查询所有的投保单
	 * @param pl
	 * @return
	 */
	public List<IPolicyLifeVOModel> queryAllPolicyLife(IPolicyLifeVOModel pl);
	
	/**
	 * 查询投保单明细
	 * @param pl
	 * @return
	 */
	public IPolicyLifeVOModel getPolicyLifeView(IPolicyLifeVOModel model) ;
	
	/**
	 * 得到投保人的详细信息
	 * @param customer
	 * @return
	 */
	public IPolicyLifePeopleVOModel getHolderView(IPolicyLifePeopleVOModel plp);
	
	/**
	 * 得到被保人的详细信息
	 * @param customer
	 * @return
	 */
	public IPolicyLifePeopleVOModel getInsurantView(IPolicyLifePeopleVOModel plp);
	
	/**
	 * 得到受益人详细信息
	 * @param customer
	 * @return
	 */
	public IPolicyLifePeopleVOModel getBenefView(IPolicyLifePeopleVOModel plp);
	
	/**
	 * 得到险种的明细
	 * @param customer_id
	 * @return
	 */
	public IPolicyLifeProductVOModel getProductView(Integer seq_id) ;
	
	/**
	 * 修改客户表
	 * @param cus
	 * @return
	 */
	public boolean modifyCustomer(IPolicyLifePeopleVOModel cus, IUserModel user);
	
	/**
	 * 更新险种
	 * @param customer
	 * @return
	 */
	public boolean modifyProduct(IPolicyLifeProductVOModel plp,IUserModel user);
	
	/**
	 * 修改投保单的状态
	 * @param model
	 * @param user
	 * @return
	 */
	public boolean modifyPolicyLifeStatus(IPolicyLifeVOModel model,IUserModel user);
	
	/**
	 * 查询状态不为首期待承保的投保单
	 * @param pl
	 * @return
	 */
	public List<IPolicyLifeVOModel> querySomePolicyLife(IPolicyLifeVOModel pl) ;
	
	/**
	 * 添加投保单的状态变更历史
	 * @param plp
	 * @param user
	 * @return
	 */
	public boolean addPolicyLifeChangeHis(IPolicyLifeVOModel plp,IUserModel user) ;
	

	/**
	 * 修改的时候查询投保单
	 * @param pl
	 * @return
	 */
	public List<IPolicyLifeVOModel> queryPolicyLifeModify(IPolicyLifeVOModel pl);
	
}
