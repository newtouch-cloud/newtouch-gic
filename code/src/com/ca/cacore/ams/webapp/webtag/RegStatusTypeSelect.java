package com.ca.cacore.ams.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ams.dao.RegStatusTypeDao;
import com.ca.cacore.ams.model.bo.RegStatusTypeModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;
@Service
public class RegStatusTypeSelect extends ServerBase implements IDynamicSelectDataSupport {
	@Autowired private RegStatusTypeDao RegStatusTypeDao;
	//获取权限类型信息标签内容
	@Override
	public List<IDynamicSelectData> getData() {
		List<RegStatusTypeModel> list=RegStatusTypeDao.getAllRegStatusTypeData();
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		for (RegStatusTypeModel regStatusTypeModel : list) {
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(regStatusTypeModel.getRegulation_status_code());
			sd.setName(regStatusTypeModel.getRegulation_status_name());
			sd.setDisplayLable(regStatusTypeModel.getRegulation_status_name());
			sds.add(sd);
		}
		return sds;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}


}
