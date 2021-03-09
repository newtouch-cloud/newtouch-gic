package com.ca.cacore.rsss.domain;

import java.util.List;

import com.ca.cacore.rsss.model.bo.ReportSelectModel;
/**
 * 
 * @author fqy
 * @since 2014-1-21
 * @description 统计季度数据字典Domain层
 */
public interface IReportSelectDomain {
	public List<ReportSelectModel> queryReportSelect();
}
