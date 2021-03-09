package com.ca.cacore.msss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ca.cacore.msss.dao.IProductType1Dao;
import com.ca.cacore.msss.model.bo.ProductType1Model;
/**
 * 
 * @author Wangds
 * @since 2013-11-20
 * @description 产品分类1数据字典Domain层
 */
@Service
public class ProductType1Domain implements IProductType1Domain{
	@Autowired private IProductType1Dao ProductType1Dao;
	@Override
	public List<ProductType1Model> queryProductType1() {
		return ProductType1Dao.queryProductType1();
	}
}
