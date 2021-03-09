package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.domain.IProblemTypeDomain;
import com.ca.cacore.ibs.model.bo.ProblemTypeModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;
@Service
public class ProblemTypeSelect extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IProblemTypeDomain problemTypeDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> lis=new ArrayList<IDynamicSelectData>();
		List<ProblemTypeModel> list=problemTypeDomain.queryProblemType();
		for(ProblemTypeModel model:list){
			IDynamicSelectData dsd=new DynamicSelectData();
			dsd.setId(model.getType_code());
			dsd.setName(model.getType_name());
			dsd.setDisplayLable(model.getType_name());
			lis.add(dsd);
		}
		return lis;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}
}
