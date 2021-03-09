package com.ca.cacore.maas.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.maas.domain.protocol.IProtocolStatusDomain;
import com.ca.cacore.maas.model.bo.IProtocolModel;
import com.ca.cacore.maas.model.bo.ProtocolModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;
@Service
public class ProtocolStatusSelect extends ServerBase implements IDynamicSelectDataSupport{

	@Autowired private IProtocolStatusDomain protocolStatusDomain;
	@Override
	public List<IDynamicSelectData> getData() {
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		List<ProtocolModel> list=protocolStatusDomain.queryProtocolStatus();
		for (IProtocolModel model : list) {
			IDynamicSelectData sd = new DynamicSelectData();
			sd.setId(model.getStatus_code());
			sd.setName(model.getStatus_name());
			sd.setDisplayLable(model.getStatus_name());
			sds.add(sd);
		}
		return sds;
	}

	@Override
	public List<IDynamicSelectData> getData(String filterParameter) {
		return null;
	}

}
