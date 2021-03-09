package com.ca.cacore.ibs.domain;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ca.cacore.ibs.dao.IProblemOriginTypeDao;
import com.ca.cacore.ibs.model.bo.ProblemOriginTypeModel;

/**
* @since:    2013年12月5日 下午5:24:53   
* @author    GCH
* @description:问题件业务来源数据字典
*/
@Service
public class ProblemOriginTypeDomain implements IProblemOriginTypeDomain {
	@Autowired private IProblemOriginTypeDao problemOriginTypeDao;
	@Override
	public List<ProblemOriginTypeModel> queryProblemOriginType() {
		return problemOriginTypeDao.queryProblemOriginType();
	}
	
}
