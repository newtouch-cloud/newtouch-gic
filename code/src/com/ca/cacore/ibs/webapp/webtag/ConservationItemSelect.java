package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.domain.IConservationItemDomain;
import com.ca.cacore.ibs.model.bo.ConservationItemModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

@Service
public class ConservationItemSelect extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IConservationItemDomain conservationItemDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> lis=new ArrayList<IDynamicSelectData>();
		List<ConservationItemModel> list=conservationItemDomain.queryConservationItem();
		for(ConservationItemModel model:list){
			IDynamicSelectData dsd=new DynamicSelectData();
			dsd.setId(model.getConservationItem_Code().toString());
			dsd.setName(model.getConservationItem_Name());
			dsd.setDisplayLable(model.getConservationItem_Name());
			lis.add(dsd);
		}
		return lis;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
