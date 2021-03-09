package com.ca.cacore.manage.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.domain.funcPanel.IFuncPanelDomain;
import com.ca.cacore.manage.model.vo.ISearchParamsModel;

/**
* @since:    2014年2月15日   
* @author    ss
* @description:
*/
@Service
public class FuncPanelService implements IFuncPanelService {
	@Autowired private IFuncPanelDomain funcPanelDomain;
	/** 
	* 获得员工的自定制常用功能信息
	* @param emp_id
	* @return List<String>
	* @description:通过员工代码获得员工的自定制常用功能信息
	*/
	@Override
	public List<String> getFuncPanelConfInfo(String emp_id) {
		return funcPanelDomain.getFuncPanelConfInfo(emp_id);
	}
	
	/** 
	* 获得弹出式搜索的信息
	* @param model
	* @return List<String>
	* @description:
	*/
	public List<String> getMenuInfo(ISearchParamsModel model){
		return funcPanelDomain.getMenuInfo(model);
	}
	/** 
	* 获得弹出式搜索的信息
	* @param model
	* @return 
	* @description:
	*/
	@Override
	public List<String> getSechInfo(ISearchParamsModel model) {
		return funcPanelDomain.getSechInfo(model);
	}

	@Override
	public List<String> getManagerId(ISearchParamsModel model) {
		return funcPanelDomain.getManagerID(model);
	}
}
