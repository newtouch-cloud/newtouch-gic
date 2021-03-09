package com.ca.cacore.rsss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.dao.IStatisticYearDao;
import com.ca.cacore.rsss.model.bo.StatisticYearModel;
/**
 * 
 * @author fqy
 * @since 2014-1-21
 * @description 统计年份数据字典Domain层
 */
@Service
public class StatisticYearDomain implements IStatisticYearDomain{
	@Autowired private IStatisticYearDao StatisticYearDao;

	@Override
	public List<StatisticYearModel> queryStatisticYear() {
		return StatisticYearDao.queryStatisticYear();
	}
}
