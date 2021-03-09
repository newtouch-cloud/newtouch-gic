package com.ca.cacore.rsss.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.rsss.model.bo.StatisticYearModel;
import com.newtouch.core.daobase.BaseDao;
/**
 * 
 * @author fqy
 * @since 2014-1-21
 * @description 统计年份数据字典DAO层
 */
@Component
public class StatisticYearDao extends BaseDao implements IStatisticYearDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<StatisticYearModel> queryStatisticYear() {
		List<StatisticYearModel> list = new ArrayList<StatisticYearModel>();
		for(int i=0; i<5; i++){
			StatisticYearModel model = new StatisticYearModel();
			model.setStatistic_year_code((2013+i)+"");
			model.setStatistic_year_name((2013+i)+"");
			list.add(model);
		}
		return list;
	}

}
