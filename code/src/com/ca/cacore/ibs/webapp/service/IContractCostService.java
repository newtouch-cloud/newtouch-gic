package com.ca.cacore.ibs.webapp.service;

import com.ca.cacore.ibs.model.vo.IContractCostVOModel;
import com.newtouch.core.returnmsg.ReturnMsg;
/**
 * 
* @since:    2014年1月9日   
* @author    ZhangChen
* @description:保单费用管理service接口
 */
public interface IContractCostService {
	
	/**
	 * 
	* 2014-1-9 
	* @param model
	* @return ReturnMsg
	* @description:保单费用明细/结算通用查询
	 */
	public ReturnMsg queryContractCost(IContractCostVOModel model);

}
