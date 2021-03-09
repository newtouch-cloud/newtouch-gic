package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.msss.domain.ILibCoveragePeriodDomain;
import com.ca.cacore.msss.model.bo.ILibCoveragePeriodModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
 * 保障期限类型标签
 * @author xxz521
 *
 */
@Service
public class LibCoveragePeriodSelect  extends ServerBase implements IDynamicSelectDataSupport {
	@Autowired private ILibCoveragePeriodDomain  libCoveragePeriodDomain ;
	
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<ILibCoveragePeriodModel>  list= libCoveragePeriodDomain.queryLibCoveragePeriod() ;
		for(ILibCoveragePeriodModel model:list){
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(model.getCoverage_period_code());
			sd.setName(model.getCoverage_period_name());
			sd.setDisplayLable(model.getCoverage_period_name());
			sds.add(sd);
		}
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}
}
