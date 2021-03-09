package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.domain.IProblemOriginTypeDomain;
import com.ca.cacore.ibs.model.bo.ProblemOriginTypeModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

@Service
public class ProblemOriginTypeSelect extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IProblemOriginTypeDomain problemOriginTypeDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> ds=new ArrayList<IDynamicSelectData>();
		List<ProblemOriginTypeModel> list=problemOriginTypeDomain.queryProblemOriginType();
		for(ProblemOriginTypeModel model:list){
			IDynamicSelectData dsd=new DynamicSelectData();
			dsd.setId(model.getOrigin_type_code());
			dsd.setName(model.getOrigin_type_name());
			dsd.setDisplayLable(model.getOrigin_type_name());
			ds.add(dsd);
		}
		return ds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}
}
