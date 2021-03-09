package com.ca.cacore.msss.domain;

import java.util.List;

import com.ca.cacore.msss.model.bo.ProductPeriodTypeModel;
/**
 * 
 * @author Wangds
 * @since 2013-11-20
 * @description 保险期限分类数据字典Domain层
 */
public interface IProductPeriodTypeDomain {
	public List<ProductPeriodTypeModel> queryProductPeriodType();
}
