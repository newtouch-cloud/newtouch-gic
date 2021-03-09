package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.domain.IImageBusTypeDomain;
import com.ca.cacore.ibs.model.bo.ImageBusTypeModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2014-1-4 13:20  
* @author    张晨
* @description:影像件类型标签
*/
@Service
public class ImageBusTypeSelect extends ServerBase implements IDynamicSelectDataSupport {
	@Autowired private IImageBusTypeDomain imageBusTypeDomain;
	
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<ImageBusTypeModel> list=imageBusTypeDomain.getTypes();
		for (ImageBusTypeModel typeModel : list) {
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(typeModel.getImg_type_code());
			sd.setName(typeModel.getImg_type_name());
			sd.setDisplayLable(typeModel.getImg_type_name());
			sds.add(sd);
		}
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
