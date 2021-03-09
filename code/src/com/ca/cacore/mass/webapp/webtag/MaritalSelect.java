package com.ca.cacore.mass.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.MaritalModel;
import com.ca.cacore.mass.domain.LibraryDomain;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;
/**
* @company:  newtouch
* @since:    2013年11月18日 下午3:46:11   
* @author    silence
* @description:婚姻状态标签MaritalSelect
*/
@Service
public class MaritalSelect extends ServerBase implements IDynamicSelectDataSupport {
	@Autowired private LibraryDomain libraryDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<MaritalModel> list=libraryDomain.queryMarital();
		for (MaritalModel maritalModel : list) {
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(maritalModel.getMarital_stat_code());
			sd.setName(maritalModel.getMarital_stat_name());
			sd.setDisplayLable(maritalModel.getMarital_stat_name());
			sds.add(sd);
		}
		return sds;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
