package com.ca.cacore.msss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ca.cacore.msss.dao.IProductInsuredFlagDao;
import com.ca.cacore.msss.model.bo.ProductInsuredFlagModel;
/**
 * 
 * @author Wangds
 * @since 2013-11-20
 * @description 多被保人类型数据字典Domain层
 */
@Service
public class ProductInsuredFlagDomain implements IProductInsuredFlagDomain{
	@Autowired private IProductInsuredFlagDao ProductInsuredFlagDao;
	@Override
	public List<ProductInsuredFlagModel> queryProductInsuredFlag() {
		return ProductInsuredFlagDao.queryProductInsuredFlag();
	}
}
