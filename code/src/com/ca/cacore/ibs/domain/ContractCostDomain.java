package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.dao.IContractCostDao;
import com.ca.cacore.ibs.model.vo.IContractCostVOModel;

/**
 * 
* @since:    2014年1月9日   
* @author    ZhangChen
* @description:保单费用管理domain层实现类
 */
@Service
public class ContractCostDomain implements IContractCostDomain {
	@Autowired private IContractCostDao contractCostDao;
	
	/**
	* 2014-1-9 
	* @param model
	* @return ReturnMsg
	* @description:保单费用明细/结算通用查询
	 */
	@Override
	public List<IContractCostVOModel> queryContractCost(IContractCostVOModel model) {
		
		return contractCostDao.queryContractCost(model);
	}

}
