package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.vo.IContractCostVOModel;
import com.newtouch.core.daobase.BaseDao;

/**
 * 
* @since:    2014年1月9日   
* @author    ZhangChen
* @description:保单费用管理dao层实用类
 */
@SuppressWarnings("unchecked")
@Component
public class ContractCostDao extends BaseDao implements IContractCostDao {

	/**
	* 2014-1-9 
	* @param model
	* @return ReturnMsg
	* @description:保单费用明细/结算通用查询
	 */
	@Override
	public List<IContractCostVOModel> queryContractCost(IContractCostVOModel model) {
		//查询数量
		Integer count  = (Integer) this.getSqlMapClientTemplate().queryForObject("contractCost.queryContractCost_count",model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		
		return (List<IContractCostVOModel>)this.getSqlMapClientTemplate().queryForList("contractCost.queryContractCost", model);
	}

}
