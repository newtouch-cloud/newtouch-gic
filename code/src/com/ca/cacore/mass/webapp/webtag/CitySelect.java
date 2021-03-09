package com.ca.cacore.mass.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.mass.domain.ICityDomain;
import com.ca.cacore.mass.model.bo.CityModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2013年12月5日 下午5:29:34   
* @author    ss
* @description:市级标签CitySelect
*/
@Service
public class CitySelect extends ServerBase implements IDynamicSelectDataSupport {
	@Autowired private ICityDomain cityDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<CityModel> list=cityDomain.getCities();
		for (CityModel cityModel : list) {
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(cityModel.getCity_code());
			sd.setName(cityModel.getCity_name());
			sd.setDisplayLable(cityModel.getCity_name());
			sds.add(sd);
		}
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
