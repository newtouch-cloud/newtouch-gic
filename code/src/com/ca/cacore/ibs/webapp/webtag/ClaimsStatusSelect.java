package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.dao.IClaimsStatusDao;
import com.ca.cacore.ibs.model.bo.ClaimsStatusModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;


/**
* @since:    2014年2月10日   
* @author    wang_ds
* @description:理赔状态标签
*/
@Service
public class ClaimsStatusSelect extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IClaimsStatusDao claimsStatusDao;
	@Override
	public List<IDynamicSelectData> getData() {
		List<ClaimsStatusModel> list=claimsStatusDao.queryClaimsStatus();
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		for(ClaimsStatusModel model:list){
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(model.getClaimsStatus_Code());
			sd.setName(model.getClaimsStatus_Name());
			sd.setDisplayLable(model.getClaimsStatus_Name());
			sds.add(sd);
		}
		return sds;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
