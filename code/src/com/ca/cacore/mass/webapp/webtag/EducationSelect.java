package com.ca.cacore.mass.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.EducationModel;
import com.ca.cacore.mass.domain.LibraryDomain;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;
/**
* @company:  newtouch
* @since:    2013年11月18日 下午3:35:17   
* @author    silence
* @description:教育程度EducationSelect
*/
@Service
public class EducationSelect extends ServerBase implements IDynamicSelectDataSupport {
	@Autowired private LibraryDomain libraryDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<EducationModel> list=libraryDomain.queryEducation();
		for (EducationModel educationModel : list) {
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(educationModel.getEducation_code());
			sd.setName(educationModel.getEducation_name());
			sd.setDisplayLable(educationModel.getEducation_name());
			sds.add(sd);
		}
		return sds;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
