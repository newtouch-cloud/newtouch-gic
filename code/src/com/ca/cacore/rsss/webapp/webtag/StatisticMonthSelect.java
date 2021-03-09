package com.ca.cacore.rsss.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.domain.IStatisticMonthDomain;
import com.ca.cacore.rsss.model.bo.StatisticMonthModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2014年1月21日    
* @author    fqy
* @description:   StatisticMonthSelect 统计季度标签
*/
@Service
public class StatisticMonthSelect extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IStatisticMonthDomain StatisticMonthDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<StatisticMonthModel> list = StatisticMonthDomain.queryStatisticMonth();
		List<IDynamicSelectData> listSelect = new ArrayList<IDynamicSelectData>();
		for(StatisticMonthModel model:list){
			IDynamicSelectData dataSelect = new DynamicSelectData();
			dataSelect.setId(model.getStatistic_month_code());
			dataSelect.setName(model.getStatistic_month_name());
			dataSelect.setDisplayLable(model.getStatistic_month_name());
			listSelect.add(dataSelect);
		}
		return listSelect;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
