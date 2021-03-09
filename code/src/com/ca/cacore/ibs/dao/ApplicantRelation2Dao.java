package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.bo.ApplicantRelation2Model;
import com.newtouch.core.daobase.BaseDao;

@Component
public class ApplicantRelation2Dao extends BaseDao implements IApplicantRelation2Dao{
	@Override
	public List<ApplicantRelation2Model> queryApplicantRelation2() {
		return this.getSqlMapClientTemplate().queryForList("applicantRelation2.queryApplicantRelation2");
	}

	@Override
	public List<ApplicantRelation2Model> queryApplicantRelation1() {
		return this.getSqlMapClientTemplate().queryForList("applicantRelation2.queryApplicantRelation1");
	}
}
