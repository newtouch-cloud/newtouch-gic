package com.ca.cacore.manage.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.dao.funcmenu.FuncMenuURITypeDao;
import com.ca.cacore.manage.model.bo.FuncMenuURITypeModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;
@Service
public class FuncMenuURITypeSelect extends ServerBase implements IDynamicSelectDataSupport {
	@Autowired private FuncMenuURITypeDao funcMenuURITypeDao;
	//获取菜单访问类型标签（下拉）内容
	@Override
	public List<IDynamicSelectData> getData() {
		List<FuncMenuURITypeModel> list=funcMenuURITypeDao.getAllFuncMenuURITypeData();
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		for (FuncMenuURITypeModel funcMenuURITypeModel : list) {
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(funcMenuURITypeModel.getUritype_code());
			sd.setName(funcMenuURITypeModel.getUritype_name());
			sd.setDisplayLable(funcMenuURITypeModel.getUritype_name());
			sds.add(sd);
		}
		return sds;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
