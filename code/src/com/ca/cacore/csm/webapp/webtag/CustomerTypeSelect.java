package com.ca.cacore.csm.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.csm.domain.customerType.ICustomerTypeDomain;
import com.ca.cacore.csm.model.bo.ICustomerTypeModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2014-1-4 13:20  
* @author    张晨
* @description:客户类型标签
*/
@Service
public class CustomerTypeSelect extends ServerBase implements IDynamicSelectDataSupport {
	@Autowired private ICustomerTypeDomain customerTypeDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<ICustomerTypeModel> list=customerTypeDomain.getTypes();
		for (ICustomerTypeModel typeModel : list) {
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(typeModel.getType_code());
			sd.setName(typeModel.getType_name());
			sd.setDisplayLable(typeModel.getType_name());
			sds.add(sd);
		}
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		List<ICustomerTypeModel> list=customerTypeDomain.getTypes();
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		String[] str = filterParameter.split(":");
		List<String> strlist = new ArrayList<String>();
		for(String s:str){
			strlist.add(s);
		}
		for(ICustomerTypeModel typeModel:list){
			if(strlist.contains(typeModel.getType_code())){
				continue;
			}else{
				IDynamicSelectData sd=new DynamicSelectData();
				sd.setId(typeModel.getType_code());
				sd.setName(typeModel.getType_name());
				sd.setDisplayLable(typeModel.getType_name());
				sds.add(sd);
			}
			
		}
		return sds;
	}

}
