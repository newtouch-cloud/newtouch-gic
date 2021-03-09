package com.ca.cacore.ibs.domain;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ca.cacore.ibs.dao.IProblemStatusDao;
import com.ca.cacore.ibs.model.bo.ProblemStatusModel;

/**
* @since:    2013年12月5日 下午5:24:15   
* @author    GCH
* @description:问题件状态数据字典
*/
@Service
public class ProblemStatusDomain implements IProblemStatusDomain{
	@Autowired private IProblemStatusDao problemStatusDao;
	@Override
	public List<ProblemStatusModel> queryProblemStatus() {
		return problemStatusDao.queryProblemStatus();
	}
}
