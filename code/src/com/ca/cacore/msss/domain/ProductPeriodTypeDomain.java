package com.ca.cacore.msss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ca.cacore.msss.dao.IProductPeriodTypeDao;
import com.ca.cacore.msss.model.bo.ProductPeriodTypeModel;
/**
 * 
 * @author Wangds
 * @since 2013-11-20
 * @description 保险期限分类数据字典Domain层
 */
@Service
public class ProductPeriodTypeDomain implements IProductPeriodTypeDomain{
	@Autowired private IProductPeriodTypeDao ProductPeriodTypeDao;
	@Override
	public List<ProductPeriodTypeModel> queryProductPeriodType() {
		return ProductPeriodTypeDao.queryProductPeriodType();
	}
}
