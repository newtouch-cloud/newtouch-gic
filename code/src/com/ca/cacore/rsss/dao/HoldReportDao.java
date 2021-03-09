package com.ca.cacore.rsss.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.rsss.model.vo.HoldReportVOModel;
import com.newtouch.core.daobase.BaseDao;
/**
 * 
* @since:    2014年1月15日   
* @author    ZhangChen
* @description:保险代理机构业务汇总表(一)保险代理机构业务汇总表(二) dao 实现类
 */
@Component
public class HoldReportDao extends BaseDao implements IHoldReportDao {

	/**
	 * 
	* 
	* @param model
	* @return List<BusinessReportVOModel1>
	* @description:查询保险代理机构业务汇总表(一)
	 */
	@Override
	public List<HoldReportVOModel> queryReport(HoldReportVOModel model) {
		
		return (List<HoldReportVOModel>)this.getSqlMapClientTemplate().queryForList("exportReport.queryHoldReport",model);
	}

}
