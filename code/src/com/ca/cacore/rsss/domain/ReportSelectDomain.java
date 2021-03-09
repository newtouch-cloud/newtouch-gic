package com.ca.cacore.rsss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.dao.IReportSelectDao;
import com.ca.cacore.rsss.model.bo.ReportSelectModel;
/**
 * 
 * @author fqy
 * @since 2014-1-21
 * @description 统计季度数据字典Domain层
 */
@Service
public class ReportSelectDomain implements IReportSelectDomain{
	@Autowired private IReportSelectDao reportSelectDao;

	@Override
	public List<ReportSelectModel> queryReportSelect() {
		return reportSelectDao.queryReportSelect();
	}
}
