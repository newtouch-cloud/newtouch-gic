package com.ca.cacore.ibs.domain;

import java.util.List;

import com.ca.cacore.ibs.model.bo.ProductAuthTypeModel;

public interface IDividendAndAuthTypeDomain {
	public List<ProductAuthTypeModel> queryAuthType();
	public List<ProductAuthTypeModel> queryDividendType();
}
