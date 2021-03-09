package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.domain.IBeneficiaryTypeDomain;
import com.ca.cacore.ibs.model.bo.BeneficiaryTypeModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
 * 收益性质动态标签
 * @author xxz521
 *
 */
@Service
public class BeneficiaryTypeSelect extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IBeneficiaryTypeDomain beneficiaryTypeDomain;
	
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<BeneficiaryTypeModel> list = beneficiaryTypeDomain.queryBeneficiaryType();
		for(BeneficiaryTypeModel model:list){
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(model.getBene_type_code());
			sd.setName(model.getBene_type_name());
			sd.setDisplayLable(model.getBene_type_name());
			sds.add(sd);
		}
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
