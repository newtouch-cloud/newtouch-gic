package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.domain.IDividendAndAuthTypeDomain;
import com.ca.cacore.ibs.model.bo.ProductAuthTypeModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
 * 红利领取方式类型数据字典
 * @author xxz521
 *
 */
@Service
public class ProductDividendTypeSelect  extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IDividendAndAuthTypeDomain dividendAndAuthTypeDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<ProductAuthTypeModel> list = dividendAndAuthTypeDomain.queryDividendType();
		for(ProductAuthTypeModel model:list){
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(model.getType_code());
			sd.setName(model.getType_name());
			sd.setDisplayLable(model.getType_name());
			sds.add(sd);
		}
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}
}
