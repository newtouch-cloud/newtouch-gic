package com.ca.cacore.msss.webapp.service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.msss.model.vo.IPkgLifeVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IPkgLifeService {

	/**
	 * @param 传入IPkgLifeVOModel model
	 * @return 返回一个ReturnMsg对象
	 * @description 查询出所有产品组合信息，或根据条件查询
	 */
	public ReturnMsg queryPkgLifeList(IPkgLifeVOModel model);

	/**
	 * @param 传入IPkgLifeVOModel model
	 * @return 返回一个ReturnMsg对象
	 * @description 查询出产品组合信息
	 */
	public ReturnMsg getPkgLifeVO(IPkgLifeVOModel model);

	/**
	 * @param 传入IPkgLifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个ModelAndView对象
	 * @description 修改出产品组合信息
	 */
	public ReturnMsg modifyPkgLife(IPkgLifeVOModel model, IUserModel user) throws BusinessException;
	
	/**
	 * @param 传入IPkgLifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个ModelAndView对象
	 * @description 添加产品组合信息
	 */
	public ReturnMsg addPkgLife(IPkgLifeVOModel model, IUserModel user) throws BusinessException;
	/**
	 * @param 传入String pkg_id
	 * @param 传入String pkg_name
	 * @return 返回一个ModelAndView对象
	 * @description 验证产品组合代码和产品组合名称是否唯一
	 */
	public Boolean checkPkgIdAndNameUnique(String pkg_id, String pkg_name);

	public String queryInsBranch();
}