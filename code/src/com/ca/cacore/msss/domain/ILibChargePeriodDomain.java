package com.ca.cacore.msss.domain;

import java.util.List;

import com.ca.cacore.msss.model.bo.LibChargePeriodModel;
/**
 * 
 * @author Wangds
 * @since 2013-11-20
 * @description 缴费期限类型数据字典Domain层
 */
public interface ILibChargePeriodDomain {
	public List<LibChargePeriodModel> queryLibChargePeriod();
}
