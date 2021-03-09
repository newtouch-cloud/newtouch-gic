package com.ca.cacore.msss.domain;

import java.util.List;

import com.ca.cacore.msss.model.bo.LibAssessmentTypeModel;
/**
* @since:    2013年12月25日   
* @author    wang_ds
* @description:LibAssessmentTypeDomain产品评估类型数据字典Domain层
*/
public interface ILibAssessmentTypeDomain {

	public List<LibAssessmentTypeModel> queryLibAssessmentType();

}