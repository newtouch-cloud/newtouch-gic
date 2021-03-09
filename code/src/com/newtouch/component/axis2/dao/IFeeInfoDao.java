package com.newtouch.component.axis2.dao;

import com.newtouch.component.axis2.model.FeeInfoVoModel;

/**
* @since:    2014年7月1日   
* @author    wangjianqiang
* @description:
 */
public interface IFeeInfoDao {
	
	/**
	 * 
	* @param model
	* @return FeeInfoVoModel
	* @description: webservice 查询手续费的相关信息用于对账
	 */
	public FeeInfoVoModel feeInfo(FeeInfoVoModel model);

	public FeeInfoVoModel agentInfo(FeeInfoVoModel model1);

	
}
