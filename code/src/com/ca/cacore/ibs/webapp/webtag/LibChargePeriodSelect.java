package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.msss.domain.ILibChargePeriodDomain;
import com.ca.cacore.msss.model.bo.LibChargePeriodModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
 * 缴费期限类型动态标签
 * @author xxz521
 *
 */
@Service
public class LibChargePeriodSelect extends ServerBase implements IDynamicSelectDataSupport {
	@Autowired private ILibChargePeriodDomain  libChargePeriodDomain ;
	
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<LibChargePeriodModel>  list= libChargePeriodDomain.queryLibChargePeriod() ;
		for(LibChargePeriodModel model:list){
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(model.getCharge_period_code());
			sd.setName(model.getCharge_period_name());
			sd.setDisplayLable(model.getCharge_period_name());
			sds.add(sd);
		}
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}
}
