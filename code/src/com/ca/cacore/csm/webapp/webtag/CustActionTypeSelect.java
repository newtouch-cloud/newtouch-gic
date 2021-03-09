package com.ca.cacore.csm.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.csm.domain.customerAction.ICustActionTypeDomain;
import com.ca.cacore.csm.model.bo.CustomerActionTypeModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2014年1月5日 下午18:06   
* @author    fqy
* @description:客户行为类型标签CustActionTypeSelect
*/
@Service
public class CustActionTypeSelect extends ServerBase implements IDynamicSelectDataSupport {
	@Autowired private ICustActionTypeDomain custActionTypeDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<CustomerActionTypeModel> list=custActionTypeDomain.queryCustActionType();
		for (CustomerActionTypeModel customerActionTypeModel : list) {
			IDynamicSelectData sd = new DynamicSelectData();
			sd.setId(customerActionTypeModel.getActiontype_code());
			sd.setName(customerActionTypeModel.getActiontype_name());
			sd.setDisplayLable(customerActionTypeModel.getActiontype_name());
			sds.add(sd);
		}
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
