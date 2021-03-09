package com.ca.cacore.maas.dao.protocol;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.maas.model.bo.ProtocolModel;
import com.newtouch.core.daobase.BaseDao;
/**
* @since:    2014年4月10日   
* @author    JYF
* @description:协议管理-状态查询-dao层
*/
@Component
public class ProtocolStatusDao extends BaseDao implements IProtocolStatusDao {

	/** 
	* 
	* @return 
	* @description:状态查询
	*/
	@Override
	public List<ProtocolModel> queryProtocolStatus() {
		return this.getSqlMapClientTemplate().queryForList("protocol.queryProtocolStatus");
	}

}
