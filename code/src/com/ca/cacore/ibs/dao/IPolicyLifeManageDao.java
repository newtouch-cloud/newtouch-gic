package com.ca.cacore.ibs.dao;

import java.util.List;

import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProductVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;

public interface IPolicyLifeManageDao {
	/**
	 * 查询所有的投保单
	 * @param pl
	 * @return
	 */
	public List<IPolicyLifeVOModel> queryAllPolicyLife(IPolicyLifeVOModel pl);
	
	/**
	 * 根据seq_id 查询投保单
	 * @param pl
	 * @return
	 */
	public IPolicyLifeVOModel getPolicyLifeBySeq(IPolicyLifeVOModel model) ;
	
	/**
	 * 查询被保人列表
	 * @param policy_id
	 * @return
	 */
	public List<IPolicyLifePeopleVOModel> getInsurantList(Long policy_id) ;
	
	/**
	 * 查询投保人列表
	 * @param policy_id
	 * @return
	 */
	public  List<IPolicyLifePeopleVOModel> getHolderList(Long policy_id) ;
	
	/**
	 * 查询受益人列表
	 * @param policy_id
	 * @return
	 */
	public  List<IPolicyLifePeopleVOModel> getBeneficiaryList(Long policy_id) ;
	
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
	 * 得到险种的详细信息
	 * @param customer
	 * @return
	 */
	public IPolicyLifeProductVOModel getProductView(Integer seq_id);
	
	/**
	 * 得到险种的列表
	 * @param customer
	 * @return
	 */
	public List<IPolicyLifeProductVOModel> getProductList(Long policy_id);
	
	/**
	 * 得到客户详细信息
	 * @param customer
	 * @return
	 */
	public  IPolicyLifePeopleVOModel getCustomerInfo(String customer_id);
	
	/**
	 * 修改客户表
	 * @param cus
	 * @return
	 */
	public boolean modifyCustomer(IPolicyLifePeopleVOModel cus);
	
	/**
	 * 修改客户表联系信息
	 * @param cus
	 * @return
	 */
	public boolean modifyCustomerContact(IPolicyLifePeopleVOModel cus);
	
	/**
	 * 更新险种
	 * @param customer
	 * @return
	 */
	public boolean modifyProduct(IPolicyLifeProductVOModel plp);
	
	/**
	 * 修改投保单的状态
	 * @param model
	 * @return
	 */
	public boolean modifyPolicyLifeStatus(IPolicyLifeVOModel model);
	
	/**
	 * 查询状态不为首期待承保的投保单
	 * @param pl
	 * @return
	 */
	public List<IPolicyLifeVOModel> querySomePolicyLife(IPolicyLifeVOModel pl) ;
	
	/**
	 * 添加投保单状态变更
	 * @param plp
	 * @return
	 */
	public boolean addPolicyLifeChangeHis(IPolicyLifeVOModel plp);
	
	/**
	 * 修改的时候查询投保单
	 * @param pl
	 * @return
	 */
	public List<IPolicyLifeVOModel> queryPolicyLifeModify(IPolicyLifeVOModel pl);
}
