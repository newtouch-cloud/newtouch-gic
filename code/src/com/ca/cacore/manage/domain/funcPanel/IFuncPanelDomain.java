package com.ca.cacore.manage.domain.funcPanel;

import java.util.List;

import com.ca.cacore.manage.model.vo.ISearchParamsModel;

/**
* @since:    2014年2月15日   
* @author    ss
* @description:
*/
public interface IFuncPanelDomain {
	/** 
	* 获得员工的自定制常用功能信息
	* @param emp_id
	* @return list<String>
	* @description:通过员工代码获得员工的自定制常用功能信息
	*/
	public List<String> getFuncPanelConfInfo(String emp_id);
	
	/** 
	* 
	* @param model
	* @return List<String>
	* @description:获得弹出式搜索的信息
	*/
	public List<String> getMenuInfo(ISearchParamsModel model);
	
	public List<String> getSechInfo(ISearchParamsModel model);
	
	public List<String> getManagerID(ISearchParamsModel model);
}
