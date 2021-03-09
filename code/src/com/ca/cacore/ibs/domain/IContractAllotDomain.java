package com.ca.cacore.ibs.domain;

import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.vo.IContractAllotHisVOModel;
/**
* @since:    2013年12月23日   
* @author    WanBo
* @description: 保单分配Domain层接口
*/
public interface IContractAllotDomain {
	/**
	 * 查询服务人员所有的保单信息列表
	 * @param model
	 * @return List<String>
	 * @description: 根据分配前服务人员代码查询服务人员所有的保单信息列表
	 */
	public List<String> queryContList(IContractAllotHisVOModel model);
	/**
	 * 查询分配前服务人员以及机构,保单等信息
	 * @param model
	 * @return List<String>
	 * @description: 根据保单号查询分配前服务人员以及机构,保单等信息
	 */
	public List<String> queryContInfo(IContractAllotHisVOModel model);
	/**
	 * 通过分配后服务人员代码查询姓名
	 * @param model
	 * @return IContractAllotHisVOModel
	 * @description: 通过分配后服务人员代码查询姓名
	 */
	public IContractAllotHisVOModel queryAftSName(IContractAllotHisVOModel model);
	/**
	 * 更新保单服务人员
	 * @param model
	 * @param user
	 * @return Boolean
	 * @description: 更新保单服务人员
	 */
	public Boolean updateContS(IContractAllotHisVOModel model,IUserModel user);
	
	/**
	 * 根据分配前服务人员代码查询服务人员代码 
	 * @param model
	 * @return IContractAllotHisVOModel
	 * @description: 根据分配前服务人员代码查询服务人员代码 
	 */
	public IContractAllotHisVOModel  queryServiceId(IContractAllotHisVOModel model);
	/**
	 * 根据保单号查询保单号
	 * @param model
	 * @return IContractAllotHisVOModel
	 * @description: 根据保单号查询保单号
	 */
	public IContractAllotHisVOModel queryPCode(IContractAllotHisVOModel model);
	/**
	 * 增加服务人员变更历史记录
	 * @param model
	 * @param user
	 * @return Boolean
	 * @description: 增加服务人员变更历史记录
	 */
	public Boolean addContHis(IContractAllotHisVOModel model, IUserModel user);
}
