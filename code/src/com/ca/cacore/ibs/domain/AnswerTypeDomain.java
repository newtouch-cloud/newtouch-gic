package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.dao.IAnswerTypeDao;
import com.ca.cacore.ibs.model.bo.AnswerTypeModel;

@Service
public class AnswerTypeDomain implements IAnswerTypeDomain{
	@Autowired private IAnswerTypeDao answerTypeDao ;

	@Override
	public List<AnswerTypeModel> queryAnswerType() {
		return answerTypeDao.queryAnswerType();
	}
}
