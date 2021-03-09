package com.ca.cacore.rsss.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.domain.IStatisticYearDomain;
import com.ca.cacore.rsss.model.bo.StatisticYearModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2014年1月21日    
* @author    fqy
* @description:   StatisticYearSelect 统计年份标签
*/
@Service
public class StatisticYearSelect extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IStatisticYearDomain StatisticYearDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<StatisticYearModel> list = StatisticYearDomain.queryStatisticYear();
		List<IDynamicSelectData> listSelect = new ArrayList<IDynamicSelectData>();
		for(StatisticYearModel model:list){
			IDynamicSelectData dataSelect = new DynamicSelectData();
			dataSelect.setId(model.getStatistic_year_code());
			dataSelect.setName(model.getStatistic_year_name());
			dataSelect.setDisplayLable(model.getStatistic_year_name());
			listSelect.add(dataSelect);
		}
		return listSelect;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
