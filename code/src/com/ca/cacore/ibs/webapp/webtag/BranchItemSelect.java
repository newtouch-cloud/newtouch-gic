package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.BranchModel;
import com.ca.cacore.ibs.domain.IBranchSelectDomain;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

@Service
public class BranchItemSelect extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired private IBranchSelectDomain branchSelectDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> ds=new ArrayList<IDynamicSelectData>();
		List<BranchModel> list=branchSelectDomain.queryBranch();
		
		for(BranchModel model:list){
			IDynamicSelectData dsd=new DynamicSelectData();
			dsd.setId(model.getBranch_id());
			dsd.setName(model.getBranch_name());
			dsd.setDisplayLable(model.getBranch_name());
			ds.add(dsd);
		}
		return ds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
