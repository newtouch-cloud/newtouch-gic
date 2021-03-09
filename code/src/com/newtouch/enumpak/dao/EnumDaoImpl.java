package com.newtouch.enumpak.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.newtouch.core.daobase.BaseDao;
import com.newtouch.enumpak.vo.EnumEntity;

@Component
public class EnumDaoImpl extends BaseDao implements EnumDao{

	@Override
	public List<EnumEntity> findEnum(String upEnum) {
		return this.getSqlMapClientTemplate().queryForList("enumEntity.findEnum", upEnum);	}

	@Override
	public List<EnumEntity> findEnumPro(EnumEntity entity) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("enumEntity.findEnumPro", entity);	
	}
}
