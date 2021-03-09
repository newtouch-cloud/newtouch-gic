package com.ca.cacore.msss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ca.cacore.msss.dao.ILibChargePeriodDao;
import com.ca.cacore.msss.model.bo.LibChargePeriodModel;
/**
 * 
 * @author Wangds
 * @since 2013-11-20
 * @description 缴费期限类型数据字典Domain层
 */
@Service
public class LibChargePeriodDomain implements ILibChargePeriodDomain{
	@Autowired private ILibChargePeriodDao LibChargePeriodDao;
	@Override
	public List<LibChargePeriodModel> queryLibChargePeriod() {
		return LibChargePeriodDao.queryLibChargePeriod();
	}
}
