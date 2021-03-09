package com.ca.cacore.msss.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import com.ca.cacore.msss.model.bo.ProductInsuredFlagModel;
import com.newtouch.core.daobase.BaseDao;
/**
 * 
 * @author Wangds
 * @since 2013-12-9
 * @description 多被保人类型数据字典
 */
@Component
public class ProductInsuredFlagDao extends BaseDao implements IProductInsuredFlagDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductInsuredFlagModel> queryProductInsuredFlag() {
		return this.getSqlMapClientTemplate().queryForList("ProductInsuredFlag.queryProductInsuredFlag");
	}

}
