package com.ca.cacore.ibs.dao;

import java.util.List;

import com.ca.cacore.ibs.model.vo.IContractLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeProductVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeVOModel;

public interface IContractLifeManageDao {
	
	/**
	 * 查询所有的保单信息
	 * @param cm
	 * @return
	 */
	public List<IContractLifeVOModel>  queryContractMaster(IContractLifeVOModel cm);
	
	/**
	 * 根据seq_id 查询保单
	 * @param pl
	 * @return
	 */
	public IContractLifeVOModel getContractLifeBySeq(Integer seq_id) ;
	
	/**
	 * 查询被保人列表
	 * @param policy_id
	 * @return
	 */
	public List<IContractLifePeopleVOModel> getInsurantList(Long policy_id) ;
	
	/**
	 * 查询投保人列表
	 * @param policy_id
	 * @return
	 */
	public  List<IContractLifePeopleVOModel> getHolderList(Long policy_id) ;
	
	/**
	 * 查询受益人列表
	 * @param policy_id
	 * @return
	 */
	public  List<IContractLifePeopleVOModel> getBeneficiaryList(Long policy_id) ;
	
	/**
	 * 得到投保人的详细信息
	 * @param customer
	 * @return
	 */
	public IContractLifePeopleVOModel getHolderView(String customer_id);
	
	/**
	 * 得到被保人的详细信息
	 * @param customer
	 * @return
	 */
	public IContractLifePeopleVOModel getInsurantView(String customer_id);
	
	/**
	 * 得到受益人详细信息
	 * @param customer
	 * @return
	 */
	public IContractLifePeopleVOModel getBenefView(String customer_id);
	
	/**
	 * 得到险种的详细信息
	 * @param customer
	 * @return
	 */
	public IContractLifeProductVOModel getProductView(int seq_id);
	
	/**
	 * 得到险种的列表
	 * @param customer
	 * @return
	 */
	public List<IContractLifeProductVOModel> getProductList(Long policy_id);
	
	/**
	 * 修改保单的状态
	 * @param model
	 * @return
	 */
	public boolean modifyContractLifeStatus(IContractLifeVOModel model);
	
	/**
	 * 添加保单状态变更历史
	 * @param model
	 * @return
	 */
	public boolean addContractLifeChangeHis(IContractLifeVOModel model);
	
	/**
	 * 查询保单信息:不包括无效保单
	 * @param cm
	 * @return
	 */
	public List<IContractLifeVOModel>  queryCLFModifyStatus(IContractLifeVOModel cm);
	
	/**
	 * 查询保单信息:不包括无效保单和终止保单
	 * @param cm
	 * @return
	 */
	public List<IContractLifeVOModel>  queryCLFModify(IContractLifeVOModel cm);
}
