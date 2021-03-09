package com.ca.cacore.rsss.dao;

import java.util.List;

import com.ca.cacore.rsss.model.vo.IBasicInfomationModel;
import com.ca.cacore.rsss.model.vo.IBasicSituationModel;

public interface IBasicSituationDao {
	
	/**
	* @param model
	* @return List<IBasicSituationModel>
	* @description:查询基本情况
	 */
	public List<IBasicSituationModel> queryBasicSituation(IBasicSituationModel model);
	

	/**
	* @param model
	* @return List<IBasicSituationModel>
	* @description:导出基本情况表
	 */
	public List<IBasicSituationModel> exportBasicSituation(IBasicSituationModel model);
	
	public List<IBasicInfomationModel> queryBasicInfomation(IBasicInfomationModel model);


	public IBasicInfomationModel queryBasicInfomation1(
			IBasicInfomationModel model);
}
