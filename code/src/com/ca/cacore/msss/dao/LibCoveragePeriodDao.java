package com.ca.cacore.msss.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.msss.model.bo.ILibCoveragePeriodModel;
import com.newtouch.core.daobase.BaseDao;
/**
 * 
 * @author Wangds
 * @since 2013-12-9
 * @description 保障期限类型数据字典
 */
@Component
public class LibCoveragePeriodDao extends BaseDao implements ILibCoveragePeriodDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ILibCoveragePeriodModel> queryLibCoveragePeriod() {
		return this.getSqlMapClientTemplate().queryForList("LibCoveragePeriod.queryLibCoveragePeriod");
	}

}
