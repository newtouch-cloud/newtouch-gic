package com.newtouch.component.axis2.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.newtouch.component.axis2.model.ProtocolQueryVoModel;
import com.newtouch.core.daobase.BaseDao;
/**
* @since:    2014年4月14日   
* @author    ZhangChen
* @description:
 */
@Component
public class QuerySaleInfoDao extends BaseDao implements IQuerySaleInfoDao {
	
	
	/**
	 * 
	* @param model
	* @return ProtocolQueryVoModel
	* @description: querySaleInfo 查询中介人员是否存在 by张晨
	 */
	@Override
	public ProtocolQueryVoModel querySalesInfo(ProtocolQueryVoModel model){
		return (ProtocolQueryVoModel) this.getSqlMapClientTemplate().queryForObject("querySaleInfo.querySalesInfo",model);
	}
	
	/**
	 * 
	* @param model
	* @return ProtocolQueryVoModel
	* @description: querySaleInfo 查询协议号是否存在 by张晨 
	 */
	@Override
	public List<ProtocolQueryVoModel> checkAgreemengtInfo(ProtocolQueryVoModel model){
		return (List<ProtocolQueryVoModel>) this.getSqlMapClientTemplate().queryForList("querySaleInfo.checkAgreemengtInfo",model);
	}

}
