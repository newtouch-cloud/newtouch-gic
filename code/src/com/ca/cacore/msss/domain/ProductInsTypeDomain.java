package com.ca.cacore.msss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ca.cacore.msss.dao.IProductInsTypeDao;
import com.ca.cacore.msss.model.bo.ProductInsTypeModel;
/**
 * 
 * @author Wangds
 * @since 2013-11-20
 * @description 主附险分类数据字典Domain层
 */
@Service
public class ProductInsTypeDomain implements IProductInsTypeDomain{
	@Autowired private IProductInsTypeDao ProductInsTypeDao;
	@Override
	public List<ProductInsTypeModel> queryProductInsType() {
		return ProductInsTypeDao.queryProductInsType();
	}
}
