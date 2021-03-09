package com.ca.cacore.rsss.domain;

import java.util.List;

import com.ca.cacore.rsss.model.vo.IBasicSituationModel;

public interface IBasicSituationDomain {
	/**
	* @param model
	* @return List<IBasicSituationModel>
	* @description:查询基本情况
	 */
	public List<IBasicSituationModel> queryBasicSituation(IBasicSituationModel model);
	/**
	* @param model
	* @return List<BusinessReportModel>
	* @description:导出基本情况表
	 */
	public List<IBasicSituationModel> exportBasicSituation(IBasicSituationModel model);
	
}
