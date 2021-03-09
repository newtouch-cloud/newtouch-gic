package com.newtouch.component.axis2.dao;

import java.util.List;

import com.newtouch.component.axis2.model.ProtocolQueryVoModel;

/**
* @since:    2014年4月14日   
* @author    ZhangChen
* @description:
 */
public interface IQuerySaleInfoDao {
	
	/**
	 * 
	* @param model
	* @return ProtocolQueryVoModel
	* @description: webservice 查询中介人员是否存在 by张晨
	 */
	public ProtocolQueryVoModel querySalesInfo(ProtocolQueryVoModel model);

	/**
	 * 
	* @param model
	* @return ProtocolQueryVoModel
	* @description: webservice 查询协议号是否存在 by张晨
	 */
	public List<ProtocolQueryVoModel> checkAgreemengtInfo(ProtocolQueryVoModel model);
	
}
