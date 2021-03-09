package com.ca.cacore.msss.webapp.service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.msss.model.vo.IProductFeeRateVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
 * 
 * @author  fqy
 * @since 2014-1-10
 * @description 产品手续费率Service层接口
 */
public interface  IProFeeRateService {
	/**
	 * @param 传入IProductFeeRateVOModel model
	 * @return 返回一个ReturnMsg对象
	 * @description 根据查询条件查询出所有产品手续费率信息
	 */
	public ReturnMsg queryProFeeRateList(IProductFeeRateVOModel model);
	
	/**
	 * @param 传入IProductFeeRateVOModel model
	 * @return 返回一个ReturnMsg对象
	 * @description 查询选中的产品手续费率明细信息
	 */
	public ReturnMsg queryProFeeRateView(IProductFeeRateVOModel model);
	
	/**
	 * @param 传入IProductFeeRateVOModel model，IUserModel user
	 * @return 返回一个ReturnMsg对象
	 * @description 修改选中的产品手续费率信息
	 */
	public ReturnMsg modifyProFeeRate(IProductFeeRateVOModel model,IUserModel user) throws BusinessException;
	
	/**
	 * @param 传入IProductFeeRateVOModel model
	 * @return 返回一个ReturnMsg对象
	 * @description 新增产品手续费率信息
	 */
	public ReturnMsg insertProFeeRate(IProductFeeRateVOModel model) throws BusinessException;
}
