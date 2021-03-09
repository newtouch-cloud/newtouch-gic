package com.ca.cacore.msss.dao;

import java.util.List;

import com.ca.cacore.msss.model.bo.LibAssessmentTypeModel;

/**
* @since:    2013年12月25日   
* @author    wang_ds
* @description: LibAssessmentTypeDao 产品评估类型数据字典dao层
*/
public interface ILibAssessmentTypeDao {

	public List<LibAssessmentTypeModel> queryLibAssessmentType();

}