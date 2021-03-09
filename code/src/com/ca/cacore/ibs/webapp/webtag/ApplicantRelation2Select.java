package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.domain.IApplicantRelation2Domain;
import com.ca.cacore.ibs.model.bo.ApplicantRelation2Model;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2013年12月10日   
* @author  xxz
* @description:被保人成员关系动态标签：answerTypeSelect
 */
@Service
public class ApplicantRelation2Select extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IApplicantRelation2Domain applicantRelation2Domain;

	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<ApplicantRelation2Model> list = applicantRelation2Domain.queryApplicantRelation2();
		for(ApplicantRelation2Model model:list){
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(model.getRelation_code());
			sd.setName(model.getRelation_name());
			sd.setDisplayLable(model.getRelation_name());
			sds.add(sd);
		}
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
