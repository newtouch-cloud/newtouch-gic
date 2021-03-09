package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.domain.IPolicyLifeInsuredDomain;
import com.ca.cacore.ibs.model.bo.PolicyLifeInsuredModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2013年12月12日   
* @author  xxz
* @description:被保人动态标签：PolicyLifeInsuredSelect
 */
@Service 
public class PolicyLifeInsuredSelect   extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IPolicyLifeInsuredDomain policyLifeInsuredDomain;

	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<PolicyLifeInsuredModel>  list = policyLifeInsuredDomain.queryAllPolicyLifeInsured();
		for(PolicyLifeInsuredModel model:list){
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(model.getCustomer_id());
			sd.setName(model.getInsurant_name());
			sd.setDisplayLable(model.getInsurant_name());
			sds.add(sd);
		}
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
