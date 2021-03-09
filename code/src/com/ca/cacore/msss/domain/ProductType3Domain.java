package com.ca.cacore.msss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ca.cacore.msss.dao.IProductType3Dao;
import com.ca.cacore.msss.model.bo.ProductType3Model;
/**
 * 
 * @author Wangds
 * @since 2013-11-20
 * @description 产品分类3数据字典Domain层
 */
@Service
public class ProductType3Domain implements IProductType3Domain{
	@Autowired private IProductType3Dao ProductType3Dao;
	@Override
	public List<ProductType3Model> queryProductType3() {
		return ProductType3Dao.queryProductType3();
	}
}
