package com.ca.cacore.ibs.dao;

import java.util.List;
import com.ca.cacore.ibs.model.vo.IContractCostVOModel;

/**
 * 
* @since:    2014年1月9日   
* @author    ZhangChen
* @description:保单费用管理dao层接口
 */

public interface IContractCostDao {
	
	/**
	* 2014-1-9 
	* @param model
	* @return ReturnMsg
	* @description:保单费用明细/结算通用查询
	 */
	public List<IContractCostVOModel> queryContractCost(IContractCostVOModel model);

}
