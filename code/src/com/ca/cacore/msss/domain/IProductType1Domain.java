package com.ca.cacore.msss.domain;

import java.util.List;

import com.ca.cacore.msss.model.bo.ProductType1Model;
/**
 * 
 * @author Wangds
 * @since 2013-11-20
 * @description 产品分类1数据字典Domain层
 */
public interface IProductType1Domain {
	public List<ProductType1Model> queryProductType1();
}
