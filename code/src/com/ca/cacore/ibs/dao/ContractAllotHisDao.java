package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.vo.IContractAllotHisVOModel;
import com.newtouch.core.daobase.BaseDao;
/**
 * @author WanBo
 * @description 保单分配轨迹查询DAO层
 */
@Component
public class ContractAllotHisDao extends BaseDao implements IContractAllotHisDao{
	/**
	 * 查询保单分配历史轨迹
	 * @param IContractAllotHisVOModel
	 * @return List<IContractAllotHisVOModel>
	 * @description: 查询保单分配历史轨迹
	 */
	@Override
	public List<IContractAllotHisVOModel> queryContList(IContractAllotHisVOModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("contractAllotHis.queryCont_count",model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		return (List<IContractAllotHisVOModel>)this.getSqlMapClientTemplate().queryForList("contractAllotHis.queryContList", model);
	}
}
