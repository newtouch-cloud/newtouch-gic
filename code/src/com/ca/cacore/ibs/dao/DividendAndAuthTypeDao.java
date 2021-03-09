package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.bo.ProductAuthTypeModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class DividendAndAuthTypeDao extends BaseDao implements IDividendAndAuthTypeDao{

	@Override
	public List<ProductAuthTypeModel> queryAuthType() {
		return this.getSqlMapClientTemplate().queryForList("DividendAndAuthType.queryAuthType");
	}

	@Override
	public List<ProductAuthTypeModel> queryDividendType() {
		return this.getSqlMapClientTemplate().queryForList("DividendAndAuthType.queryDividenType");
	}
	
}
