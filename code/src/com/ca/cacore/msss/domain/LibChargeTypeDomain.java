package com.ca.cacore.msss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ca.cacore.msss.dao.ILibChargeTypeDao;
import com.ca.cacore.msss.model.bo.LibChargeTypeModel;
/**
 * 
 * @author Wangds
 * @since 2013-11-20
 * @description 缴费方式数据字典Domain层
 */
@Service
public class LibChargeTypeDomain implements ILibChargeTypeDomain{
	@Autowired private ILibChargeTypeDao LibChargeTypeDao;
	@Override
	public List<LibChargeTypeModel> queryLibChargeType() {
		return LibChargeTypeDao.queryLibChargeType();
	}
}
