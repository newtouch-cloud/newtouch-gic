package com.ca.cacore.msss.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.msss.domain.IProductType3Domain;
import com.ca.cacore.msss.model.bo.ProductType3Model;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2013年12月09日    
* @author    wang_ds
* @description:ProductType3Select产品分类3标签
*/
@Service
public class ProductType3Select extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IProductType3Domain ProductType3Domain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<ProductType3Model> list=ProductType3Domain.queryProductType3();
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		for(ProductType3Model type3Model:list){
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(type3Model.getProduct_type_code());
			sd.setName(type3Model.getProduct_type_name());
			sd.setDisplayLable(type3Model.getProduct_type_name());
			sds.add(sd);
		}
		return sds;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
