package com.ca.cacore.msss.domain;

import java.util.List;

import com.ca.cacore.msss.model.bo.LibChargeTypeModel;
/**
 * 
 * @author Wang_ds
 * @since 2013-11-20
 * @description 缴费方式数据字典Domain层
 */
public interface ILibChargeTypeDomain {
	public List<LibChargeTypeModel> queryLibChargeType();
}
