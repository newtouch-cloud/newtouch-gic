package com.ca.cacore.msss.domain;

import java.util.List;

import com.ca.cacore.msss.model.bo.ILibCoveragePeriodModel;
/**
 * 
 * @author Wangds
 * @since 2013-11-20
 * @description 保障期限类型数据字典Domain层
 */
public interface ILibCoveragePeriodDomain {
	public List<ILibCoveragePeriodModel> queryLibCoveragePeriod();
}
