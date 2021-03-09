package com.ca.cacore.manage.dao.funcPanel;

import java.util.List;

import com.ca.cacore.manage.model.bo.IFuncPanelModel;
import com.ca.cacore.manage.model.bo.IMenuModel;
import com.ca.cacore.manage.model.vo.ISearchParamsModel;

/**
* @since:    2014年2月15日   
* @author    ss
* @description:查询自定制界面的配置信息
*/
public interface IFuncPanelDao {
	/** 
	* 获得员工的自定制常用功能信息
	* @param emp_id
	* @return List<IFuncPanelModel>
	* @description:通过员工代码获得员工的自定制常用功能信息
	*/
	public List<IFuncPanelModel> getFuncPanelConfInfo(String emp_id);
	
	/** 
	* 
	* @param model
	* @return List<IMenuModel>
	* @description:获得弹出式搜索的信息
	*/
	public List<IMenuModel> getMenuInfo(ISearchParamsModel model);
	public List<ISearchParamsModel> getSechInfo(ISearchParamsModel model);

	public List<ISearchParamsModel> getManagerID(ISearchParamsModel model);
}
