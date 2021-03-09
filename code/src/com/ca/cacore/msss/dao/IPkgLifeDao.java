package com.ca.cacore.msss.dao;

import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.msss.model.bo.IPkgLifeModel;
import com.ca.cacore.msss.model.bo.IPkgLifeRelationModel;
import com.ca.cacore.msss.model.vo.IPkgLifeVOModel;

public interface IPkgLifeDao {

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
	public Boolean modifyPkgLife(IPkgLifeModel model, IUserModel user);

	/**
	 * @param 传入IPkgLifeVOModel model
	 * @return 返回一个PkgLifeModel对象
	 * @description 查询出产品组合信息
	 */
	public IPkgLifeModel getPkgLifeBO(IPkgLifeVOModel model);

	/**
	 * @param 传入IPkgLifeVOModel model
	 * @return 返回一个PkgLifeVOModel对象
	 * @description 查询出产品组合信息
	 */
	public IPkgLifeVOModel getPkgLifeVO(IPkgLifeVOModel model);

	/**
	 * @param 传入IPkgLifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个Integer对象
	 * @description 添加产品组合信息  返回添加的产品组合的seq_id
	 */
	public Integer addPkgLife(IPkgLifeModel model, IUserModel user);
	
	/**
	 * @param 传入IPkgLifeRelationModel relationModel
	 * @param 传入IUserModel user
	 * @return 返回一个Boolean对象
	 * @description 新增寿险产品组合关联信息
	 */
	public Boolean addPkgLifeRelation(IPkgLifeRelationModel relationModel,IUserModel user);
	
	/**
	 * @param 传入IPkgLifeRelationModel relationModelForDelete
	 * @param 传入IUserModel user
	 * @return 返回一个Boolean对象
	 * @description 删除寿险产品组合关联信息
	 */
	public Boolean deletePkgLifeRelation(IPkgLifeRelationModel relationModelForDelete, IUserModel user);



}