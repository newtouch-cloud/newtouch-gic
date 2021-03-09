package com.ca.cacore.ibs.dao;

import java.util.List;

import com.ca.cacore.ibs.model.bo.FeeStatusModel;
/**
 * 
* @since:    2014年1月10日   
* @author    ZhangChen
* @description:费用处理状态标签Dao层接口
 */
public interface IFeeStatusDao {
	
	/**
	 * 
	* 
	* @return List
	* @description: 查询所有费用处理状态内容
	 */
	public List<FeeStatusModel> getStatus();

}
