package com.ca.cacore.msss.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import com.ca.cacore.msss.model.bo.ProductInsTypeModel;
import com.newtouch.core.daobase.BaseDao;
/**
 * 
 * @author Wangds
 * @since 2013-12-9
 * @description 主附险分类数据字典
 */
@Component
public class ProductInsTypeDao extends BaseDao implements IProductInsTypeDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductInsTypeModel> queryProductInsType() {
		return this.getSqlMapClientTemplate().queryForList("ProductInsType.queryProductInsType");
	}

}
