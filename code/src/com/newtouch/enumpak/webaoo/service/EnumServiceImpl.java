package com.newtouch.enumpak.webaoo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.enumpak.dao.EnumDao;
import com.newtouch.enumpak.vo.EnumEntity;
@Service
public class EnumServiceImpl implements EnumService {
	@Autowired
	private EnumDao enumDao;
	@Override
	public List<EnumEntity> findEnum(String upEnum) {
		return enumDao.findEnum(upEnum);
	}
	@Override
	public List<EnumEntity> findEnumPro(EnumEntity entity) {
		return enumDao.findEnumPro(entity);
	}
	
}
