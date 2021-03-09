package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.domain.IFeeStatusDomain;
import com.ca.cacore.ibs.model.bo.FeeStatusModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;
/**
 * 
* @since:    2014年1月10日   
* @author    ZhangChen
* @description:费用处理状态标签
 */
@Service
public class FeeStatusSelect extends ServerBase implements IDynamicSelectDataSupport{
	
	@Autowired private IFeeStatusDomain feeStatusDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<FeeStatusModel> list=feeStatusDomain.getStatus();
		for (FeeStatusModel model : list) {
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(model.getStatus_code());
			sd.setName(model.getStatus_name());
			sd.setDisplayLable(model.getStatus_name());
			sds.add(sd);
		}
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
