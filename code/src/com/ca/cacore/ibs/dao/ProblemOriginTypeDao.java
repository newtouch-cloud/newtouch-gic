package com.ca.cacore.ibs.dao;

import java.util.List;
import org.springframework.stereotype.Component;
import com.ca.cacore.ibs.model.bo.ProblemOriginTypeModel;
import com.newtouch.core.daobase.BaseDao;

/**
* @since:    2013年12月5日 下午5:23:26   
* @author    GCH
* @description:问题件业务来源数据字典
*/
@Component
public class ProblemOriginTypeDao extends BaseDao implements IProblemOriginTypeDao{
	@SuppressWarnings("unchecked")
	@Override
	public List<ProblemOriginTypeModel> queryProblemOriginType() {
		return this.getSqlMapClientTemplate().queryForList("problemOriginType.queryProblemOriginType");
	}

}
