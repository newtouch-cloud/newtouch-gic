package com.ca.cacore.rsss.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.rsss.model.vo.ICustomeGDVOModel;
import com.newtouch.core.daobase.BaseDao;
@Component
public class CustomeGDDao extends BaseDao implements ICustomeGDDao  {
	
	/**
	 * 
	* 
	* @param model
	* @return List<ICustomeGDVOModel>
	* @description:查询客户群体分布表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ICustomeGDVOModel> queryCustomeGD(ICustomeGDVOModel model) {
		
		return this.getSqlMapClientTemplate().queryForList("CustomeGD.queryCustomeGD",model);
		
	}
	/**
	 * 
	* 
	* @param model
	* @return List<ICustomeGDVOModel>
	* @description:导出客户群体分布表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ICustomeGDVOModel> exportCustomeGD(
			ICustomeGDVOModel model) {
		return this.getSqlMapClientTemplate().queryForList("CustomeGD.exportCustomeGD",model);

	}
}
