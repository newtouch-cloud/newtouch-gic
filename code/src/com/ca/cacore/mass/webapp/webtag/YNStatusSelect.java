package com.ca.cacore.mass.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2013年11月27日 上午10:00:06   
* @author    ss
* @description:是否状态标签YNStatusSelect
*/
@Service
public class YNStatusSelect extends ServerBase implements IDynamicSelectDataSupport {
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		IDynamicSelectData sd1=new DynamicSelectData();
		sd1.setId("Y");
		sd1.setName("是");
		sd1.setDisplayLable("是");
		IDynamicSelectData sd2=new DynamicSelectData();
		sd2.setId("N");
		sd2.setName("否");
		sd2.setDisplayLable("否");
		sds.add(sd1);
		sds.add(sd2);
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		// TODO Auto-generated method stub
		return null;
	}

}
