package com.ca.cacore.ibs.dao;

import java.util.List;


import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.vo.IContractAllotHisVOModel;
/**
* @since:    2013年12月23日   
* @author    WanBo
* @description: 保单分配DAO层接口
*/
public interface IContractAllotDao {
	/**
	 * 查询服务人员所有的保单信息列表
	 * @param IContractAllotHisVOModel
	 * @return List<IContractAllotHisVOModel>
	 * @description: 根据分配前服务人员代码查询服务人员所有的保单信息列表
	 */
	public List<IContractAllotHisVOModel> queryContList(IContractAllotHisVOModel model);
	
	/**
	 * 查询分配前服务人员以及机构,保单等信息
	 * @param IContractAllotHisVOModel
	 * @return IContractAllotHisVOModel
	 * @description: 根据保单号查询分配前服务人员以及机构,保单等信息
	 */
	public IContractAllotHisVOModel queryContInfo(IContractAllotHisVOModel model);
	/**
	 * @param IContractAllotHisVOModel
	 * @return IContractAllotHisVOModel
	 * @description: 通过分配后服务人员代码查询姓名
	 */
	public IContractAllotHisVOModel queryAftSName(IContractAllotHisVOModel model);
	/**
	 * 更新保单服务人员
	 * @param IContractAllotHisVOModel,IUserModel
	 * @return Boolean
	 * @description: 更新保单服务人员
	 */
	public Boolean updateContS(IContractAllotHisVOModel model,IUserModel user);
	/**
	 * 根据分配前服务人员代码查询服务人员代码
	 * @param IContractAllotHisVOModel
	 * @return IContractAllotHisVOModel
	 * @description: 根据分配前服务人员代码查询服务人员代码
	 */
	public IContractAllotHisVOModel queryServiceId(IContractAllotHisVOModel model);
	/**
	 * 根据保单号查询保单号
	 * @param IContractAllotHisVOModel
	 * @return IContractAllotHisVOModel
	 * @description: 根据保单号查询保单号是否存在
	 */
	public IContractAllotHisVOModel queryPCode(IContractAllotHisVOModel model);
	/**
	 * 增加服务人员变更历史记录
	 * @param IContractAllotHisVOModel,IUserModel
	 * @return Boolean
	 * @description: 增加服务人员变更历史记录
	 */
	public Boolean addContHis(IContractAllotHisVOModel model, IUserModel user);
	
}
