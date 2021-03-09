package com.ca.cacore.msss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.msss.dao.ILibCoveragePeriodDao;
import com.ca.cacore.msss.model.bo.ILibCoveragePeriodModel;

/**
 * 
 * @author Wangds
 * @since 2013-11-20
 * @description 保障期限类型数据字典Domain层
 */
@Service
public class LibCoveragePeriodDomain implements ILibCoveragePeriodDomain{
	@Autowired private ILibCoveragePeriodDao LibCoveragePeriodDao;
	@Override
	public List<ILibCoveragePeriodModel> queryLibCoveragePeriod() {
		return LibCoveragePeriodDao.queryLibCoveragePeriod();
	}
}
