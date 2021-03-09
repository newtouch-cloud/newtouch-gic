package com.ca.cacore.mass.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.NationModel;
import com.ca.cacore.mass.domain.LibraryDomain;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2013年11月18日 下午3:31:07   
* @author    silence
* @description:民族标签NationSelect
*/
@Service
public class NationSelect extends ServerBase implements IDynamicSelectDataSupport {
	@Autowired private LibraryDomain libraryDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<NationModel> list=libraryDomain.queryNation();
		for (NationModel nationModel : list) {
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(nationModel.getNation_code());
			sd.setName(nationModel.getNation_name());
			sd.setDisplayLable(nationModel.getNation_name());
			sds.add(sd);
		}
		return sds;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
