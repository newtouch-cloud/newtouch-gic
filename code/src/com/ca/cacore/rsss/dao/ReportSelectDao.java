package com.ca.cacore.rsss.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.rsss.model.bo.ReportSelectModel;
import com.newtouch.core.daobase.BaseDao;
/**
 * 
 * @author fqy
 * @since 2014-1-21
 * @description 统计季度数据字典DAO层
 */
@Component
public class ReportSelectDao extends BaseDao implements IReportSelectDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ReportSelectModel> queryReportSelect() {
		return this.getSqlMapClientTemplate().queryForList("reportSelect.getReports");
	}

}
