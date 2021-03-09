package com.ca.cacore.manage.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.GenderModel;
import com.ca.cacore.mass.domain.LibraryDomain;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

@Service
public class GenderSelect extends ServerBase implements IDynamicSelectDataSupport {
	@Autowired private LibraryDomain libraryDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<GenderModel> list=libraryDomain.queryGender();
		for (GenderModel genderModel : list) {
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(genderModel.getGender_code());
			sd.setName(genderModel.getGender_name());
			sd.setDisplayLable(genderModel.getGender_name());
			sds.add(sd);
		}
		return sds;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}
}
