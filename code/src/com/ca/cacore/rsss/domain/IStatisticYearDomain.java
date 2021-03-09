package com.ca.cacore.rsss.domain;

import java.util.List;

import com.ca.cacore.rsss.model.bo.StatisticYearModel;
/**
 * 
 * @author fqy
 * @since 2014-1-21
 * @description 统计年份数据字典Domain层
 */
public interface IStatisticYearDomain {
	public List<StatisticYearModel> queryStatisticYear();
}
