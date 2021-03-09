package com.ca.cacore.msss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ca.cacore.msss.dao.IProductType2Dao;
import com.ca.cacore.msss.model.bo.ProductType2Model;
/**
 * 
 * @author Wangds
 * @since 2013-11-20
 * @description 产品分类2数据字典Domain层
 */
@Service
public class ProductType2Domain implements IProductType2Domain{
	@Autowired private IProductType2Dao ProductType2Dao;
	@Override
	public List<ProductType2Model> queryProductType2() {
		return ProductType2Dao.queryProductType2();
	}
}
