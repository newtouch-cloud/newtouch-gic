package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.domain.IProblemStatusDomain;
import com.ca.cacore.ibs.model.bo.ProblemStatusModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

@Service
public class ProblemStatusSelect extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IProblemStatusDomain problemStatusDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> ds=new ArrayList<IDynamicSelectData>();
		List<ProblemStatusModel> list=problemStatusDomain.queryProblemStatus();
		for(ProblemStatusModel model:list){
			IDynamicSelectData dsd=new DynamicSelectData();
			dsd.setId(model.getStatus_code());
			dsd.setName(model.getStatus_name());
			dsd.setDisplayLable(model.getStatus_name());
			ds.add(dsd);
		}
		return ds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
