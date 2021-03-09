package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import com.ca.cacore.ibs.model.bo.ClaimsStatusModel;
import com.newtouch.core.daobase.BaseDao;

/**
* @since:    2014年2月10日   
* @author    wand_ds
* @description:理赔状态Dao层
*/
@Component
public class ClaimsStatusDao extends BaseDao implements IClaimsStatusDao{
	@SuppressWarnings("unchecked")
	@Override
	public List<ClaimsStatusModel> queryClaimsStatus() {
		return this.getSqlMapClientTemplate().queryForList("claimsStatus.queryClaimsStatus");
	}
}
