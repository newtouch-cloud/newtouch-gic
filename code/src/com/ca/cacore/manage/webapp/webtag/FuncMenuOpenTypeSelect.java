package com.ca.cacore.manage.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.dao.funcmenu.FuncMenuOpenTypeDao;
import com.ca.cacore.manage.model.bo.FuncMenuOpenTypeModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;
@Service
public class FuncMenuOpenTypeSelect extends ServerBase implements IDynamicSelectDataSupport {
	@Autowired private FuncMenuOpenTypeDao funcMenuOpenTypeDao;
	//获取菜单打开方式标签（下拉）内容
	@Override
	public List<IDynamicSelectData> getData() {
		List<FuncMenuOpenTypeModel> list=funcMenuOpenTypeDao.getAllFuncMenuOpenTypeData();
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		for (FuncMenuOpenTypeModel funcMenuOpenTypeModel : list) {
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(funcMenuOpenTypeModel.getOpentype_code());
			sd.setName(funcMenuOpenTypeModel.getOpentype_name());
			sd.setDisplayLable(funcMenuOpenTypeModel.getOpentype_name());
			sds.add(sd);
		}
		return sds;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
