package com.ca.cacore.msss.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.msss.domain.IProductInsTypeDomain;
import com.ca.cacore.msss.model.bo.ProductInsTypeModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2013年12月09日    
* @author    wang_ds
* @description:ProductInsTypeSelect主附险分类数据字典标签
*/
@Service
public class ProductInsTypeSelect extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IProductInsTypeDomain ProductInsTypeDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<ProductInsTypeModel> list=ProductInsTypeDomain.queryProductInsType();
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		for(ProductInsTypeModel insTypeModel:list){
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(insTypeModel.getIns_type_code());
			sd.setName(insTypeModel.getIns_type_name());
			sd.setDisplayLable(insTypeModel.getIns_type_name());
			sds.add(sd);
		}
		return sds;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
