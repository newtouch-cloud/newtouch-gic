package com.ca.cacore.msss.webapp.service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.msss.model.bo.IProductLlifeModel;
import com.ca.cacore.msss.model.vo.IProductLlifeVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
 * 
 * @author  Wang_ds
 * @since 2013-11-10
 * @description 产品信息Service层接口
 */
public interface  IProductLlifeService {
	/**
	 * @param 传入IProductLlifeVOModel model
	 * @return 返回一个ModelAndView对象
	 * @description 查询出所有产品信息，或根据条件查询
	 */
	public ReturnMsg queryProductLlifeList(IProductLlifeVOModel model);
	
	/*public ReturnMsg queryProductLlifeVOList(IProductLlifeVOModel model);*/
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ReturnMsg对象
	 * @description 查询单个产品信息
	 */
	public ReturnMsg getProductLlifeVO(IProductLlifeVOModel model);
	/**
	 * @param 传入IProductLlifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个ModelAndView对象
	 * @description 修改出所有产品信息
	 */
	public ReturnMsg modifyProductLlife(IProductLlifeVOModel model,IUserModel user) throws BusinessException;
	/**
	 * @param 传入IProductLlifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个ModelAndView对象
	 * @description 修改产品评估信息
	 */
	public ReturnMsg modifyProductEvaluation(IProductLlifeVOModel model,IUserModel user);
	/**
	 * @param 传入IProductLlifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个ModelAndView对象
	 * @description 添加产品信息
	 */
	public ReturnMsg addProductLlife(IProductLlifeVOModel model, IUserModel user);
	/**
	 * @param 传入IProductLlifeVOModel model
	 * @return 返回一个String对象
	 * @description 用于前台输入产品代码，带出产品信息
	 */
	public String getProductLife4Pkg(IProductLlifeVOModel model);
	
	/** 
	* 
	* @param model
	* @return String
	* @description:
	*/
	public String checkProductIdUnique(IProductLlifeVOModel model);
	
	/** 
	* 
	* @param model
	* @return 
	* @description:判断产品名称是否重复（同一家保险公司）
	*/
	public String checkProductNameUnique(IProductLlifeVOModel model);

	/** 
	* 
	* @param model
	* @param user
	* @return ReturnMsg
	* @description:
	*/
	public ReturnMsg deteleProductLlife(IProductLlifeModel model, IUserModel user);
}
