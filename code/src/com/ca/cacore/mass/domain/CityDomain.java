package com.ca.cacore.mass.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.mass.dao.ICityDao;
import com.ca.cacore.mass.model.bo.CityModel;

/**
* @company:  newtouch
* @since:    2013年12月5日 下午5:27:08   
* @author    ss
* @description:
*/
@Service
public class CityDomain implements ICityDomain {
	@Autowired private ICityDao cityDao;
	@Override
	public List<CityModel> getCities() {
		return cityDao.getCities();
	}

}
