package com.ca.cacore.rsss.webapp.service;

import java.util.List;

import com.ca.cacore.rsss.model.vo.IBasicInfomationModel;
import com.ca.cacore.rsss.model.vo.IBasicSituationModel;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IBasicSituationService {
	/**
	 * 
	* 
	* @param model
	* @return ReturnMsg
	* @description:查询基本情况
	 */
	public ReturnMsg queryBasicSituation(IBasicSituationModel model);
	/**
	 * 
	* 
	* @param model
	* @return List<IBasicSituationModel>
	* @description:导出基本情况表
	 */
	public List<IBasicSituationModel> exportBasicSituation(IBasicSituationModel model);
	
	public ReturnMsg queryBasicInfomation(IBasicInfomationModel model);
	
	public List<IBasicInfomationModel> queryBasicInfomations(IBasicInfomationModel model);
	
	public IBasicInfomationModel queryBasicInfomation1(IBasicInfomationModel model);

}
