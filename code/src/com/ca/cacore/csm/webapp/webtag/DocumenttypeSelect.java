package com.ca.cacore.csm.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;
@Service
public class DocumenttypeSelect extends ServerBase implements IDynamicSelectDataSupport{
	@Override
	public List<IDynamicSelectData> getData(){
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		IDynamicSelectData  sd1=new DynamicSelectData();
		sd1.setId("S");
		sd1.setName("身份证");
		sd1.setDisplayLable("身份证");
		sds.add(sd1);
		return sds;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		// TODO Auto-generated method stub
		return null;
	}

}
