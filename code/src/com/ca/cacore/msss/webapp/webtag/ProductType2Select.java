package com.ca.cacore.msss.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.msss.domain.IProductType2Domain;
import com.ca.cacore.msss.model.bo.ProductType2Model;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2013年12月09日    
* @author    wang_ds
* @description:ProductType2Select产品分类2标签
*/
@Service
public class ProductType2Select extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IProductType2Domain ProductType2Domain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<ProductType2Model> list=ProductType2Domain.queryProductType2();
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		for(ProductType2Model type2Model:list){
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(type2Model.getProduct_type_code());
			sd.setName(type2Model.getProduct_type_name());
			sd.setDisplayLable(type2Model.getProduct_type_name());
			sds.add(sd);
		}
		return sds;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
