package com.ca.cacore.msss.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.msss.domain.IProductInsuredFlagDomain;
import com.ca.cacore.msss.model.bo.ProductInsuredFlagModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2013年12月09日    
* @author    wang_ds
* @description:ProductInsuredFlagSelect 多被保人类型数据字典 标签
*/
@Service
public class ProductInsuredFlagSelect extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IProductInsuredFlagDomain ProductInsuredFlagDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<ProductInsuredFlagModel> list=ProductInsuredFlagDomain.queryProductInsuredFlag();
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		for(ProductInsuredFlagModel insuredFlagModel:list){
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(insuredFlagModel.getInsuredFlag_code());
			sd.setName(insuredFlagModel.getInsuredFlag_name());
			sd.setDisplayLable(insuredFlagModel.getInsuredFlag_name());
			sds.add(sd);
		}
		return sds;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
