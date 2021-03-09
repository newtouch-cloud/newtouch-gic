package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.dao.IDividendAndAuthTypeDao;
import com.ca.cacore.ibs.model.bo.ProductAuthTypeModel;

@Service
public class DividendAndAuthTypeDomain implements IDividendAndAuthTypeDomain{
	@Autowired private IDividendAndAuthTypeDao dvidendAndAuthTypeDao;
	@Override
	public List<ProductAuthTypeModel> queryAuthType() {
		return dvidendAndAuthTypeDao.queryAuthType();
	}

	@Override
	public List<ProductAuthTypeModel> queryDividendType() {
		return dvidendAndAuthTypeDao.queryDividendType();
	}

}
