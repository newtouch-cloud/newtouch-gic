package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.msss.domain.ILibChargeTypeDomain;
import com.ca.cacore.msss.model.bo.LibChargeTypeModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
 * 缴费方式标签
 * @author xxz521
 *
 */
@Service
public class LibChargeTypeSelect  extends ServerBase implements IDynamicSelectDataSupport {
@Autowired private ILibChargeTypeDomain libChargeTypeDomain;

@Override
public List<IDynamicSelectData> getData() {
	List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
	List<LibChargeTypeModel>  list= libChargeTypeDomain.queryLibChargeType() ;
	for(LibChargeTypeModel model:list){
		IDynamicSelectData sd=new DynamicSelectData();
		sd.setId(model.getCharge_type_code());
		sd.setName(model.getCharge_type_name());
		sd.setDisplayLable(model.getCharge_type_name());
		sds.add(sd);
	}
	return sds;
}

@Override
public List<IDynamicSelectData> getData(String filterParameter) {
	return null;
}

}
