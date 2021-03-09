package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.dao.IContractAllotHisDao;
import com.ca.cacore.ibs.model.vo.IContractAllotHisVOModel;
import com.newtouch.core.serverbase.ServerBase;
/**
 * @author WanBo
 * @description 保单分配轨迹查询Domain层
 */
@Service
public class ContractAllotHisDomain extends ServerBase implements IContractAllotHisDomain{
	@Autowired 
	private IContractAllotHisDao ContractAllotHisDao;
	
	/**
	 * 查询保单分配历史轨迹
	 * @param IContractAllotHisVOModel
	 * @return List<IContractAllotHisVOModel>
	 * @description: 查询保单分配历史轨迹
	 */
	public List<IContractAllotHisVOModel> queryContList(IContractAllotHisVOModel model) {
		return ContractAllotHisDao.queryContList(model);
	}
	

}
