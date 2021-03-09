package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.dao.IBeneficiaryTypeDao;
import com.ca.cacore.ibs.model.bo.BeneficiaryTypeModel;

@Service
public class BeneficiaryTypeDomain implements IBeneficiaryTypeDomain {
	@Autowired private IBeneficiaryTypeDao beneficiaryTypeDao;

	@Override
	public List<BeneficiaryTypeModel> queryBeneficiaryType() {
		return beneficiaryTypeDao.queryBeneficiaryType();
	}

}
