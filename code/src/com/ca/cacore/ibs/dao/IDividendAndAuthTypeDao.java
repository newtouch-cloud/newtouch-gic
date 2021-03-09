package com.ca.cacore.ibs.dao;

import java.util.List;

import com.ca.cacore.ibs.model.bo.ProductAuthTypeModel;

public interface IDividendAndAuthTypeDao {
	public List<ProductAuthTypeModel> queryAuthType();
	public List<ProductAuthTypeModel> queryDividendType();
}
