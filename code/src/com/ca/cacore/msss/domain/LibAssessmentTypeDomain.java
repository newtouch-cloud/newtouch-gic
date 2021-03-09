package com.ca.cacore.msss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ca.cacore.msss.dao.ILibAssessmentTypeDao;
import com.ca.cacore.msss.model.bo.LibAssessmentTypeModel;


/**
* @since:    2013年12月25日   
* @author    wang_ds
* @description:LibAssessmentTypeDomain产品评估类型数据字典Domain层
*/


@Service
public class LibAssessmentTypeDomain implements ILibAssessmentTypeDomain {
	@Autowired private ILibAssessmentTypeDao LibAssessmentTypeDao;
	
	/** 
	* 
	* @return List<LibAssessmentTypeModel>
	* @description:queryLibAssessmentType产品评估类型数据
	*/
	@Override
	public List<LibAssessmentTypeModel> queryLibAssessmentType() {
		return LibAssessmentTypeDao.queryLibAssessmentType();
	}
}
