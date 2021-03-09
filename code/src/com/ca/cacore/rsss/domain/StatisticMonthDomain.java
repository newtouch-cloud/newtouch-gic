package com.ca.cacore.rsss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.dao.IStatisticMonthDao;
import com.ca.cacore.rsss.model.bo.StatisticMonthModel;
/**
 * 
 * @author fqy
 * @since 2014-1-21
 * @description 统计季度数据字典Domain层
 */
@Service
public class StatisticMonthDomain implements IStatisticMonthDomain{
	@Autowired private IStatisticMonthDao StatisticMonthDao;

	@Override
	public List<StatisticMonthModel> queryStatisticMonth() {
		return StatisticMonthDao.queryStatisticMonth();
	}
}
