package com.ca.cacore.msss.domain;

import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.msss.model.vo.IPkgLifeVOModel;
import com.newtouch.component.c11assistant.BusinessException;

public interface IPkgLifeDomain {

	/**
	 * @param 传入IPkgLifeVOModel model
	 * @return 返回一个List<IPkgLifeVOModel>对象
	 * @description 查询出所有产品组合信息，或根据条件查询
	 */
	public List<IPkgLifeVOModel> queryPkgLifeList(IPkgLifeVOModel model);

	/**
	 * @param 传入IPkgLifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个Boolean对象
	 * @description 修改出产品组合信息
	 */
	public Boolean modifyPkgLife(IPkgLifeVOModel model, IUserModel user);
	
	/**
	 * @param 传入IPkgLifeVOModel model
	 * @return 返回一个PkgLifeVOModel对象
	 * @description 查询出产品组合信息
	 */
	public IPkgLifeVOModel getPkgLifeVO(IPkgLifeVOModel model);
	
	/**
	 * @param 传入IPkgLifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个Boolean对象
	 * @description 添加产品组合信息
	 */
	public Boolean addPkgLife(IPkgLifeVOModel model, IUserModel user);
	/**
     * 校验产品产品组合是否唯一
     * @param model 产品model
     * @throws BusinessException 不合法时抛出此异常
     */
	public Boolean checkPkgIdAndNameUnique(IPkgLifeVOModel model);
}