package com.ca.cacore.rsss.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.domain.IReportSelectDomain;
import com.ca.cacore.rsss.model.bo.ReportSelectModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2014年6月6日    
* @author    wjq
* @description:   统计报表
*/
@Service
public class ReportSelect extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IReportSelectDomain reportSelectDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<ReportSelectModel> list = reportSelectDomain.queryReportSelect();
		List<IDynamicSelectData> listSelect = new ArrayList<IDynamicSelectData>();
		for(ReportSelectModel model:list){
			IDynamicSelectData dataSelect = new DynamicSelectData();
			dataSelect.setId(model.getReport_code());
			dataSelect.setName(model.getReport_name());
			dataSelect.setDisplayLable(model.getReport_name());
			listSelect.add(dataSelect);
		}
		return listSelect;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
