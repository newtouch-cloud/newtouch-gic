package com.ca.cacore.mass.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.mass.dao.IProvinceDao;
import com.ca.cacore.mass.model.bo.ProvinceModel;
/**
* @company:  newtouch
* @since:    2013年12月5日 下午2:31:09   
* @author    ss
* @description:
*/
@Service
public class ProvinceDomain implements IProvinceDomain {
	@Autowired private IProvinceDao provinceDao;
	@Override
	public List<ProvinceModel> querypProvince() {
		return provinceDao.queryProvince();
	}

}
