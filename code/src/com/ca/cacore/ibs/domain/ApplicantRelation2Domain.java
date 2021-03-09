package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.dao.ApplicantRelation2Dao;
import com.ca.cacore.ibs.model.bo.ApplicantRelation2Model;

@Service
public class ApplicantRelation2Domain implements IApplicantRelation2Domain{
	@Autowired private ApplicantRelation2Dao applicantRelation2Dao ;

	@Override
	public List<ApplicantRelation2Model> queryApplicantRelation2() {
		return applicantRelation2Dao.queryApplicantRelation2();
	}

	@Override
	public List<ApplicantRelation2Model> queryApplicantRelation1() {
		return applicantRelation2Dao.queryApplicantRelation1();
	}
}
