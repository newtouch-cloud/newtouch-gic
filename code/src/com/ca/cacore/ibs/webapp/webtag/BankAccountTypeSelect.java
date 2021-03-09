package com.ca.cacore.ibs.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.domain.IBankAccountTypeDomain;
import com.ca.cacore.ibs.model.bo.BankAccountTypeModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
 * 
 * @author xxz521
 *
 */
@Service
public class BankAccountTypeSelect  extends ServerBase implements IDynamicSelectDataSupport {
	@Autowired private IBankAccountTypeDomain bankAccountTypeDomain;
	
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<BankAccountTypeModel>  list= bankAccountTypeDomain.queryBankAccountTypeModel() ;
		for(BankAccountTypeModel model:list){
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(model.getAccountType_code());
			sd.setName(model.getAccountType_name());
			sd.setDisplayLable(model.getAccountType_name());
			sds.add(sd);
		}
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}
	
}
