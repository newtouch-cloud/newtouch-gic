package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.domain.IPolicyStatusDomain;
import com.ca.cacore.ibs.model.bo.PolicyStatusModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
 * 投保单状态动态标签
 * @author xxz521
 *
 */
@Service
public class PolicyStatusSelect extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IPolicyStatusDomain policyStatusDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<PolicyStatusModel> list = policyStatusDomain.queryPolicyStatus();
		for(PolicyStatusModel model:list){
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(model.getStatus_code());
			sd.setName(model.getStatus_name());
			sd.setDisplayLable(model.getStatus_name());
			sds.add(sd);
		}
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		List<PolicyStatusModel> list = policyStatusDomain.queryPolicyStatus();
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<String> strlist = new ArrayList<String>();
		if(filterParameter!=null){
			String[] str = filterParameter.split(":");
			for(String s:str){
				strlist.add(s);
			}
		}
		
		for(PolicyStatusModel policyStatusModel:list){
			if(filterParameter!=null && strlist.contains(policyStatusModel.getStatus_code())){
				continue;
			}else{
				IDynamicSelectData sd=new DynamicSelectData();
				sd.setId(policyStatusModel.getStatus_code());
				sd.setName(policyStatusModel.getStatus_name());
				sd.setDisplayLable(policyStatusModel.getStatus_name());
				sds.add(sd);
			}
		}
		return sds;
	}
}
