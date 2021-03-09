package com.ca.cacore.msss.domain;

import java.util.List;

import com.ca.cacore.msss.model.bo.ProductInsuredFlagModel;
/**
 * 
 * @author Wangds
 * @since 2013-11-20
 * @description 多被保人类型数据字典Domain层
 */
public interface IProductInsuredFlagDomain {
	public List<ProductInsuredFlagModel> queryProductInsuredFlag();
}
