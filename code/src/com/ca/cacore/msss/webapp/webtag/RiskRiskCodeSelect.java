package com.ca.cacore.msss.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.msss.domain.IInsRncDfnDomain;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2017年12月05日    
* @author    sunhao
* @description:查询所有险种
*/
@Service
public class RiskRiskCodeSelect extends ServerBase implements IDynamicSelectDataSupport{
	@Autowired
	private IInsRncDfnDomain InsRncDfnDomain;
	
	@Override
	public List<IDynamicSelectData> getData() {
		//List<IInsRncDfnModel> list=InsRncDfnDomain.queryRiskCode();
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		/*for(IInsRncDfnModel iInsRncDfnModel:list){
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(iInsRncDfnModel.getRiskCode());
			sd.setName(iInsRncDfnModel.getRiskName());
			sd.setDisplayLable(iInsRncDfnModel.getRiskName());
			sds.add(sd);
		}*/
		return sds;
	}
	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
