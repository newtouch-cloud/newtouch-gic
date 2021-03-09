package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.bo.FeeStatusModel;
import com.newtouch.core.daobase.BaseDao;

/**
 * 
* @since:    2014年1月10日   
* @author    ZhangChen
* @description:费用处理状态标签Dao层实现类
 */
@Component
public class FeeStatusDao extends BaseDao implements IFeeStatusDao {

	/** 
	* @return List
	* @description: 查询所有费用处理状态内容
	 */
	@Override
	public List<FeeStatusModel> getStatus() {
		
		return this.getSqlMapClientTemplate().queryForList("feeStatus.getStatus");
	}

}
