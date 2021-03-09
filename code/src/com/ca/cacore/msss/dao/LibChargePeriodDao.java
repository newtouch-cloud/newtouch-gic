package com.ca.cacore.msss.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import com.ca.cacore.msss.model.bo.LibChargePeriodModel;
import com.newtouch.core.daobase.BaseDao;
/**
 * 
 * @author Wangds
 * @since 2013-12-9
 * @description 缴费期限类型数据字典
 */
@Component
public class LibChargePeriodDao extends BaseDao implements ILibChargePeriodDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<LibChargePeriodModel> queryLibChargePeriod() {
		return this.getSqlMapClientTemplate().queryForList("LibChargePeriod.queryLibChargePeriod");
	}

}
