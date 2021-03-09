package com.ca.cacore.mass.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.PoliticalModel;
import com.ca.cacore.mass.domain.LibraryDomain;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;
/**
* @company:  newtouch
* @since:    2013年11月18日 下午3:41:52   
* @author    silence
* @description:政治面貌标签PoliticalSelect
*/
@Service
public class PoliticalSelect extends ServerBase implements IDynamicSelectDataSupport {
	@Autowired private LibraryDomain libraryDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<PoliticalModel> list=libraryDomain.queryPolitical();
		for (PoliticalModel politicalModel : list) {
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(politicalModel.getPolitical_code());
			sd.setName(politicalModel.getPolitical_name());
			sd.setDisplayLable(politicalModel.getPolitical_name());
			sds.add(sd);
		}
		return sds;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
