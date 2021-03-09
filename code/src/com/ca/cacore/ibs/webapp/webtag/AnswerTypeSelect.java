package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.domain.IAnswerTypeDomain;
import com.ca.cacore.ibs.model.bo.AnswerTypeModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2013年12月10日   
* @author  xxz
* @description:回访方式标签：answerTypeSelect
 */
@Service
public class AnswerTypeSelect extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IAnswerTypeDomain answerTypeDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<AnswerTypeModel> list = answerTypeDomain.queryAnswerType();
		for(AnswerTypeModel model:list){
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(model.getAnswer_type_code());
			sd.setName(model.getAnswer_type_name());
			sd.setDisplayLable(model.getAnswer_type_name());
			sds.add(sd);
		}
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}
	
}
