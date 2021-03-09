package com.ca.cacore.rsss.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.dao.IBasicSituationDao;
import com.ca.cacore.rsss.domain.IBasicSituationDomain;
import com.ca.cacore.rsss.model.vo.IBasicInfomationModel;
import com.ca.cacore.rsss.model.vo.IBasicSituationModel;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
@Service
public class BasicSituationService implements IBasicSituationService{
@Autowired private IBasicSituationDomain ibasicsituationdomain;

@Autowired private  IBasicSituationDao ibasicsituationdao;
	/**
	* @param model
	* @return ReturnMsg
	* @description:查询基本情况表
	 */
	@Override
	public ReturnMsg queryBasicSituation(IBasicSituationModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IBasicSituationModel> list = ibasicsituationdomain.queryBasicSituation(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	@Override
	public List<IBasicSituationModel> exportBasicSituation(
			IBasicSituationModel model) {
		List<IBasicSituationModel> list = ibasicsituationdomain.exportBasicSituation(model);
		return list;
	}

	@Override
	public ReturnMsg queryBasicInfomation(IBasicInfomationModel model) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();
		List<IBasicInfomationModel> list = ibasicsituationdao.queryBasicInfomation(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	@Override
	public List<IBasicInfomationModel> queryBasicInfomations(
			IBasicInfomationModel model) {
		// TODO Auto-generated method stub
		return ibasicsituationdao.queryBasicInfomation(model);
	}

	@Override
	public IBasicInfomationModel queryBasicInfomation1(IBasicInfomationModel model) {
		// TODO Auto-generated method stub
		return ibasicsituationdao.queryBasicInfomation1(model);
	}
	
}
