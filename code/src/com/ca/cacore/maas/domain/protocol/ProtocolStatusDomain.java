package com.ca.cacore.maas.domain.protocol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.maas.dao.protocol.IProtocolStatusDao;
import com.ca.cacore.maas.model.bo.ProtocolModel;
/**
* @since:    2014年4月10日   
* @author    JYF
* @description:协议管理-状态Domain层
*/
@Service
public class ProtocolStatusDomain implements IProtocolStatusDomain {

	@Autowired private IProtocolStatusDao protocolStatusDao;
	/** 
	* 
	* @return 
	* @description:状态查询
	*/
	@Override
	public List<ProtocolModel> queryProtocolStatus() {
		return protocolStatusDao.queryProtocolStatus();
	}

}
