package com.ca.cacore.ams.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ams.model.bo.RegStatusTypeModel;
import com.newtouch.core.daobase.BaseDao;


	@Component
	public class RegStatusTypeDao extends BaseDao {
		@SuppressWarnings("unchecked")
		public List<RegStatusTypeModel> getAllRegStatusTypeData() {
			return (List<RegStatusTypeModel>)this.getSqlMapClientTemplate().queryForList("regStatusType.getRegStatusType");
		}
}
