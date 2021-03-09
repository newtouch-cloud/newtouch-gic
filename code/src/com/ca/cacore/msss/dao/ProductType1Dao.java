package com.ca.cacore.msss.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.msss.model.bo.ProductType1Model;
import com.newtouch.core.daobase.BaseDao;
/**
 * 
 * @author Wangds
 * @since 2013-12-9
 * @description 产品分类1数据字典DAO层
 */
@Component
public class ProductType1Dao extends BaseDao implements IProductType1Dao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductType1Model> queryProductType1() {
		return this.getSqlMapClientTemplate().queryForList("ProductType1.queryProductType1");
	}

}
