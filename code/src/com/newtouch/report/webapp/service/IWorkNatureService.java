package com.newtouch.report.webapp.service;

import java.util.List;

import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.report.model.vo.IWorkNatureModel;

public interface IWorkNatureService {

	/**
	 * 人员情况分析表-合同类型查询
	 * @param model
	 * @return
	 */
	ReturnMsg queryWorkNature(IWorkNatureModel model);
	
	public List<IWorkNatureModel> queryWorkNatures(IWorkNatureModel model);

	public IWorkNatureModel queryTotal(IWorkNatureModel model);

	public IWorkNatureModel queryHeadquarter(IWorkNatureModel model);

}
