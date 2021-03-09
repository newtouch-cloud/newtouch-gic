package com.ca.cacore.msss.domain;

import java.util.List;

import com.ca.cacore.msss.model.bo.ProductInsTypeModel;
/**
 * 
 * @author Wangds
 * @since 2013-11-20
 * @description 主附险分类数据字典Domain层
 */
public interface IProductInsTypeDomain {
	public List<ProductInsTypeModel> queryProductInsType();
}
