package com.ca.cacore.rsss.dao;

import java.util.List;

import com.ca.cacore.rsss.model.vo.ICustomeGDVOModel;

public interface ICustomeGDDao {
	
	/**
	* @param model
	* @return List<ICustomeGDVOModel>
	* @description:查询客户群体分布表
	 */
	public List<ICustomeGDVOModel> queryCustomeGD(ICustomeGDVOModel model);
	

	/**
	* @param model
	* @return List<ICustomeGDVOModel>
	* @description:导出客户群体分布表
	 */
	public List<ICustomeGDVOModel> exportCustomeGD(ICustomeGDVOModel model);

}
