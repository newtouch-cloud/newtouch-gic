package com.ca.cacore.mass.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.mass.model.bo.ProvinceModel;
import com.newtouch.core.daobase.BaseDao;
/**
* @company:  newtouch
* @since:    2013年12月5日 下午2:30:46   
* @author    ss
* @description:
*/
@Component
public class ProvinceDao extends BaseDao implements IProvinceDao{
	@SuppressWarnings("unchecked")
	@Override
	public List<ProvinceModel> queryProvince() {
		return this.getSqlMapClientTemplate().queryForList("province.queryProvince");
	}
	
}
