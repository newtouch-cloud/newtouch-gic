package com.ca.cacore.msss.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import com.ca.cacore.msss.model.bo.ProductType2Model;
import com.newtouch.core.daobase.BaseDao;
/**
 * 
 * @author Wangds
 * @since 2013-12-9
 * @description 产品分类2数据字典	
 */
@Component
public class ProductType2Dao extends BaseDao implements IProductType2Dao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductType2Model> queryProductType2() {
		return this.getSqlMapClientTemplate().queryForList("ProductType2.queryProductType2");
	}

}
