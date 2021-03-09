package com.ca.cacore.msss.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @since:    2013年12月09日    
* @author    wang_ds
* @description:InsBranch_id_Select产品分类1标签
*/
@Service
public class SurrPermitSelect extends ServerBase implements IDynamicSelectDataSupport{
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		IDynamicSelectData  sd1=new DynamicSelectData();
		sd1.setId("Y");
		sd1.setName("可以");
		sd1.setDisplayLable("可以");
		IDynamicSelectData  sd2=new DynamicSelectData();
		sd2.setId("N");
		sd2.setName("不可以");
		sd2.setDisplayLable("不可以");
		sds.add(sd1);
		sds.add(sd2);
		return sds;
		}
		
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
