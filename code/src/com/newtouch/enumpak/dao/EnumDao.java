package com.newtouch.enumpak.dao;

import java.util.List;

import com.newtouch.enumpak.vo.EnumEntity;

public interface EnumDao {
	List<EnumEntity> findEnum(String parentId);
	
	List<EnumEntity> findEnumPro(EnumEntity entity);
}
