package com.ca.cacore.ibs.dao;

import java.util.List;

import com.ca.cacore.ibs.model.vo.IContractAllotHisVOModel;
/**
 * @author WanBo
 * @description 保单分配轨迹查询DAO层接口
 */
public interface IContractAllotHisDao {
	/**
	 * 查询保单分配历史轨迹
	 * @param IContractAllotHisVOModel
	 * @return List<IContractAllotHisVOModel>
	 * @description: 查询保单分配历史轨迹
	 */
	public List<IContractAllotHisVOModel> queryContList(IContractAllotHisVOModel model);
}
