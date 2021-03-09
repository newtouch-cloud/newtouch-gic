package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.domain.IPolicyLifeOverdueDomain;
import com.ca.cacore.ibs.model.bo.PolicyLifeOverdueModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
 * 保费逾期未付动态标签
 * @author xxz521
 *
 */
@Service
public class PolicyLifeOverdueSelect extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired  private IPolicyLifeOverdueDomain policyLifeOverdueDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<PolicyLifeOverdueModel>list = policyLifeOverdueDomain.queryPolicyLifeOverdue();
		for(PolicyLifeOverdueModel model:list){
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(model.getOverdue_code());
			sd.setName(model.getOverdue_name());
			sd.setDisplayLable(model.getOverdue_name());
			sds.add(sd);
		}
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}
	
}
