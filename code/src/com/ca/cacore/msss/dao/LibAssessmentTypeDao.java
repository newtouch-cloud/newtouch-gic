package com.ca.cacore.msss.dao;

import java.util.List;
import org.springframework.stereotype.Component;
import com.ca.cacore.msss.model.bo.LibAssessmentTypeModel;
import com.newtouch.core.daobase.BaseDao;

/**
* @since:    2013年12月25日   
* @author    wang_ds
* @description: LibAssessmentTypeDao 产品评估类型数据字典dao层
*/
@Component
public class LibAssessmentTypeDao extends BaseDao implements ILibAssessmentTypeDao {


	@SuppressWarnings("unchecked")
	/** 
	* 
	* @return List
	* @description: queryLibAssessmentType 查询出所有的产品评估类型数据
	*/
	@Override
	public List<LibAssessmentTypeModel> queryLibAssessmentType() {
		return this.getSqlMapClientTemplate().queryForList("LibAssessmentType.queryLibAssessmentType");
	}

}
