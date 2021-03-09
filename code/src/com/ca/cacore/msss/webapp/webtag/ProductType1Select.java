package com.ca.cacore.msss.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.msss.domain.IProductType1Domain;
import com.ca.cacore.msss.model.bo.ProductType1Model;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2013年12月09日    
* @author    wang_ds
* @description:ProductType1Select产品分类1标签
*/
@Service
public class ProductType1Select extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IProductType1Domain ProductType1Domain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<ProductType1Model> list=ProductType1Domain.queryProductType1();
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		for(ProductType1Model type1Model:list){
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(type1Model.getProduct_type_code());
			sd.setName(type1Model.getProduct_type_name());
			sd.setDisplayLable(type1Model.getProduct_type_name());
			sds.add(sd);
		}
		return sds;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
