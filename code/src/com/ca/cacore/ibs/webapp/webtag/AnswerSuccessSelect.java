package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

@Service
public class AnswerSuccessSelect extends ServerBase implements IDynamicSelectDataSupport{

	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		IDynamicSelectData sd1 = new DynamicSelectData();
		sd1.setId("Y");
		sd1.setName("成功");
		sd1.setDisplayLable("成功");
		IDynamicSelectData sd2 = new DynamicSelectData();
		sd2.setId("N");
		sd2.setName("失败");
		sd2.setDisplayLable("失败");
		sds.add(sd1);
		sds.add(sd2);
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}
	
}
