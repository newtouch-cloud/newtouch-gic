package com.ca.cacore.rsss.domain;

import java.util.List;

import com.ca.cacore.rsss.model.vo.ICustomeGDVOModel;

public interface ICustomeGDDomain {
	
	/**
	* @param model
	* @return List<ICustomeGDVOModel>
	* @description:查询询客户群体分布表
	 */
	public List<ICustomeGDVOModel> queryCustomeGD(ICustomeGDVOModel model);
	/**
	* @param model
	* @return List<ICustomeGDVOModel>
	* @description:导出询客户群体分布表
	 */
	public List<ICustomeGDVOModel> exportCustomeGD(ICustomeGDVOModel model);
	
}
