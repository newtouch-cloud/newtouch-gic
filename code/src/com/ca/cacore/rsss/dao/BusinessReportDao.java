package com.ca.cacore.rsss.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.rsss.model.vo.BusinessReportVOModel1;
import com.ca.cacore.rsss.model.vo.BusinessReportVOModel2;
import com.newtouch.core.daobase.BaseDao;
/**
 * 
* @since:    2014年1月15日   
* @author    ZhangChen
* @description:保险代理机构业务汇总表(一)保险代理机构业务汇总表(二) dao 实现类
 */
@Component
public class BusinessReportDao extends BaseDao implements IBusinessReportDao {

	/**
	 * 
	* 
	* @param model
	* @return List<BusinessReportVOModel1>
	* @description:查询保险代理机构业务汇总表(一)
	 */
	@Override
	public List<BusinessReportVOModel1> queryReport1(BusinessReportVOModel1 model) {
		
		return this.getSqlMapClientTemplate().queryForList("exportReport.queryBusinessReport1",model);
		
	}
	
	/**
	 * 
	* 
	* @param model
	* @return List<BusinessReportVOModel2>
	* @description:查询保险代理机构业务汇总表(二)
	 */
	@Override
	public List<BusinessReportVOModel2> queryReport2(BusinessReportVOModel2 model) {
		return this.getSqlMapClientTemplate().queryForList("exportReport.queryBusinessReport2",model);
	}

}
