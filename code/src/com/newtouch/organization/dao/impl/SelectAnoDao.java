package com.newtouch.organization.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.newtouch.core.daobase.BaseDao;
import com.newtouch.organization.dao.ISelectAnoDao;
import com.newtouch.organization.model.vo.impl.AgencyModel;

@Component
public class SelectAnoDao extends BaseDao implements ISelectAnoDao{

	//可能是webtag里的
	@Override
	public List<AgencyModel> selectAno() {
		return this.getSqlMapClientTemplate().queryForList("agency.queryUpdateAgency");
	}
	
}
