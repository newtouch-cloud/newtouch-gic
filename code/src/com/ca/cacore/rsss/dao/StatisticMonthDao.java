package com.ca.cacore.rsss.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.rsss.model.bo.StatisticMonthModel;
import com.newtouch.core.daobase.BaseDao;
/**
 * 
 * @author fqy
 * @since 2014-1-21
 * @description 统计季度数据字典DAO层
 */
@Component
public class StatisticMonthDao extends BaseDao implements IStatisticMonthDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<StatisticMonthModel> queryStatisticMonth() {
		List<StatisticMonthModel> list = new ArrayList<StatisticMonthModel>();
		StatisticMonthModel model1 = new StatisticMonthModel();
		model1.setStatistic_month_code("01");
		model1.setStatistic_month_name("第一季度");
		list.add(model1);
		StatisticMonthModel model2 = new StatisticMonthModel();
		model2.setStatistic_month_code("04");
		model2.setStatistic_month_name("第二季度");
		list.add(model2);
		StatisticMonthModel model3 = new StatisticMonthModel();
		model3.setStatistic_month_code("07");
		model3.setStatistic_month_name("第三季度");
		list.add(model3);
		StatisticMonthModel model4 = new StatisticMonthModel();
		model4.setStatistic_month_code("10");
		model4.setStatistic_month_name("第四季度");
		list.add(model4);
		return list;
	}

}
