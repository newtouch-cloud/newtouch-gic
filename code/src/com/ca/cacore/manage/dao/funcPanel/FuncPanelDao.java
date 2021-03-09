package com.ca.cacore.manage.dao.funcPanel;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.manage.model.bo.IFuncPanelModel;
import com.ca.cacore.manage.model.bo.IMenuModel;
import com.ca.cacore.manage.model.vo.ISearchParamsModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class FuncPanelDao extends BaseDao implements IFuncPanelDao {
	/** 
	* 获得员工的自定制常用功能信息
	* @param emp_id
	* @return List<IFuncPanelModel>
	* @description:通过员工代码获得员工的自定制常用功能信息
	*/
	@SuppressWarnings("unchecked")
	public List<IFuncPanelModel> getFuncPanelConfInfo(String emp_id){
		return this.getSqlMapClientTemplate().queryForList("funcPanel.getFuncPanelConfInfo",emp_id);
	}
	
	/** 
	* 
	* @param model
	* @return List<IMenuModel>
	* @description:获得弹出式搜索的信息
	*/
	@SuppressWarnings("unchecked")
	public List<IMenuModel> getMenuInfo(ISearchParamsModel model){
		return this.getSqlMapClientTemplate().queryForList("funcPanel.getMenuInfo",model);
	}
	/** 
	* 获得弹出式搜索的信息
	* @param model
	* @return 
	* @description:created by wang_ds
	*/
	@Override
	public List<ISearchParamsModel> getSechInfo(ISearchParamsModel model) {
		this.getSqlMapClient().flushDataCache();
		List<ISearchParamsModel> list = (List<ISearchParamsModel>)this.getSqlMapClientTemplate().queryForList("funcPanel.getSechInfo",model);
		return list;
	}

	@Override
	public List<ISearchParamsModel> getManagerID(ISearchParamsModel model) {
		this.getSqlMapClient().flushDataCache();
		List<ISearchParamsModel> list = (List<ISearchParamsModel>)this.getSqlMapClientTemplate().queryForList("funcPanel.getManagerId",model);
		return list;
	}
}
