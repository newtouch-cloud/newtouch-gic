package com.ca.cacore.manage.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

@Service
public class StatusSelect extends ServerBase implements IDynamicSelectDataSupport {
	//获取状态标签（下拉）内容
	@Override
	public List<IDynamicSelectData> getData(){
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		IDynamicSelectData  sd1=new DynamicSelectData();
		sd1.setId("1");
		sd1.setName("有效");
		sd1.setDisplayLable("有效");
		IDynamicSelectData  sd2=new DynamicSelectData();
		sd2.setId("0");
		sd2.setName("无效");
		sd2.setDisplayLable("无效");
		sds.add(sd1);
		sds.add(sd2);
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}
}
