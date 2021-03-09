package com.ca.cacore.mass.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.mass.domain.IProvinceDomain;
import com.ca.cacore.mass.model.bo.ProvinceModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2013年12月5日 下午2:34:09   
* @author    ss
* @description:省的下拉标签ProvinceSelect
*/
@Service
public class ProvinceSelect extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IProvinceDomain provinceDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<ProvinceModel> list=provinceDomain.querypProvince();
		for (ProvinceModel provinceModel : list) {
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(provinceModel.getProvince_code());
			sd.setName(provinceModel.getProvince_name());
			sd.setDisplayLable(provinceModel.getProvince_name());
			sds.add(sd);
		}
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
