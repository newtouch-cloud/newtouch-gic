package com.newtouch.team.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;
@Service
public class TeamTypeSelect extends ServerBase implements IDynamicSelectDataSupport{
	//获取状态标签（下拉）内容
		@Override
		public List<IDynamicSelectData> getData(){
			List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
			IDynamicSelectData  sd1=new DynamicSelectData();
			sd1.setId("T");
			sd1.setName("团队");
			sd1.setDisplayLable("团队");
			IDynamicSelectData  sd2=new DynamicSelectData();
			sd2.setId("D");
			sd2.setName("部门");
			sd2.setDisplayLable("部门");
			sds.add(sd1);
			sds.add(sd2);
			return sds;
		}

		@Override
		public List<IDynamicSelectData> getData(String filterParameter) {
			return null;
		}
}
