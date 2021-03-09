package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.bo.ConservationItemModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class ConservationItemDao extends BaseDao implements IConservationItemDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ConservationItemModel> queryConservationItem() {
		return this.getSqlMapClientTemplate().queryForList("conservationItem.queryConservationItem");
	}
}
