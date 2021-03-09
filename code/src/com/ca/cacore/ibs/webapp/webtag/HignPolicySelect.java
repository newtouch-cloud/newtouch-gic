package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
 * 许小珍：是否高额件动态标签
 * @author xxz521
 */
@Service
public class HignPolicySelect  extends ServerBase implements IDynamicSelectDataSupport {
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		IDynamicSelectData sd1 = new DynamicSelectData();
		sd1.setId("Y");
		sd1.setName("是");
		sd1.setDisplayLable("是");
		IDynamicSelectData sd2 = new DynamicSelectData();
		sd2.setId("N");
		sd2.setName("否");
		sd2.setDisplayLable("否");
		sds.add(sd1);
		sds.add(sd2);
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}
}
