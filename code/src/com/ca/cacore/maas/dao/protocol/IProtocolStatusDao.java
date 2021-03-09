package com.ca.cacore.maas.dao.protocol;

import java.util.List;

import com.ca.cacore.maas.model.bo.ProtocolModel;

/**
* @since:    2014年4月10日   
* @author    JYF
* @description:协议管理-状态-dao层接口
*/
public interface IProtocolStatusDao {

	/** 
	* 
	* @return List<ProtocolModel>
	* @description:状态-查询
	*/
	public List<ProtocolModel> queryProtocolStatus();
}
