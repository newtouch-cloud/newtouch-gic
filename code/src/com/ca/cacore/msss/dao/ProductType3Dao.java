package com.ca.cacore.msss.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import com.ca.cacore.msss.model.bo.ProductType3Model;
import com.newtouch.core.daobase.BaseDao;
/**
 * 
 * @author Wangds
 * @since 2013-12-9
 * @description 产品分类2数据字典	
 */
@Component
public class ProductType3Dao extends BaseDao implements IProductType3Dao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductType3Model> queryProductType3() {
		return this.getSqlMapClientTemplate().queryForList("ProductType3.queryProductType3");
	}

}
