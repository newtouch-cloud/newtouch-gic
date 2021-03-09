package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.bo.BeneficiaryTypeModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class BeneficiaryTypeDao extends BaseDao implements IBeneficiaryTypeDao{

	@Override
	public List<BeneficiaryTypeModel> queryBeneficiaryType() {
		return this.getSqlMapClientTemplate().queryForList("beneficiayType.queryBeneficiaryType");
	}

}
