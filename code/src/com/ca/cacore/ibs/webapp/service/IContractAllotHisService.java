package com.ca.cacore.ibs.webapp.service;

import com.ca.cacore.ibs.model.vo.IContractAllotHisVOModel;
import com.newtouch.core.returnmsg.ReturnMsg;
/**
 * @author WanBo
 * @description 保单分配轨迹查询Service层接口
 */
public interface IContractAllotHisService {
	/**
	 * 查询保单分配历史轨迹
	 * @param IContractAllotHisVOModel
	 * @return ReturnMsg
	 * @description: 查询保单分配历史轨迹
	 */
	public ReturnMsg queryContList(IContractAllotHisVOModel model);
}
