package com.ca.cacore.msss.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.msss.domain.ILibCoveragePeriodDomain;
import com.ca.cacore.msss.model.bo.ILibCoveragePeriodModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicRadioData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicMultiSelectTagSupport;
import com.newtouch.core.serverbase.ServerBase;

/**
* @company:  newtouch
* @since:    2013年12月09日    
* @author    wang_ds
* @description:LibCoveragePeriodSelect保障期限类型数据字典标签
*/
@Service
public class LibCoveragePeriodMultiSelect extends ServerBase implements IDynamicMultiSelectTagSupport{
	@Autowired private ILibCoveragePeriodDomain LibCoveragePeriodDomain;
	@Override
	public List<DynamicRadioData> getCheckbox() {
		List<ILibCoveragePeriodModel> list=LibCoveragePeriodDomain.queryLibCoveragePeriod();
		List<DynamicRadioData> sds=new ArrayList<DynamicRadioData>();
		for(ILibCoveragePeriodModel coveragePeriodModel:list){
			DynamicRadioData sd=new DynamicRadioData();
			sd.setName(coveragePeriodModel.getCoverage_period_name());
			sd.setDisplayLable(coveragePeriodModel.getCoverage_period_code());
			sds.add(sd);
		}
		return sds;
	}

}
