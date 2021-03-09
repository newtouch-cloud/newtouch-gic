package com.ca.cacore.msss.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.msss.domain.IInsRncDfnDomain;
import com.ca.cacore.mass.model.bo.CompanyBranchModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @since:    2013年12月09日    
* @author    wang_ds
* @description:IsAuthPartreceivedSelect 是否可以部分领取年金
*/
@Service
public class CompanySelect extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired
	private IInsRncDfnDomain InsRncDfnDomain;
	
	@Override
	public List<IDynamicSelectData> getData() {
		List<CompanyBranchModel> list=InsRncDfnDomain.queryCompany();
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		for(CompanyBranchModel insTypeModel:list){
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(insTypeModel.getBranch_id());
			sd.setName(insTypeModel.getBranch_name());
			sd.setDisplayLable(insTypeModel.getBranch_name());
			sds.add(sd);
		}
		return sds;
		}
		
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
