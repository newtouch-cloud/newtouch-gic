package com.ca.cacore.manage.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.dao.branch.AuthTypeDao;
import com.ca.cacore.manage.model.bo.AuthTypeModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;
@Service
public class AuthTypeSelect extends ServerBase implements IDynamicSelectDataSupport {
	@Autowired private AuthTypeDao AuthTypeDao;
	//获取权限类型信息标签内容
	@Override
	public List<IDynamicSelectData> getData() {
		List<AuthTypeModel> list=AuthTypeDao.getAllAuthTypeData();
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		for (AuthTypeModel authTypeModel : list) {
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(authTypeModel.getAuth_type_code());
			sd.setName(authTypeModel.getAuth_type_name());
			sd.setDisplayLable(authTypeModel.getAuth_type_name());
			sds.add(sd);
		}
		return sds;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}


}
