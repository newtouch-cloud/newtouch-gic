package com.newtouch.component.axis2.dao;

import org.springframework.stereotype.Component;

import com.newtouch.component.axis2.model.FeeInfoVoModel;
import com.newtouch.core.daobase.BaseDao;
/**
* @since:    2014年7月17日   
* @author    wangjianqiang
* @description:
 */
@Component
public class FeeInfoDao extends BaseDao implements IFeeInfoDao {
	
	
	/**
	 * 
	* @param model
	* @return ProtocolQueryVoModel
	* @description: querySaleInfo 查询中介人员是否存在 by张晨
	 */
	@Override
	public FeeInfoVoModel feeInfo(FeeInfoVoModel model){
		return (FeeInfoVoModel) this.getSqlMapClientTemplate().queryForObject("FeeInfo.queryFeeInfo",model);
	}

	@Override
	public FeeInfoVoModel agentInfo(FeeInfoVoModel model1) {
		return (FeeInfoVoModel) this.getSqlMapClientTemplate().queryForObject("FeeInfo.queryAgentInfo",model1);
	}
	

}
