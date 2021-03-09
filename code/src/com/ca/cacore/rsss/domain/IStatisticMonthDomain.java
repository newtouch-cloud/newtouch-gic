package com.ca.cacore.rsss.domain;

import java.util.List;

import com.ca.cacore.rsss.model.bo.StatisticMonthModel;
/**
 * 
 * @author fqy
 * @since 2014-1-21
 * @description 统计季度数据字典Domain层
 */
public interface IStatisticMonthDomain {
	public List<StatisticMonthModel> queryStatisticMonth();
}
