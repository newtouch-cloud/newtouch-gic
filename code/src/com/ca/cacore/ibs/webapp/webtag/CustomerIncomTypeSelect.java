package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.domain.ICustomerIncomTypeDomain;
import com.ca.cacore.ibs.model.bo.CustomerIncomTypeModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
 * 客户收入范围标签
 * @author xxz521
 *
 */
@Service
public class CustomerIncomTypeSelect  extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private ICustomerIncomTypeDomain customerIncomTypeDomain;
	
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<CustomerIncomTypeModel> list = customerIncomTypeDomain.queryIncomType();
		for(CustomerIncomTypeModel model:list){
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(model.getIncomType_code());
			sd.setName(model.getIncomType_name());
			sd.setDisplayLable(model.getIncomType_name());
			sds.add(sd);
		}
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}
	
}
