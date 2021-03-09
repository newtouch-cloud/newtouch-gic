package com.ca.cacore.mass.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.mass.model.bo.CityModel;
import com.newtouch.core.daobase.BaseDao;

/**
* @company:  newtouch
* @since:    2013年12月5日 下午5:22:13   
* @author    ss
* @description:
*/
@Component
public class CityDao extends BaseDao implements ICityDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<CityModel> getCities() {
		return this.getSqlMapClientTemplate().queryForList("city.queryCity");
	}

}
