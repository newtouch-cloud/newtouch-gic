package com.ca.cacore.ibs.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.domain.IContractCostDomain;
import com.ca.cacore.ibs.model.vo.IContractCostVOModel;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
 * 
* @since:    2014年1月9日   
* @author    ZhangChen
* @description:保单费用管理Serivce实现类
 */
@Service
public class ContractCostService implements IContractCostService{
	@Autowired private IContractCostDomain contractCostDomain;
	
	
	/**
	* 2014-1-9 
	* @param model
	* @return ReturnMsg
	* @description:保单费用明细/结算通用查询
	 */
	@Override
	public ReturnMsg queryContractCost(IContractCostVOModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IContractCostVOModel>list = contractCostDomain.queryContractCost(model);//获取查询结果
		returnMsg.setDataList(TransHelper.list2MapList(list)); //转换成ReturnMsg类型
		return returnMsg;
	}

}
