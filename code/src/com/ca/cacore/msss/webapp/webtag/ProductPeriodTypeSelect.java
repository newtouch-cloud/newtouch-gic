package com.ca.cacore.msss.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.msss.domain.IProductPeriodTypeDomain;
import com.ca.cacore.msss.model.bo.ProductPeriodTypeModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2013年12月09日    
* @author    wang_ds
* @description:ProductPeriodTypeSelect保险期限分类数据字典标签
*/
@Service
public class ProductPeriodTypeSelect extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IProductPeriodTypeDomain ProductPeriodTypeDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<ProductPeriodTypeModel> list=ProductPeriodTypeDomain.queryProductPeriodType();
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		for(ProductPeriodTypeModel periodTypeModel:list){
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(periodTypeModel.getPeriodType_code());
			sd.setName(periodTypeModel.getPeriodType_name());
			sd.setDisplayLable(periodTypeModel.getPeriodType_name());
			sds.add(sd);
		}
		return sds;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
