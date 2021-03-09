package com.ca.cacore.ibs.dao;

import java.util.List;
import org.springframework.stereotype.Component;
import com.ca.cacore.ibs.model.bo.ProblemStatusModel;
import com.newtouch.core.daobase.BaseDao;

/**
* @since:    2013年12月5日 下午5:24:01   
* @author    GCH
* @description:问题件状态数据字典
*/
@Component
public class ProblemStatusDao extends BaseDao implements IProblemStatusDao{
	@SuppressWarnings("unchecked")
	@Override
	public List<ProblemStatusModel> queryProblemStatus() {
		return this.getSqlMapClientTemplate().queryForList("problemStatus.queryProblemStatus");
	}
}
