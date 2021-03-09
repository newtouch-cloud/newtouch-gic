package com.ca.cacore.ibs.webapp.service;

import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProductFeePremVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeInfoVOModel;
import com.ca.cacore.csm.model.vo.ICustomerVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.returnmsg.ReturnMsg;
/**
 * 投保单录入service
 * @author szl
 *
 */
public interface IPolicyLifeService {
	/**
	 *  增加客户基本信息保存方法
	 * @param IPolicyLifeHolderModel
	 * @return  ReturnMsg
	 */
	public String addCustomerAndContact(IPolicyLifePeopleVOModel customer, IUserModel user);
	
	public String  modifyCustomerAndContact(IPolicyLifePeopleVOModel cus, IUserModel user);
	
	
	/**
	 *  险种保存方法
	 * @param PolicyLifeModel
	 * @return  boolean
	 */
	//public ReturnMsg addPolicyLifeProduct(PolicyLifeProductModel model);
	
	/**
	 * 保存投保单：全部的保存
	 * @param pl
	 * @return
	 */
	public ReturnMsg saveAllPolicyLife(PolicyLifeInfoVOModel pl, IUserModel user) throws BusinessException;
	
	/**
	 * 查询客户详细信息
	 */
	public ReturnMsg getCustomerView(ICustomerVOModel model);
	
	/**
	 * 投保单修改的方法
	 * @param pl
	 * @param user
	 * @return
	 */
	public ReturnMsg modifyAllPolicyLife(PolicyLifeInfoVOModel pl, IUserModel user) throws BusinessException;
	
	/**
	 * 得到seq_id
	 * @return
	 */
	public Integer getSeq_id();
	
	/** 
	* 获得产品可选缴费方式的信息  ss
	* @param model
	* @return List<String>
	* @description:
	*/
	public List<String> getChargeType(IPolicyLifeProductFeePremVOModel model);
	/** 
	* 获得产品可选缴费期限类型的信息  ss
	* @param model
	* @return List<String>
	* @description:
	*/
	public List<String> getCharge_Period(IPolicyLifeProductFeePremVOModel model);
	/** 
	* 获得产品可选保障期限类型的信息 ss
	* @param model
	* @return List<String>
	* @description:
	*/
	public List<String> getCoveragePeriod(IPolicyLifeProductFeePremVOModel model);
	
	/**
	 * 添加险种费用
	 * @param pl
	 * @param user
	 * @return
	 */
	public ReturnMsg addPolicylifePrem(PolicyLifeInfoVOModel pl, IUserModel user);
	
	/**
	 * 添加险种费用
	 * @param pl
	 * @param user
	 * @return
	 */
	public ReturnMsg modifyPolicylifePrem(PolicyLifeInfoVOModel pl, IUserModel user);
	
	/**
	 * 核心校验
	 * @param pl
	 * @return
	 */
	public PolicyLifeInfoVOModel coreCheck(PolicyLifeInfoVOModel pl,IUserModel user);
}
