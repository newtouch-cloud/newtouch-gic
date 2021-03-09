package com.ca.cacore.msss.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.msss.domain.ILibChargePeriodDomain;
import com.ca.cacore.msss.model.bo.LibChargePeriodModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicRadioData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicMultiSelectTagSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2013年12月09日    
* @author    wang_ds
* @description:LibChargePeriodSelect缴费期限类型数据字典标签
*/
@Service
public class LibChargePeriodMultiSelect extends ServerBase implements IDynamicMultiSelectTagSupport{
	@Autowired private ILibChargePeriodDomain LibChargePeriodDomain;
	@Override
	public List<DynamicRadioData> getCheckbox() {
		List<LibChargePeriodModel> list=LibChargePeriodDomain.queryLibChargePeriod();
		List<DynamicRadioData> sds=new ArrayList<DynamicRadioData>();
		for(LibChargePeriodModel chargePeriodModel:list){
			DynamicRadioData sd=new DynamicRadioData();
			sd.setName(chargePeriodModel.getCharge_period_name());
			sd.setDisplayLable(chargePeriodModel.getCharge_period_code());
			sds.add(sd);
		}
		return sds;
	}

}
