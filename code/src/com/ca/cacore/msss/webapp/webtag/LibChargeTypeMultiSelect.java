package com.ca.cacore.msss.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.msss.domain.ILibChargeTypeDomain;
import com.ca.cacore.msss.model.bo.LibChargeTypeModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicRadioData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicMultiSelectTagSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2013年12月09日    
* @author    wang_ds
* @description:LibChargeTypeSelect缴费方式数据字典标签
*/
@Service
public class LibChargeTypeMultiSelect extends ServerBase implements IDynamicMultiSelectTagSupport{
	@Autowired private ILibChargeTypeDomain LibChargeTypeDomain;

	@Override
	public List<DynamicRadioData> getCheckbox() {
		List<LibChargeTypeModel> list=LibChargeTypeDomain.queryLibChargeType();
		List<DynamicRadioData> sds=new ArrayList<DynamicRadioData>();
		for(LibChargeTypeModel chargeTypeModel:list){
			DynamicRadioData sd=new DynamicRadioData();
			sd.setName(chargeTypeModel.getCharge_type_name());
			sd.setDisplayLable(chargeTypeModel.getCharge_type_code());
			sds.add(sd);
		}
		return sds;
	}

}
