package com.ca.cacore.maas.domain.protocol;

import java.util.List;

import com.ca.cacore.maas.model.bo.ProtocolModel;

/**
* @since:    2014年4月10日   
* @author    JYF
* @description:协议管理-状态-Domain层接口
*/
public interface IProtocolStatusDomain {

	/** 
	* 
	* @return List<ProtocolModel>
	* @description:状态查询
	*/
	public List<ProtocolModel> queryProtocolStatus();
}
