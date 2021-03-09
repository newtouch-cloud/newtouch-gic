package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.domain.IInsBranchDomain;
import com.ca.cacore.mass.model.bo.ICompanyBranchModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

@Service
public class InsBranchSelect extends ServerBase implements IDynamicSelectDataSupport {
	@Autowired
	private IInsBranchDomain insBranchDomain;

	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> lis = new ArrayList<IDynamicSelectData>();
		List<ICompanyBranchModel> list = insBranchDomain.queryInsBranch();
		for (ICompanyBranchModel model : list) {
			IDynamicSelectData dsd = new DynamicSelectData();
			dsd.setId(model.getBranch_id());
			dsd.setName(model.getBranch_name());
			dsd.setDisplayLable(model.getBranch_name());
			lis.add(dsd);
		}
		return lis;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
