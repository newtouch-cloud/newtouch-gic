package com.newtouch.report.dao;

import java.util.List;

import com.newtouch.report.model.vo.IWorkNatureModel;

public interface IWorkNatureDao {

	/**
	 * 人员情况分析表-合同类型查询
	 * @param model
	 * @return
	 */
	List<IWorkNatureModel> queryWorkNature(IWorkNatureModel model);

	public IWorkNatureModel queryTotal(IWorkNatureModel model);

	public IWorkNatureModel queryHeadquarter(IWorkNatureModel model);

}
