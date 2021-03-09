package com.ca.cacore.msss.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.msss.model.bo.ProductPeriodTypeModel;
import com.newtouch.core.daobase.BaseDao;
/**
 * 
 * @author Wangds
 * @since 2013-12-9
 * @description 保险期限分类数据字典
 */
@Component
public class ProductPeriodTypeDao extends BaseDao implements IProductPeriodTypeDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductPeriodTypeModel> queryProductPeriodType() {
		return this.getSqlMapClientTemplate().queryForList("ProductPeriodType.queryProductPeriodType");
	}

}
