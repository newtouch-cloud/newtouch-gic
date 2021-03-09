package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.vo.IContractAllotHisVOModel;
import com.newtouch.core.daobase.BaseDao;
/**
* @since:    2013年12月23日   
* @author    WanBo
* @description: 保单分配DAO层
*/
@Component
public class ContractAllotDao extends BaseDao implements IContractAllotDao{
	/**
	 * 查询服务人员所有的保单信息列表
	 * @param model
	 * @return List<IContractAllotHisVOModel>
	 * @description: 根据分配前服务人员代码查询服务人员所有的保单信息列表
	 */
	@Override
	public List<IContractAllotHisVOModel> queryContList(IContractAllotHisVOModel model) {
		return this.getSqlMapClientTemplate().queryForList("contractAllot.queryContList", model);
	}
	/** 
	 * 更新保单服务人员
	* @param model
	* @param user
	* @return  Boolean
	* @description:更新保单服务人员
	*/
	@Override
	public Boolean updateContS(IContractAllotHisVOModel model,IUserModel user) {
		this.getSqlMapClientTemplate().update("contractAllot.updateContS",model);
		return true;
	}
	/** 
	* 查询分配前服务人员以及机构,保单等信息
	* @param model
	* @return IContractAllotHisVOModel
	* @description: 根据保单号查询分配前服务人员以及机构,保单等信息
	*/
	@Override
	public IContractAllotHisVOModel queryContInfo(IContractAllotHisVOModel model) {
		return (IContractAllotHisVOModel)this.getSqlMapClientTemplate().queryForObject("contractAllot.queryContInfo", model);
	}
	/** 
	* 通过分配后服务人员代码查询姓名
	* @param model
	* @return IContractAllotHisVOModel
	* @description: 通过分配后服务人员代码查询姓名
	*/
	@Override
	public IContractAllotHisVOModel queryAftSName(IContractAllotHisVOModel model) {
		return (IContractAllotHisVOModel)this.getSqlMapClientTemplate().queryForObject("contractAllot.queryAftSName", model);
	}
	/** 
	 * 根据分配前服务人员代码查询服务人员代码
	* @param model
	* @return Boolean
	* @description: 根据分配前服务人员代码查询服务人员代码是否存在 
	*/
	@Override
	public IContractAllotHisVOModel queryServiceId(IContractAllotHisVOModel model) {
		return (IContractAllotHisVOModel) this.getSqlMapClientTemplate().queryForObject("contractAllot.queryServiceId", model);
		 
	}
	/** 
	 * 根据保单号查询保单号
	* @param model
	* @return IContractAllotHisVOModel
	* @description: 根据保单号查询保单号是否存在
	*/
	@Override
	public IContractAllotHisVOModel queryPCode(IContractAllotHisVOModel model) {
		return (IContractAllotHisVOModel) this.getSqlMapClientTemplate().queryForObject("contractAllot.queryPCode", model);
		
	}
	/** 
	 * 增加服务人员变更历史记录
	* @param model
	* @param user
	* @return Boolean
	* @description: 增加服务人员变更历史记录
	*/
	@Override
	public Boolean addContHis(IContractAllotHisVOModel model,IUserModel user) {
		this.getSqlMapClientTemplate().insert("contractAllot.addContHis",model);
		return true;
	}

}
