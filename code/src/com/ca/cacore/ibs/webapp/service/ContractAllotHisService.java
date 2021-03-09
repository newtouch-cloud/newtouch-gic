package com.ca.cacore.ibs.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.domain.IContractAllotHisDomain;
import com.ca.cacore.ibs.model.vo.IContractAllotHisVOModel;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;
/**
 * @author WanBo
 * @description 保单分配轨迹查询Service层
 */
@Service
public class ContractAllotHisService extends ServerBase implements IContractAllotHisService{
	@Autowired
	private IContractAllotHisDomain  ContractAllotHisDomain;
	/**
	 * 查询保单分配历史轨迹
	 * @param IContractAllotHisVOModel
	 * @return ReturnMsg
	 * @description: 查询保单分配历史轨迹
	 */
	@Override
	public ReturnMsg queryContList(IContractAllotHisVOModel model) {
		ReturnMsg msg = new ReturnMsg();
		List<IContractAllotHisVOModel> relist = ContractAllotHisDomain.queryContList(model);
		msg.setDataList(TransHelper.list2MapList(relist));
		return msg;
	}

}
