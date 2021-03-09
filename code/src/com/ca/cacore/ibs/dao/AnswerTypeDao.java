package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.bo.AnswerTypeModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class AnswerTypeDao  extends BaseDao implements IAnswerTypeDao{
	public List<AnswerTypeModel> queryAnswerType(){
		return this.getSqlMapClientTemplate().queryForList("answerType.queryAnswerType");
	};
}
